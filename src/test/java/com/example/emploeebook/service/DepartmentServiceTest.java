package com.example.emploeebook.service;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeNotFound;
import com.example.emploeebook.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Array;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService = new EmployeeService();

    @InjectMocks
    private DepartmentService departmentService;

    List<Employee> expectedEmployeeBook;


    @BeforeEach
    void generateEmployeeBook() throws DataEntryError {
        expectedEmployeeBook = List.of(new Employee("John", "Bell", 50000, 1),
                new Employee("Bella", "Bell", 60000, 3),
                new Employee("Bella", "Luis", 50000, 2),
                new Employee("Bella", "Adams", 70000, 3),
                new Employee("John", "Luis", 60000, 1),
                new Employee("Bella", "Samuel", 65000, 2),
                new Employee("John", "Samuel", 70000, 1));
        Mockito.when(employeeService.getEmployeeBook()).thenReturn(expectedEmployeeBook);
    }

//    static Stream<Arguments> provideParams() {
//        return Stream.of(
//                Arguments.of("John", "Bell", 50000, 1),
//                Arguments.of("Bella", "Luis", 50000, 2),
//                Arguments.of("Bella", "Bell", 60000, 3)
//        );
//    }


    @Test
    void minSalaryInDepartment() {
        Assertions.assertEquals(new Employee("John", "Bell", 50000, 1), departmentService.minSalaryInDepartment(1));
    }

    @Test
    void maxSalaryInDepartment() {
        Assertions.assertEquals(new Employee("Bella", "Adams", 70000, 3), departmentService.maxSalaryInDepartment(3));
    }

    @Test
    void employeeListInDepartment() {
        Assertions.assertArrayEquals(List.of(new Employee("John", "Bell", 50000, 1),
                        new Employee("John", "Luis", 60000, 1),
                        new Employee("John", "Samuel", 70000, 1)).toArray(),
                departmentService.employeeListInDepartment(1).toArray());
    }

    @Test
    void employeeListInEmptyDepartment() {

        Array[] e = new Array[0];
        Assertions.assertArrayEquals(e,
                departmentService.employeeListInDepartment(5).toArray());
    }

    @Test
    void maxSalaryInDepartmentError() {
        Assertions.assertThrows(EmployeeNotFound.class, () -> departmentService.maxSalaryInDepartment(5));
    }


    @Test
    void minSalaryInDepartmentError() {
        Assertions.assertThrows(EmployeeNotFound.class, () -> departmentService.minSalaryInDepartment(5));
    }

    @Test
    void employeeListSortedInDepartment() {
        org.assertj.core.api.Assertions.assertThat(departmentService.employeeListSortedInDepartment()).containsAllEntriesOf(
                Map.of(1, List.of(new Employee("John", "Bell", 50000, 1),
                                new Employee("John", "Luis", 60000, 1),
                                new Employee("John", "Samuel", 70000, 1)),
                        2, List.of(new Employee("Bella", "Luis", 50000, 2),
                                new Employee("Bella", "Samuel", 65000, 2)),
                        3, List.of(new Employee("Bella", "Bell", 60000, 3),
                                new Employee("Bella", "Adams", 70000, 3))));

    }
}