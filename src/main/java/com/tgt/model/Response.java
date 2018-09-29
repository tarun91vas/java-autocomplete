package com.tgt.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Double time_in_millis;
    private Object data;
    private String message;

    public Response(){
    }

    public Double getTime_in_millis() {
        return time_in_millis;
    }

    public void setTime_in_millis(Double time_in_millis) {
        this.time_in_millis = time_in_millis;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}