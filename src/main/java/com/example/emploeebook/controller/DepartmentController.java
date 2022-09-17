package com.example.emploeebook.controller;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.model.Employee;
import com.example.emploeebook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

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
    public Employee minSalaryInDepartment( @RequestParam(defaultValue="0") int departmentID) throws DataEntryError {
        return departmentService.minSalaryInDepartment(departmentID);
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryInDepartment( @RequestParam(defaultValue="0")int departmentID) throws DataEntryError {
        return departmentService.maxSalaryInDepartment(departmentID);
    }

    @GetMapping("/all-in-department")
    public Collection<Employee> employeeListInDepartment( @RequestParam(defaultValue="0") int departmentID) throws DataEntryError {
        return departmentService.employeeListInDepartment(departmentID);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeeListSortedInDepartment() {
        return departmentService.employeeListSortedInDepartment();
    }

    @ExceptionHandler(DataEntryError.class)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}
