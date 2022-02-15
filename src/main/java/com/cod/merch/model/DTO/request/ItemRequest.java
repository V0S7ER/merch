package com.cod.merch.model.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private String name;
    private Long price;
    private String description;

    @JsonProperty("admin_email")
    private String admin_email;
    @JsonProperty("admin_password")
    private String admin_password;
}
