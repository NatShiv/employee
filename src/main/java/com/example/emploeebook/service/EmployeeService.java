package com.example.emploeebook.service;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeAlreadyAdded;
import com.example.emploeebook.customException.EmployeeNotFound;
import com.example.emploeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<String, Employee> employeeBook = new HashMap<>();


    public Employee add(String firstName, String lastName, double salary, int department) throws DataEntryError {
        Employee employee = new Employee(firstName, lastName, salary, department);
        String key = Employee.fullName(firstName, lastName);
        if (employeeBook.containsKey(key)) {
            throw new EmployeeAlreadyAdded("Сотрудник с такими параметрами уже сществует.");
        } else {
            employeeBook.put(key, employee);
            return employee;
        }
    }

    public Employee find(String firstName, String lastName) throws DataEntryError {
       String key = Employee.fullName(firstName, lastName);

        if (employeeBook.containsKey(key)) {
            return employeeBook.get(key);
        } else {
            throw new EmployeeNotFound("Сотрудник с такими параметрами не найден.");
        }
    }

    public Employee remove(String firstName, String lastName) throws DataEntryError {
        String key = Employee.fullName(firstName, lastName);

        if (employeeBook.containsKey(key)) {
            Employee employee= employeeBook.get(key);
            employeeBook.remove(key);
            return employee;
        } else {
            throw new EmployeeNotFound("Сотрудник с такими параметрами не найден.");

        }
    }

    public List<Employee> getEmployeeBook() {

        return new ArrayList<>(employeeBook.values());
    }

}