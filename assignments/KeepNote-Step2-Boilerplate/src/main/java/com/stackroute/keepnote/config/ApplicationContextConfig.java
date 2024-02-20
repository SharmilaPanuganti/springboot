package com.stackroute.keepnote.config;

import com.stackroute.keepnote.model.Note;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*This class will contain the application-context for the application.
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the
 *                  class can be used by the Spring IoC container as a source of
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *
 * */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class ApplicationContextConfig {

  /*
   * Define the bean for DataSource. In our application, we are using MySQL as the
   * dataSource. To create the DataSource bean, we need to know: 1. Driver class
   * name 2. Database URL 3. UserName 4. Password
   */
  @Value("${db.driverClassName}")
  private String driverClass;

  @Value("${db.username}")
  private String userName;

  @Value("${db.url}")
  private String url;

  @Value("${db.password}")
  private String password;

  @Bean("ds")
  DriverManagerDataSource getDataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl(url);
    ds.setUsername(userName);
    ds.setDriverClassName(driverClass);
    ds.setPassword(password);
    return ds;
  }

  /*
        Use this configuration while submitting solution in hobbes.
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DATABASE")
				+"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASSWORD")); */

  /*
   * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
   * class through which we get sessions and perform database operations.
   */
  @Bean("factory")
  LocalSessionFactoryBean getFactory() {
    LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
    factory.setDataSource(getDataSource());
    Properties p = new Properties();
    p.put("hibernate.hbm2ddl.auto", "update");
    p.put("hibernate.show_sql", true);
    p.put("hibernate.format_sql", true);
    p.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    factory.setHibernateProperties(p);
    factory.setAnnotatedClasses(Note.class);
    return factory;
  }

  /*
   * Define the bean for Transaction Manager. HibernateTransactionManager handles
   * transaction in Spring. The application that uses single hibernate session
   * factory for database transaction has good choice to use
   * HibernateTransactionManager. HibernateTransactionManager can work with plain
   * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
   * ensures data integrity.
   */
  @Bean("transactionManager")
  HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager tx = new HibernateTransactionManager();
    tx.setSessionFactory(getFactory().getObject());
    return tx;
  }
}
