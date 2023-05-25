package com.procesos.parcial.messages;

public enum MessageUser {

    USER_CREATED("The user has been create"),
    USER_UPDATED("The user has been update");

    private final String message;

    MessageUser(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
