package be.pxl.services.departmentservice;

import be.pxl.services.departmentservice.domain.Department;
import be.pxl.services.departmentservice.repository.DepartmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class DepartmentTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static MySQLContainer mySQLContainer =
            new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void RegisterSQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.user",mySQLContainer::getUsername);
        registry.add("spring.datasource.password",mySQLContainer::getPassword);
    }


    @Test
    public void createDepartment() throws Exception {
        Department department = Department.builder()
                .id(1L)
                .name("Digital")
                .organizationId(1)
                .position("?")
                .build();
        departmentRepository.save(department);

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1,departmentRepository.findAll().size());
    }

    @Test
    public void getDepartmentById() throws Exception {
        Department department = Department.builder()
                .id(1L)
                .name("Digital")
                .organizationId(1)
                .position("?")
                .build();
        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Digital"));

    }

    @Test
    public void getDepartments() throws Exception {
        Department department = Department.builder()
                .id(1L)
                .name("Digital")
                .organizationId(1)
                .position("?")
                .build();

        Department department2 = Department.builder()
                .id(2L)
                .name("Stat")
                .organizationId(1)
                .position("?")
                .build();

        departmentRepository.save(department);
        departmentRepository.save(department2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Digital"))
                .andExpect(jsonPath("[1].name").value("Stat"));
    }

    @Test
    public void findByOrganizationIdTest() throws Exception {
        Department department = Department.builder()
                .id(1L)
                .name("Digital")
                .organizationId(1)
                .position("?")
                .build();
        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Digital"));

    }
    //TODO withemployees


}
