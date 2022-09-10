package com.example.emploeebook.service;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //HW2-8 Stream API и Optional

    public Employee minSalaryInDepartment(int department) throws DataEntryError {
        return employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new DataEntryError("В данном отделе никто не работает"));
    }

    public Employee maxSalaryInDepartment(int department) throws DataEntryError {
        return employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new DataEntryError("В данном отделе никто не работает"));
    }

    public Collection<Employee> employeeListInDepartment(int department) throws DataEntryError {
        return Optional.of(employeeService.getEmployeeBook().stream().filter((p) -> p.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList())).orElseThrow(() -> new DataEntryError("В данном отделе никто не работает"));
    }

    public Map<Integer, List<Employee>> employeeListSortedInDepartment() {
        return employeeService.getEmployeeBook().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

