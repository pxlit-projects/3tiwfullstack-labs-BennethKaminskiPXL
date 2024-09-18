package be.pxl.services.services;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAllEmployees();

    void addEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse getEmployeeById(long id);

    List<EmployeeResponse> getEmployeesByDepartment(long departmentId);

    List<EmployeeResponse> getEmployeesByOrganizationId(long id);
}
