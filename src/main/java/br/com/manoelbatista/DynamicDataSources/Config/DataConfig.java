/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Config;

import br.com.manoelbatista.DynamicDataSources.Exception.DataSourceConfigErrorException;
import br.com.manoelbatista.DynamicDataSources.Model.Support.DataSetInfo;
import br.com.manoelbatista.DynamicDataSources.Model.Support.MultiRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 * @see
 * https://riteshshergill.medium.com/multi-database-connections-with-spring-boot-80d66f97d39f
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager",
        basePackages = "br.com.manoelbatista.DynamicDataSources"
)
public class DataConfig {

    protected static final Map<Object, Object> SOURCES = new HashMap<>();
    private static final MultiRoutingDataSource mrds = new MultiRoutingDataSource();
    //Primary data Source
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/test1");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("test");
        ds.setPassword("5432");

        return ds;
    }

    @Bean
    public DataSource multiDataSources() {

        DataSource dataSource = dataSource();
        SOURCES.put("primaryDB", dataSource());        
        mrds.setDefaultTargetDataSource(dataSource);
        mrds.setTargetDataSources(SOURCES);
        
        return mrds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(multiDataSources());
        lcemfb.setPackagesToScan("br.com.manoelbatista.DynamicDataSources.Model.Entity");
        lcemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        lcemfb.setJpaProperties(hibernateProperties());

        return lcemfb;
    }

    @Bean
    public PlatformTransactionManager multiTransactionManager() {
        
        return new JpaTransactionManager(multiEntityManager().getObject());
    }

    //add hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);

        return properties;
    }

    public static boolean KeyExists(String key) {
        return SOURCES.containsKey(key);
    }

    /**
     * this function validate and insert new data source
     * the AbstractRoutingDataSource.afterPropertiesSet() reload the connections.
     * @param key
     * @param dsi 
     */
    public static void AddNewDataSource(String key, DataSetInfo dsi) {
        if (SOURCES.containsKey(key)) {
            throw new DataSourceConfigErrorException("The data source: %s already exists".formatted(key));
        }

        if (dsi == null) {
            throw new DataSourceConfigErrorException("The data source: %s is null".formatted(key));
        }

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(dsi.getUrl());
        ds.setDriverClassName(dsi.getDriverClass());
        ds.setUsername(dsi.getUser());
        ds.setPassword(dsi.getPassword());
        
        SOURCES.put(key, ds);
        mrds.afterPropertiesSet();
    }

}
