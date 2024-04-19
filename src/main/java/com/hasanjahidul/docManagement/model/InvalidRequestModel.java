package com.hasanjahidul.docManagement.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Invalid request model.
 */
@Getter
@Setter
public class InvalidRequestModel<T> implements Serializable {
    private transient T message;

    private final Integer status;

    private static final String defaultInvalidRequestMessage = "Something went wrong, please try again later!";

    /**
     * Instantiates a new Invalid request model.
     */
    public InvalidRequestModel() {
        this.message = (T) defaultInvalidRequestMessage;
        this.status = 400;
    }

    /**
     * Instantiates a new Invalid request model.
     *
     * @param message the message
     * @param status  the status
     */
    public InvalidRequestModel(T message, Integer status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Instantiates a new Invalid request model.
     *
     * @param message the message
     */
    public InvalidRequestModel(T message) {
        this.message = message;
        this.status = 400;
    }
}

