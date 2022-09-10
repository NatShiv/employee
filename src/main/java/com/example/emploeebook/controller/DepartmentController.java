package com.example.emploeebook.controller;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.model.Employee;
import com.example.emploeebook.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/min-salary")
    public Employee minSalaryInDepartment(@RequestParam(required = false) int departmentID) throws DataEntryError {
        return departmentService.minSalaryInDepartment(departmentID);
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryInDepartment(int departmentID) throws DataEntryError {
        return departmentService.maxSalaryInDepartment(departmentID);
    }

    @GetMapping("/all-in-department")
    public Collection<Employee> employeeListInDepartment(@RequestParam(required = false) int departmentID) throws DataEntryError {
        return departmentService.employeeListInDepartment(departmentID);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeeListSortedInDepartment() {
        return departmentService.employeeListSortedInDepartment();
    }
}
