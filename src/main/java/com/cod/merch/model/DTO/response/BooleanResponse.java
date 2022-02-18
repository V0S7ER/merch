package com.cod.merch.model.DTO.response;

import lombok.Data;

@Data
public class BooleanResponse {
    private String contains; //"true", "false"

    public BooleanResponse(boolean bool) {
        if(bool) {
            contains = "true";
        } else {
            contains = "false";
        }
    }
}
