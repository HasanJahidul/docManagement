package com.hasanjahidul.docManagement.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Delete success model.
 */
@Getter
@Setter
public class DeleteSuccessModel implements Serializable {
    private final String message;

    private final Integer status;

    private static final String defaultDeleteSuccessMessage = "Item has been deleted successfully!";

    /**
     * Instantiates a new Delete success model.
     */
    public DeleteSuccessModel() {
        this.message = defaultDeleteSuccessMessage;
        this.status = 200;
    }
    public DeleteSuccessModel(String message) {
        this.message = message;
        this.status = 200;
    }
    
    @Override
    public String toString() {
        return "DeleteSuccessModel{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
