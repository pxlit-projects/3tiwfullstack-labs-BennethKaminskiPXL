package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data //zorgt voor setters en getters, equals en tostring
@Builder // om object te creëeren
@AllArgsConstructor //project wordt van alle constructors voorzien
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long organizationId; //geen relaties omdat microservices zijn
    private long departmentId;
    private String name;
    private int age;
    private String position;


}
