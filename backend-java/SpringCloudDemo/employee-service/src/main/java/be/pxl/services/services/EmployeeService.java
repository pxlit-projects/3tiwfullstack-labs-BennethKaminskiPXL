package be.pxl.services.services;

import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.NotificationRequest;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.exception.EmployeeNotFoundException;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
         List<Employee> employees = employeeRepository.findAll();
         return employees.stream().map(employee -> mapToEmployeeResponse(employee)).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .age(employeeRequest.getAge())
                .name(employeeRequest.getName())
                .position(employeeRequest.getPosition())
                .build();
        employeeRepository.save(employee);

        //Notificationrequest gemaakt
        NotificationRequest notificationRequest =
                NotificationRequest.builder()
                        .message("Employee Created")
                        .sender("Tom")
                        .build();
//        //die sturen naar notificationservice
       notificationClient.sendNotification(notificationRequest);

    }

    @Override
    public EmployeeResponse getEmployeeById(long id) {
        Employee targetEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new  EmployeeNotFoundException("Employee not found with id : " + id));
        return mapToEmployeeResponse(targetEmployee);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(long departmentId) {
        List<Employee> targetEmployees = employeeRepository.findEmployeesByDepartmentId(departmentId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employees not found with department id : " + departmentId));
        if (targetEmployees.isEmpty()){
            throw  new EmployeeNotFoundException("Employees with department id not found. organization id : "+departmentId);
        }

        return targetEmployees.stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public List<EmployeeResponse> getEmployeesByOrganizationId(long id) {
        List<Employee> targetEmployees = employeeRepository.findEmployeesByOrganizationId(id)
                .orElseThrow(() ->new EmployeeNotFoundException("Employees with organization id not found. organization id : "+id));
        if (targetEmployees.isEmpty()){
            throw  new EmployeeNotFoundException("Employees with organization id not found. organization id : "+id);
        }
        return targetEmployees.stream().map(this::mapToEmployeeResponse).toList();
    }
}
