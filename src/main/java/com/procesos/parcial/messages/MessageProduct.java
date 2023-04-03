package com.procesos.parcial.messages;

public enum MessageProduct {

    PRODUCT_LIST("All products have been listed"),
    PRODUCT_FIND("The product has been find"),
    PRODUCT_CREATED("The product has been create"),
    PRODUCT_IMPORT("All products has been import"),
    PRODUCT_UPDATED("The product has been update");

    private final String message;

    MessageProduct(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
