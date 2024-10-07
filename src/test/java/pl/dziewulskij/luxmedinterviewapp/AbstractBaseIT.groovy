package pl.dziewulskij.luxmedinterviewapp

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ActiveProfiles('test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:test-properties.yml"])
@ContextConfiguration(initializers = PostgreSQLContainerConfig.PostgreSQLInitializer)
abstract class AbstractBaseIT extends Specification {

    MockMvc mockMvc
    ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    WebApplicationContext webApplicationContext

    def setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

}
