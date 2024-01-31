package com.example.employeemanagement.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is mandatory")
    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String email;

    @ElementCollection
    @Size(min = 1, message = "At least one phone number is required")
    @Column(name = "phone_number")
    private List<String> phoneNumbers;

    @PastOrPresent(message = "Date of Joining must be in the past or present")
    @NotNull(message = "Date of Joining is mandatory")
    @Column(name = "doj")
    private LocalDate doj;

    @Positive(message = "Salary must be a positive value")
    @Column(name = "salary")
    private double salary;
	

}
