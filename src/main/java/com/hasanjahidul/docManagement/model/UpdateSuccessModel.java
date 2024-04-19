package com.hasanjahidul.docManagement.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Update success model.
 */
@Getter
@Setter
public class UpdateSuccessModel implements Serializable {

    private static final String defaultUpdateSuccessMessage = "Item has been updated successfully!";


    private final String message;

    private final Integer status;

    /**
     * Instantiates a new Update success model.
     */
    public UpdateSuccessModel() {
        this.status = 200;
        this.message = defaultUpdateSuccessMessage;
    }

    /**
     * Instantiates a new Update success model.
     *
     * @param message the message
     */
    public UpdateSuccessModel(String message) {
        this.status = 200;
        this.message = message;
    }

    /**
     * Instantiates a new Update success model.
     *
     * @param message the message
     * @param status  the status
     */
    public UpdateSuccessModel(String message, Integer status) {
        this.status = status;
        this.message = message;
    }
}
