package cgg.springassign.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*This class will contain bean for viewresolver
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the
 *                  class can be used by the Spring IoC container as a source of
 *                  bean definitions
 * @ComponentScan - this annotation is used to search for the Spring components amongst the application
 * @EnableWebMvc - Adding this annotation to an @Configuration class imports the Spring MVC
 * 				   configuration from WebMvcConfigurationSupport
 * */
@Configuration
@ComponentScan(basePackages = "cgg.springassign")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  /*
   * Define the bean for view resolver so that it can be used to resolve the JSP
   * files which are existing in /WEB-INF/views folder. A ViewResolver is capable
   * of mapping logical view names to actual views, such as a JSP or a HTML page.
   */

  @Bean("viewResolver")
  InternalResourceViewResolver getViewResolver() {
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    internalResourceViewResolver.setPrefix("/WEB-INF/views/");
    internalResourceViewResolver.setSuffix(".jsp");
    return internalResourceViewResolver;
  }

  @Bean
  public MappingJackson2HttpMessageConverter jsonMessageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }

  @Override
  public void configureMessageConverters(
    List<HttpMessageConverter<?>> converters
  ) {
    converters.add(jsonMessageConverter());
  }
}
