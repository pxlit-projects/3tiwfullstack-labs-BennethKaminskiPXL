package be.pxl.services.services;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(long id);

    List<DepartmentResponse> findAllDepartments();

    List<DepartmentResponse> findByOrganizationId(long id);

    List<DepartmentResponse> findByOrganizationIdWithEployees(long organizationId);
}
