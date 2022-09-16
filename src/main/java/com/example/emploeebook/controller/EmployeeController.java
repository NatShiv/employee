package com.example.emploeebook.controller;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeAlreadyAdded;
import com.example.emploeebook.customException.EmployeeNotFound;
import org.springframework.web.bind.annotation.*;
import com.example.emploeebook.model.Employee;
import com.example.emploeebook.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public List<Employee> getEmployeeBook() {
        return employeeService.getEmployeeBook();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(defaultValue = "", name = "firstName") String firstName,
                        @RequestParam(defaultValue = "", name = "lastName") String lastName,
                        @RequestParam(defaultValue = "0", name = "salary") double salary,
                        @RequestParam(defaultValue = "0", name = "department") int department) throws DataEntryError {

        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(defaultValue = "") String firstName,
                         @RequestParam(defaultValue = "") String lastName) throws DataEntryError {

        return employeeService.find(firstName, lastName);
    }


    @GetMapping("/remove")
    public Employee remove(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) throws DataEntryError {

        return employeeService.remove(firstName, lastName);

    }


    @ExceptionHandler({DataEntryError.class, EmployeeAlreadyAdded.class, EmployeeNotFound.class})
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
