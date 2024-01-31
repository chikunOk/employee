package com.example.employeemanagement.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.repository.EmployeeRepo;
import com.example.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    private EmployeeRepo employeeRepo;

	@Override
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        
        return employeeRepo.save(employee);
    }

  @Override
    public Map<String, Object> calculateTaxDeduction(String employeeId) {
        EmployeeEntity employee = employeeRepo.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found for ID: " + employeeId);
        }

        double yearlySalary = calculateYearlySalary(employee);
        double taxAmount = calculateTaxAmount(yearlySalary);
        double cessAmount = calculateCessAmount(yearlySalary);

        Map<String, Object> taxResult = new HashMap<>();
        taxResult.put("employeeId", employee.getEmployeeId());
        taxResult.put("firstName", employee.getFirstName());
        taxResult.put("lastName", employee.getLastName());
        taxResult.put("yearlySalary", yearlySalary);
        taxResult.put("taxAmount", taxAmount);
        taxResult.put("cessAmount", cessAmount);

        return taxResult;
    }

    private double calculateYearlySalary(EmployeeEntity employee) {
        return employee.getSalary() * 12;
    }

    private double calculateTaxAmount(double yearlySalary) {
        
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary <= 500000) {
            return 0.05 * (yearlySalary - 250000);
        } else if (yearlySalary <= 1000000) {
            return 0.1 * (yearlySalary - 500000) + 12500;
        } else {
            return 0.2 * (yearlySalary - 1000000) + 125000;
        }
    }

    private double calculateCessAmount(double yearlySalary) {
        
        if (yearlySalary > 2500000) {
            return 0.02 * (yearlySalary - 2500000);
        } else {
            return 0;
        }
    }
}
