package io.orionbs.resilient.database.reactive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "io.orionbs.resilient.database.reactive.repository")
public class ConfigurationReactiveDatabase {
}
