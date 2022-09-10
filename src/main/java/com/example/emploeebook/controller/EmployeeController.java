package com.example.emploeebook.controller;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeAlreadyAdded;
import com.example.emploeebook.customException.EmployeeNotFound;
import org.springframework.web.bind.annotation.*;
import com.example.emploeebook.model.Employee;
import com.example.emploeebook.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> getEmployeeBook() {
        return employeeService.getEmployeeBook();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(required = false, name = "firstName") String firstName,
                        @RequestParam(required = false, name = "lastName") String lastName,
                        @RequestParam(required = false, name = "salary") double salary,
                        @RequestParam(required = false, name = "department") int department) throws DataEntryError {
        Employee.validateString(firstName);
        Employee.validateString(lastName);

        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) throws DataEntryError {
        Employee.validateString(firstName);
        Employee.validateString(lastName);

        return employeeService.find(firstName, lastName);
    }


    @GetMapping("/remove")
    public Employee remove(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) throws DataEntryError {
        Employee.validateString(firstName);
        Employee.validateString(lastName);

        return employeeService.remove(firstName, lastName);

    }


    @ExceptionHandler({DataEntryError.class, EmployeeAlreadyAdded.class, EmployeeNotFound.class})
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
