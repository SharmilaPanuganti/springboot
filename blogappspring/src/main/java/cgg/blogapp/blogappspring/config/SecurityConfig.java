package cgg.blogapp.blogappspring.config;

import cgg.blogapp.blogappspring.helpers.JwtAuthEntryPoint;
import cgg.blogapp.blogappspring.helpers.JwtAuthFilter;
import cgg.blogapp.blogappspring.security.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 */
@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Blog app API",
    version = "2.2.0",
    description = "Api to create blog posts",
    license = @License(name = "Apache 2.o", url = "http://www.cgg.gov.in")
  ),
  externalDocs = @ExternalDocumentation(
    description = "External documentation description",
    url = "https://example.com"
  )
)
@SecurityScheme(
  name = "bearerScheme",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtAuthEntryPoint entryPoint;

  @Autowired
  private JwtAuthFilter authFilter;

  @Bean
  SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/api/v1/posts/**")
          .permitAll()
          .requestMatchers("/api/v1/users/**")
          .hasRole("USER")
          .requestMatchers("/api/v1/posts/**")
          .hasRole("USER")
          .requestMatchers("/api/v1/categories/**")
          .hasRole("USER")
          .requestMatchers("/api/v1/comments/**")
          .hasRole("USER")
          .requestMatchers("/api/admin/**")
          .hasRole("ADMIN")
          .requestMatchers("/api/v1/auth/**")
          .permitAll()
          .requestMatchers(HttpMethod.GET)
          .permitAll()
      )
      .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint))
      .sessionManagement(s ->
        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );
    http.addFilterBefore(
      authFilter,
      UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManagerBean(
    AuthenticationConfiguration config
  ) throws Exception {
    return config.getAuthenticationManager();
  }
}
