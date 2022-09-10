package com.example.emploeebook.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataEntryError extends Exception {
    public DataEntryError(String message) {
        super(message);
    }


}
