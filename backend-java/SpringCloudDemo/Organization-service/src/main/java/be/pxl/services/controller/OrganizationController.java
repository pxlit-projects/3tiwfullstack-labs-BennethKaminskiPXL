package be.pxl.services.controller;

import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.services.IOrganizationService;
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
    public OrganizationResponse findById(@RequestParam long id){
        return organizationService.findById(id);
    }

    @GetMapping("/{id}/with-departments")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartments(@RequestParam long id){
        return organizationService.findByIdWithDepartments(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(@RequestParam long id){
        return organizationService.findByIdWithDepartmentsAndEmployees(id);
    }

    @GetMapping("/{id}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithEmployees(@RequestParam long id){
        return  organizationService.findByIdWithEmployees(id);
    }



}
