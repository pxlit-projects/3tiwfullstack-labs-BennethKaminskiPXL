package be.pxl.services.organizationservice;

import be.pxl.services.organizationservice.domain.Organization;
import be.pxl.services.organizationservice.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class OrganizationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private OrganizationRepository organizationRepository;


    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void RegisterMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    public void GetOrganizationByIdTest() throws Exception {
        Organization organization = Organization.builder()
                .id(1L)
                .name("Cola")
                .address("fantaweg")
                .build();
        organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cola"));

    }
}
