package pl.dziewulskij.luxmedinterviewapp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static java.nio.charset.StandardCharsets.UTF_8

@ActiveProfiles('test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:test-properties.yml"])
@ContextConfiguration(initializers = PostgreSQLContainerConfig.PostgreSQLInitializer)
abstract class AbstractBaseIT extends AbstractRepository {

    MockMvc mockMvc
    ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    WebApplicationContext webApplicationContext

    def setup() {
        objectMapper.registerModule(new JavaTimeModule())
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    def cleanup() {
        departmentJpaRepository.deleteAll()
        companyJpaRepository.deleteAll()
    }

    protected <T> T readValueAsUtf8Content(MvcResult mvcResult, Class<T> tClazz) {
        return objectMapper.readValue(mvcResult.response.getContentAsString(UTF_8), tClazz)
    }

}
