package pack.restserver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("pack.restserver.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("pack.restserver")
public class DatabaseConfig {

    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean= new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));

        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(getHibernateProperties());

        return factoryBean;
    }

    public Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);
            return properties;
        } catch (IOException Ex) {
            throw new IllegalArgumentException("Can't find 'hibernate.properties'", Ex);
        }
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        dataSource.setInitialSize(Integer.valueOf(environment.getRequiredProperty("db.initialSize")));
        dataSource.setMinIdle(Integer.valueOf(environment.getRequiredProperty("db.minIdle")));
        dataSource.setMaxIdle(Integer.valueOf(environment.getRequiredProperty("db.maxIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(environment.getRequiredProperty("db.timeBeetwenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(environment.getRequiredProperty("db.minEvictableTimeMillis")));
        dataSource.setTestOnBorrow(Boolean.valueOf(environment.getRequiredProperty("db.testOnBorrow")));
        dataSource.setValidationQuery(environment.getRequiredProperty("db.validationQuery"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityFactoryBean().getObject());

        return manager;
    }
}
