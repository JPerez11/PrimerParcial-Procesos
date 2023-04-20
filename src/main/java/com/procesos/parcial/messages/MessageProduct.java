package com.procesos.parcial.messages;

/**
 * Enum to create constant variables
 */
public enum MessageProduct {

    PRODUCT_LIST("All products have been listed"),
    PRODUCT_FIND("The product has been find"),
    PRODUCT_CREATED("The product has been create"),
    PRODUCT_IMPORT("All products has been import"),
    PRODUCT_UPDATED("The product has been update");

    /**
     * Class attribute.
     */
    private final String message;

    /**
     * Enum Constructor.
     * @param message Message to the customer.
     */
    MessageProduct(String message) {
        this.message = message;
    }

    /**
     * Method get to obtain message.
     * @return The message of the enum.
     */
    public String getMessage() {
        return this.message;
    }
}
