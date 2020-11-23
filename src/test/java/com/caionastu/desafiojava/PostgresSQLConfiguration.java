package com.caionastu.desafiojava;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Component
class PostgresSQLConfiguration {

    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13-alpine");

    static {
        postgreSQLContainer.start();
    }

    @Bean
    public DataSource dataSource() {
//       String jdbcURL = String.format("jdbc:postgresql://%s:%s/%s", postgreSQLContainer.getContainerIpAddress(), postgreSQLContainer.getFirstMappedPort(), postgreSQLContainer.getDatabaseName());
        return DataSourceBuilder.create()
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .build();
    }
}
