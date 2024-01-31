package com.example.employeemanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

	@PostMapping("/add")
    public ResponseEntity<EmployeeEntity> addEmployee(@Valid @RequestBody EmployeeEntity employee) {
        try {
            EmployeeEntity createdEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.ok(createdEmployee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/tax-deduction/{employeeId}")
    public ResponseEntity<Map<String, Object>> getTaxDeduction(@PathVariable String employeeId) {
        try {
            Map<String, Object> taxResult = employeeService.calculateTaxDeduction(employeeId);
            return ResponseEntity.ok(taxResult);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createErrorMap(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMap("Error calculating tax deduction: " + e.getMessage()));
        }
    }

    private Map<String, Object> createErrorMap(String errorMessage) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", errorMessage);
        return errorMap;
    }
	
}
