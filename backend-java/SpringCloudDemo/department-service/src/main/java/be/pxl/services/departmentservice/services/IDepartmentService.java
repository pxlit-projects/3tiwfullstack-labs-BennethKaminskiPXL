package be.pxl.services.departmentservice.services;



import be.pxl.services.departmentservice.domain.dto.DepartmentRequest;
import be.pxl.services.departmentservice.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(long id);

    List<DepartmentResponse> findAllDepartments();

    List<DepartmentResponse> findByOrganizationId(long id);

    List<DepartmentResponse> findByOrganizationIdWithEployees(long organizationId);
}
