package com.backend.survey.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DataResponse<T> {
    private Date timestamp;
    private int status;
    private String error;
    private List<T> data;

    public DataResponse() {
        super();
    }

    public DataResponse(Date timestamp, int status, String error, List<T> data) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.data = data;
    }
}
