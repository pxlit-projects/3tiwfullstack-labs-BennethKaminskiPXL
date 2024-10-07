package be.pxl.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.repository.EmployeeRepository;
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
@Testcontainers //voor de testcontainers
@AutoConfigureMockMvc //mockmvc
public class EmployeeTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    //jackson package
    //om van de Employee een employeestring te maken
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Container //voor de testcontainer
    private static MySQLContainer sqlContainer =
            new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource //voor de testcontainer
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username",sqlContainer::getUsername);
        registry.add("spring.datasource.password",sqlContainer::getPassword);
    }

    //junit jupiter annotatie hier gebruiken
    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Jan")
                .position("student")
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isCreated());

        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void getAllEmployees() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Jan")
                .position("student")
                .build();
        employeeRepository.save(employee);

        Employee secondEmployee = Employee.builder()
                .age(24)
                .name("Bob")
                .position("student")
                .build();
        employeeRepository.save(secondEmployee);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jan"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    public void getEmployeeByDepartment() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Jan")
                .position("student")
                .departmentId(1)
                .build();
        employeeRepository.save(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Jan"));
    }

    @Test
    public void getEmployeeByOrganizationId() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("Jan")
                .position("student")
                .departmentId(1)
                .organizationId(2)
                .build();
        employeeRepository.save(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/organization/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Jan"));

    }
}