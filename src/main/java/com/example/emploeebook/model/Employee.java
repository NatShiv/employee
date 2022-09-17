package com.example.emploeebook.model;

import com.example.emploeebook.customException.DataEntryError;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final double salary;
    private final int department;


    public Employee(String firstName, String lastName, double salary, int department) throws DataEntryError {
        validateNumber(salary);
        validateNumber(department);

        this.firstName = validateString(firstName);
        this.lastName = validateString(lastName);
        this.fullName = fullName(this.firstName, this.lastName);

        this.salary = salary;
        this.department = department;
    }

    public static String fullName(String firstName, String lastName) throws DataEntryError {
        return validateString(firstName) + " " + validateString(lastName);
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    private static String transform(String string) {
        return StringUtils.capitalize(string.toLowerCase());
    }


    public static String validateString(String string) throws DataEntryError {
        if (StringUtils.isAlpha(string)) {
            return transform(string);
        } else {
            throw new DataEntryError("Имя и фамилия должны содержать только буквы. <br />" +
                    " И должны быть введены в формате <span style=\"font-weight:bold\">?firstName=Имя&lastName=Фамилия</span>. <br />" +
                    "Двойное имя или фамилия пишется через тире");

        }
    }

    public static void validateNumber(double num) throws DataEntryError {
        if (num <= 0) {
            throw new DataEntryError("Зарплата и номер отдела не могут быть отрицательными либо равными 0.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (Double.compare(employee.salary, salary) != 0) return false;
        if (department != employee.department) return false;
        if (!Objects.equals(firstName, employee.firstName)) return false;
        return Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + department;
        return result;
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
