package be.pxl.services.departmentservice.domain.dto;

import be.pxl.services.departmentservice.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private Long id;
    private long organizationId;
    private String name;
    private List<Employee> employee;
    private String position;
}