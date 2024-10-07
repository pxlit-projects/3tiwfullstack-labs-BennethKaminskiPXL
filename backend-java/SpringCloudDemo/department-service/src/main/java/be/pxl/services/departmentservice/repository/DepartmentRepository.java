package be.pxl.services.departmentservice.repository;


import be.pxl.services.departmentservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findDepartmentsByOrganizationId(long id);
}
