package com.hasanjahidul.docManagement.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotFoundModel implements Serializable {

    private String message;
    private Integer status;

    private static final String defaultNotFoundMessage = "Requested item was not found!";

    /**
     * Instantiates a new Not found model.
     */
    public NotFoundModel() {
        this.message = defaultNotFoundMessage;
        this.status = 404;
    }

    /**
     * Instantiates a new Not found model.
     *
     * @param message the message
     * @param status  the status
     */
    public NotFoundModel(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

}

