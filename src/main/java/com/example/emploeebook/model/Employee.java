package com.example.emploeebook.model;

import com.example.emploeebook.customException.DataEntryError;
import org.apache.commons.lang3.StringUtils;

public class Employee implements Comparable<Employee> {
    private final String firstName;
    private final String lastName;
    private String fullName;
    private double salary;
    private int department;

    public Employee(String firstName, String lastName, double salary, int department) {
        this(firstName, lastName);
        this.salary = salary;
        this.department = department;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = transform(firstName);
        this.lastName = transform(lastName);
        setFullName(firstName, lastName);
    }

    public int getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    public String fullName() {
        return fullName;
    }

    public void setFullName(String firstName, String lastName) {
        this.fullName = transform(firstName) + " " + transform(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String transform(String string) {
        return StringUtils.capitalize(string.toLowerCase());
    }

    public static void validateString(String string) throws DataEntryError {
        if (!StringUtils.isAlpha(string)) {
            throw new DataEntryError ("Имя и фамилия должны содержать только буквы. И должны быть введены в формате <span style=\"font-weight:bold\">?firstName=Имя&lastName=Фамилия</span>");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return fullName.equals(employee.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public String toString() {
        return "Employee: " +
                "firstName= " + firstName +
                ", lastName= " + lastName;
    }

    @Override
    public int compareTo(Employee o) {
        return (o.getDepartment() > this.department) ? -1 : 1;
    }
}
