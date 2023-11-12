package io.orionbs.resilient.integration.database.reactive.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "io.orionbs.resilient.database.reactive.repository")
@Profile(value = "test")
public class TestConfigurationReactiveDatabase {

    @Bean
    public ConnectionFactoryOptions connectionFactoryOptions() {
        return ConnectionFactoryOptions
                .builder()
                .option(ConnectionFactoryOptions.DRIVER, "h2")
                .option(ConnectionFactoryOptions.PROTOCOL, "file")
                .option(ConnectionFactoryOptions.USER, "sa")
                .option(ConnectionFactoryOptions.PASSWORD, "")
                .option(ConnectionFactoryOptions.DATABASE, "./h2/test")
                .build();
    }

    @Bean
    public ConnectionFactory connectionFactory(ConnectionFactoryOptions connectionFactoryOptions) {
        return ConnectionFactories.get(connectionFactoryOptions);
    }

}
