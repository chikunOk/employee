package com.example.employeemanagement.service;

import java.util.Map;

import com.example.employeemanagement.entity.EmployeeEntity;

import jakarta.validation.Valid;

public interface EmployeeService {

public	EmployeeEntity addEmployee(@Valid EmployeeEntity employee);

public Map<String, Object> calculateTaxDeduction(String employeeId);


}
