package pl.dziewulskij.luxmedinterviewapp

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class PostgreSQLContainerConfig {

    private static final String IMAGE_VERSION = 'postgres:15.2'
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(IMAGE_VERSION)

    static class PostgreSQLInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start()

            TestPropertyValues.of(
                    'spring.datasource.url=' + postgreSQLContainer.getJdbcUrl(),
                    'spring.datasource.username=' + postgreSQLContainer.getUsername(),
                    'spring.datasource.password=' + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment())

            Runtime.getRuntime().addShutdownHook(new Thread(postgreSQLContainer::close))
        }
    }

}
