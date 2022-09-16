package com.example.emploeebook.service;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeNotFound;
import com.example.emploeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.emploeebook.model.Employee.validateNumber;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //HW2-8 Stream API и Optional

    public Employee minSalaryInDepartment(int department) throws DataEntryError {
        validateNumber(department);
        return employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFound("В данном отделе никто не работает"));
    }

    public Employee maxSalaryInDepartment(Integer department) throws DataEntryError {
        validateNumber(department);
        return employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFound("В данном отделе никто не работает"));
    }

    public List<Employee> employeeListInDepartment(int department) throws DataEntryError {
        validateNumber(department);
        return employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Integer, List<Employee>> employeeListSortedInDepartment() {
        return employeeService.getEmployeeBook().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

}

