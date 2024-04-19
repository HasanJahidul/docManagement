package com.hasanjahidul.docManagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
@Getter
@Setter
public class APIResponseModel<T> implements Serializable {
    private Integer status;
    private transient T results;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalRows;

    /**
     * Instantiates a new Api response.
     *
     * @param data the data
     */
    public APIResponseModel(Integer status, T data) {
        this.status = status;
        this.results = data;
    }

    public APIResponseModel(Integer status, T data, Long totalRows) {
        this.status = status;
        this.results = data;
        this.totalRows = Objects.requireNonNullElse(totalRows, 0L);
    }

    public APIResponseModel(T data) {
        this.status = 200;
        this.results = data;
    }


}
