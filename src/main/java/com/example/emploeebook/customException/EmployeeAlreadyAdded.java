package com.example.emploeebook.customException;

public class EmployeeAlreadyAdded extends RuntimeException{
    public EmployeeAlreadyAdded(String message) {
        super(message);
    }
}
