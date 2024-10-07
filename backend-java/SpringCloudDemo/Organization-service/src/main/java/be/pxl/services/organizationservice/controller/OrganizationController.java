package be.pxl.services.organizationservice.controller;

import be.pxl.services.organizationservice.domain.dto.OrganizationResponse;
import be.pxl.services.organizationservice.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService organizationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findById(@PathVariable long id){
        return organizationService.findById(id);
    }

    @GetMapping("/{id}/with-departments")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartments(@PathVariable long id){
        return organizationService.findByIdWithDepartments(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(@PathVariable long id){
        return organizationService.findByIdWithDepartmentsAndEmployees(id);
    }

    @GetMapping("/{id}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithEmployees(@PathVariable long id){
        return  organizationService.findByIdWithEmployees(id);
    }



}
