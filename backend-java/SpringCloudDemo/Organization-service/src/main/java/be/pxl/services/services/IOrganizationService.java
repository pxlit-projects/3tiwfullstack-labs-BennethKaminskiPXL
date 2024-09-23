package be.pxl.services.services;

import be.pxl.services.domain.dto.OrganizationResponse;

public interface IOrganizationService {
    OrganizationResponse findById(long id);

    OrganizationResponse findByIdWithDepartments(long id);

    OrganizationResponse findByIdWithDepartmentsAndEmployees(long id);

    OrganizationResponse findByIdWithEmployees(long id);
}
