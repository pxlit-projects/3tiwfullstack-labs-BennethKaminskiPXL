package be.pxl.services.departmentservice.services;


import be.pxl.services.departmentservice.Exception.DepartmentNotFoundException;
import be.pxl.services.departmentservice.domain.Department;
import be.pxl.services.departmentservice.domain.dto.DepartmentRequest;
import be.pxl.services.departmentservice.domain.dto.DepartmentResponse;
import be.pxl.services.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;


    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .id(departmentRequest.getId())
                .name(departmentRequest.getName())
                .position(departmentRequest.getPosition())
                .organizationId(departmentRequest.getOrganizationId())
                .employee(departmentRequest.getEmployee()).build();
        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse getDepartmentById(long id) {
        Department department =  departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("department not found with id: " + id));
        return mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentResponse> findAllDepartments() {
        return departmentRepository.findAll().stream().map(this::mapToDepartmentDto)
                .toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganizationId(long id) {
        List<Department> departmentList = departmentRepository.findDepartmentsByOrganizationId(id);
        if (departmentList.isEmpty()){
            throw new DepartmentNotFoundException("departments not found with id : "+ id);
        }
        return departmentList.stream().map(this::mapToDepartmentDto).toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganizationIdWithEployees(long organizationId) {
        //TODO niet klaar with employees
        List<Department> departmentList = departmentRepository.findDepartmentsByOrganizationId(organizationId);
        if (departmentList.isEmpty()){
            throw new DepartmentNotFoundException("departments not found with id : "+ organizationId);
        }
        return departmentList.stream().map(this::mapToDepartmentDto).toList();
    }

    public DepartmentResponse mapToDepartmentDto(Department department){
        DepartmentResponse response = DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .employee(department.getEmployee())
                .name(department.getName())
                .position(department.getPosition())
                .build();
        return response;
    }


}
