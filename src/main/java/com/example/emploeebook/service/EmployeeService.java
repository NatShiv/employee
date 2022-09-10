package com.example.emploeebook.service;

import com.example.emploeebook.customException.EmployeeAlreadyAdded;
import com.example.emploeebook.customException.EmployeeNotFound;
import com.example.emploeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService {
    private final Map<String, Employee> employeeBook;

    public EmployeeService() {
        this.employeeBook = new HashMap<>();
    }

    public Employee add(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        if (employeeBook.containsKey(employee.fullName())) {
            throw new EmployeeAlreadyAdded("Сотрудник с такими параметрами уже сществует.");
        } else {
            employeeBook.put(employee.fullName(), employee);
            return employee;
        }
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeeBook.containsKey(employee.fullName())) {
            return employeeBook.get(employee.fullName());
        } else {
            throw new EmployeeNotFound("Сотрудник с такими параметрами не найден.");
        }
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeeBook.containsKey(employee.fullName())) {
            employeeBook.remove(employee.fullName());
            return employee;
        } else {
            throw new EmployeeNotFound("Сотрудник с такими параметрами не найден.");

        }
    }

    public Collection<Employee> getEmployeeBook() {

        return Collections.unmodifiableCollection(employeeBook.values());
    }

}