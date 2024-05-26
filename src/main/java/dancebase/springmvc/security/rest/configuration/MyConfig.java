package dancebase.springmvc.security.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "dancebase.springmvc.security.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {
    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dancers_base?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("rasl_1998");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("dancebase.springmvc.security.rest");
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql","true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
