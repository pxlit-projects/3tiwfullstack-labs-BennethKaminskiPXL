package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.exception.OrganizationNotFoundException;
import be.pxl.services.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse findById(long id) {
        var org =  organizationRepository.findById(id).orElseThrow(()-> new OrganizationNotFoundException("organization not found with id : "+id));
        return mapToResponse(org);
    }

    @Override
    public OrganizationResponse findByIdWithDepartments(long id) {
        //TODO not implemented with department
        var org =  organizationRepository.findById(id).orElseThrow(()-> new OrganizationNotFoundException("organization not found with id : "+id));
        return mapToResponse(org);
    }

    @Override
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(long id) {
        //TODO not implemented with department and employees
        var org =  organizationRepository.findById(id).orElseThrow(()-> new OrganizationNotFoundException("organization not found with id : "+id));
        return mapToResponse(org);
    }

    @Override
    public OrganizationResponse findByIdWithEmployees(long id) {
        //TODO not implemented with employees
        var org =  organizationRepository.findById(id).orElseThrow(()-> new OrganizationNotFoundException("organization not found with id : "+id));
        return mapToResponse(org);
    }

    public OrganizationResponse mapToResponse(Organization organization){
        return OrganizationResponse.builder().name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .employees(organization.getEmployees())
                .id(organization.getId())
                .build();
    }
}
