package ru.dilichev.AccountControl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:application.properties")
public class DBConfig {
    @Value("${DB_driver_class}")
    String driver;

    @Value("${DB_host}")
    String host;

    @Value("${DB_username}")
    String username;

    @Value("${DB_passwd}")
    String passwd;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(oraDataSource());
        sessionFactory.setPackagesToScan("ru.dilichev.AccountControl.models");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        hibernateProperties.setProperty("connection_pool_size", "1");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    private DataSource oraDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(host);
        dataSource.setUsername(username);
        dataSource.setPassword(passwd);

        return dataSource;
    }
}
