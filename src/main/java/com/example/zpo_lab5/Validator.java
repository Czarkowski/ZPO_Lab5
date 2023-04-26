package com.example.zpo_lab5;

public interface Validator {
    void validate(String value);
    boolean isValid();
    String getMessage();
}
