package com.hasanjahidul.docManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Create success model.
 */
@Getter
@Setter
public class CreateSuccessModel implements Serializable {


    private static final String defaultCreateSuccessMessage = "Item created successfully!";

    private final String message;

    private final Integer status;

    @JsonIgnore
    private long id;

    /**
     * Instantiates a new Create success model.
     */
    public CreateSuccessModel() {
        this.message = defaultCreateSuccessMessage;
        this.status = 201;
    }

    /**
     * Instantiates a new Create success model.
     *
     * @param message the message
     * @param status  the status
     */
    public CreateSuccessModel(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Instantiates a new Create success model.
     *
     * @param message the message
     */
    public CreateSuccessModel(String message) {
        this.message = message;
        this.status = 201;
    }

    /**
     * Instantiates a new Creation success model.
     *
     * @param message the message
     * @param status  the status
     * @param id      the id
     */
    public CreateSuccessModel(String message, Integer status, Long id) {
        this.message = message;
        this.status = status;
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateSuccessModel{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }

    public void setMessage(String conceptNoteApproverAdded) {
    }
}
