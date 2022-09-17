package com.example.emploeebook.service;

import com.example.emploeebook.customException.DataEntryError;
import com.example.emploeebook.customException.EmployeeAlreadyAdded;
import com.example.emploeebook.customException.EmployeeNotFound;
import com.example.emploeebook.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    Map<String, Employee> expectedEmployeeBook = new HashMap<>();

    public static final String FIRST_NAME = "Bella";
    public static final String LAST_NAME = "Johns";

    static Stream<Arguments> provideParamsForAdd() {
        return Stream.of(
                Arguments.of("Bella", "Johns", 50000, 1),
                Arguments.of("BeLLa", "Johns", 50000, 1),
                Arguments.of("Bella", "JoHNs", 50000, 1)
        );
    }

    static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of("John", "Bell", 50000, 1),
                Arguments.of("JOhN", "Bell", 50000, 1),
                Arguments.of("John", "BEll", 50000, 1)
        );
    }

    static Stream<Arguments> provideParamsForError() {
        return Stream.of(
                Arguments.of("John@", "Bell"),
                Arguments.of("JOhN", "Bel l"),
                Arguments.of("", "BEll"),
                Arguments.of("John", ""));
    }


    static Stream<Arguments> provideParamsForAddError() {
        return Stream.of(
                Arguments.of("JOhN@", "Bell", 50000, 1),
                Arguments.of("John", "BE ll", 50000, 1),
                Arguments.of("John", "Bell", -50000, 1),
                Arguments.of("JOhN", "Bell", 50000, -1),
                Arguments.of("", "BEll", 50000, 1),
                Arguments.of("John", "", 50000, 1),
                Arguments.of("John", "Bell", 0, 1),
                Arguments.of("JOhN", "Bell", 50000, 0)
        );
    }


    @BeforeEach
    void generateEmployeeBook() throws DataEntryError {
        expectedEmployeeBook.put("John" + " " + "Bell", new Employee("John", "Bell", 50000, 1));
        expectedEmployeeBook.put("Bella" + " " + "Bell", new Employee("Bella", "Bell", 60000, 3));
        expectedEmployeeBook.put("Bella" + " " + "Luis", new Employee("Bella", "Luis", 50000, 2));
        expectedEmployeeBook.put("Bella" + " " + "Adams", new Employee("Bella", "Adams", 70000, 3));
        expectedEmployeeBook.put("John" + " " + "Luis", new Employee("John", "Luis", 60000, 1));
        expectedEmployeeBook.put("Bella" + " " + "Samuel", new Employee("Bella", "Samuel", 65000, 2));
        expectedEmployeeBook.put("John" + " " + "Samuel", new Employee("John", "Samuel", 70000, 1));
        employeeService.add("John", "Bell", 50000, 1);
        employeeService.add("Bella", "Bell", 60000, 3);
        employeeService.add("Bella", "Luis", 50000, 2);
        employeeService.add("Bella", "Adams", 70000, 3);
        employeeService.add("John", "Luis", 60000, 1);
        employeeService.add("Bella", "Samuel", 65000, 2);
        employeeService.add("John", "Samuel", 70000, 1);

    }


    @MethodSource("provideParamsForAdd")
    @ParameterizedTest
    void addEmployee(String firstName, String lastName, double salary, int department) throws DataEntryError {

        Employee employee = new Employee(FIRST_NAME, LAST_NAME, salary, department);
        Assertions.assertEquals(employee, employeeService.add(firstName, lastName, salary, department));
    }

    @Test
    void getEmployeeBook() {
        Assertions.assertArrayEquals(Collections.unmodifiableCollection(expectedEmployeeBook.values()).toArray(), employeeService.getEmployeeBook().toArray());
    }

    @MethodSource("provideParams")
    @ParameterizedTest
    void find(String firstName, String lastName, double salary, int department) throws DataEntryError {
        Employee employee = new Employee(firstName, lastName, salary, department);
        Assertions.assertEquals(employee, employeeService.find(firstName, lastName));
    }

    @MethodSource("provideParams")
    @ParameterizedTest
    void remove(String firstName, String lastName, double salary, int department) throws DataEntryError {
        Employee employee = new Employee(firstName, lastName, salary, department);
        Assertions.assertEquals(employee, employeeService.remove(firstName, lastName));
    }


    @MethodSource("provideParams")
    @ParameterizedTest
  void addAlreadyAdded(String firstName, String lastName, double salary, int department) {
       Assertions.assertThrows(EmployeeAlreadyAdded.class, () -> employeeService.add(firstName, lastName, salary, department));
   }

    @Test
    void findEmployeeNotFoundError() {
        Assertions.assertThrows(EmployeeNotFound.class, () -> employeeService.find(FIRST_NAME, LAST_NAME));
    }

    @Test
    void removeEmployeeNotFoundError() {
        Assertions.assertThrows(EmployeeNotFound.class, () -> employeeService.remove(FIRST_NAME, LAST_NAME));
    }

    @MethodSource("provideParamsForAddError")
    @ParameterizedTest
    void addDataEntryError(String firstName, String lastName, double salary, int department) {
        Assertions.assertThrows(DataEntryError.class, () -> employeeService.add(firstName, lastName, salary, department));
    }

    @MethodSource("provideParamsForError")
    @ParameterizedTest
    void findDataEntryError(String firstName, String lastName) {
        Assertions.assertThrows(DataEntryError.class, () -> employeeService.find(firstName, lastName));
    }

    @MethodSource("provideParamsForError")
    @ParameterizedTest
    void removeDataEntryError(String firstName, String lastName) {
        Assertions.assertThrows(DataEntryError.class, () -> employeeService.remove(firstName, lastName));
    }

}