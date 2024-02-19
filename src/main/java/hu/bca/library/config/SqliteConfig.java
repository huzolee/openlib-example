package hu.bca.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("sqlite")
@PropertySource("classpath:persistence-sqlite.properties")
class SqliteConfig {
}
