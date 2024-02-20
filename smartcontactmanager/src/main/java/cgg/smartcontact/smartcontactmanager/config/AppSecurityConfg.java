package cgg.smartcontact.smartcontactmanager.config;

import cgg.smartcontact.smartcontactmanager.services.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfg {

  @Autowired
  private CustomerUserDetailsService userDetailsService;

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain getSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/users/**")
          .hasRole("USER")
          .requestMatchers("/admin/**")
          .hasRole("ADMIN")
          .requestMatchers("/**")
          .permitAll()
      )
      .formLogin(login ->
        login
          .loginPage("/signin")
          .loginProcessingUrl("/doLogin")
          .defaultSuccessUrl("/users/index")
      );
    return http.build();
  }

  // @Bean
  // UserDetailsService userDetailsService() {
  //   // UserDetails userDetails = User
  //   //   .withUsername("Sharmila")
  //   //   .password(passwordEncoder().encode("add"))
  //   //   .roles("USER")
  //   //   .build();
  //   return userDetailsService;
  // }

  @Bean
  AuthenticationProvider getAuthProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(
      passwordEncoder()
    );
    authProvider.setUserDetailsService(userDetailsService);
    return authProvider;
  }
}
