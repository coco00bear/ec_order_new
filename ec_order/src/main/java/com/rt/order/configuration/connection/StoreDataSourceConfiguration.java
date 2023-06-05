package com.rt.order.configuration.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@PropertySource(value = "classpath:config/database.properties")
@PropertySource(value = "classpath:config/host.properties")
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.st")
public class StoreDataSourceConfiguration {

    Map<String, HikariConfig> ec;

    public Map<String, HikariConfig> getEc() {
        return ec;
    }

    public void setEc(Map<String, HikariConfig> ec) {
        this.ec = ec;
    }

    @Bean
    public Map<String, HikariConfig> ueMap(){
        return ec;
    }

    @Bean
    public Map<String,HikariDataSource> storeDataSource(@Qualifier("ueMap") final Map<String, HikariConfig> configMap) {
        Map<String, HikariDataSource> dataSourceMap = new HashMap<String, HikariDataSource>();
        for (String storeNo : configMap.keySet()) {
            dataSourceMap.put(storeNo,new HikariDataSource(configMap.get(storeNo)));
        }
        return dataSourceMap;
    }

    @Bean
    public Map<String, NamedParameterJdbcTemplate> storeJDBC(@Qualifier("storeDataSource") final Map<String,HikariDataSource> dataSourceMap) {
        Map<String, NamedParameterJdbcTemplate> jdbcTemplateMap = new HashMap<String, NamedParameterJdbcTemplate>();
        for (String storeNo : dataSourceMap.keySet()) {
            jdbcTemplateMap.put(storeNo, new NamedParameterJdbcTemplate(dataSourceMap.get(storeNo)));
        }
        return jdbcTemplateMap;
    }
}
