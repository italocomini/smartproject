package com.github.italocomini.smartproject.handlers;

class ValidationError {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
}