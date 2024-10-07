package be.pxl.services.organizationservice.repository;

import be.pxl.services.organizationservice.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
