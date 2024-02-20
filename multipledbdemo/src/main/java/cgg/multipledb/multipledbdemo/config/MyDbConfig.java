package cgg.multipledb.multipledbdemo.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "entityManagerFactory",
  basePackages = { "cgg.multipledb.multipledbdemo.repos" },
  transactionManagerRef = "transactionManager"
)
public class MyDbConfig {

  @Autowired
  private Environment environment;

  @Bean
  @Primary
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    // dataSource.setUrl(environment.getProperty("second.datasource.url"));
    dataSource.setUrl(environment.getProperty("second.datasource.url"));
    // dataSource.setDriverClassName(
    //   environment.getProperty("spring.datasource.driver-class-name")
    // );
    dataSource.setDriverClassName(
      environment.getProperty("second.datasource.driver-class-name")
    );
    // dataSource.setUsername(
    //   environment.getProperty("spring.datasource.username")
    // ); // Corrected to setUsername
    dataSource.setUsername(
      environment.getProperty("second.datasource.username")
    ); // Corrected to setUsername
    // dataSource.setPassword(
    //   environment.getProperty("spring.datasource.password")
    // );
    dataSource.setPassword(
      environment.getProperty("second.datasource.password")
    );
    return dataSource;
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource());
    JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    bean.setJpaVendorAdapter(adapter);
    Map<String, String> props = new HashMap<>();
    // props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    props.put("hibernate.show_sql", "true");
    props.put("hibernate.hbm2ddl.auto", "update");
    bean.setJpaPropertyMap(props);
    bean.setPackagesToScan("cgg.multipledb.multipledbdemo.entities");
    return bean;
  }

  @Primary
  @Bean(name = "transactionManager")
  PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }
}
