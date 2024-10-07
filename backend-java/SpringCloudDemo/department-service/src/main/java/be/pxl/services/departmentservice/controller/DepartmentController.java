package be.pxl.services.departmentservice.controller;


import be.pxl.services.departmentservice.domain.dto.DepartmentRequest;
import be.pxl.services.departmentservice.domain.dto.DepartmentResponse;
import be.pxl.services.departmentservice.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest departmentRequest){
        departmentService.addDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getDepartmentById(@PathVariable long id){
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @GetMapping("/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findByOrganizationId(@PathVariable long organizationId){
        return departmentService.findByOrganizationId(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findByOrganizationWithEmployees (@PathVariable long organizationId){
        return departmentService.findByOrganizationIdWithEployees(organizationId);
    }





}
