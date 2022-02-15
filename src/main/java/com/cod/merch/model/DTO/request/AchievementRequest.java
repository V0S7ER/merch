package com.cod.merch.model.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AchievementRequest {
    private String name;
    private Long cost;
    private String description;

    @JsonProperty("admin_email")
    private String admin_email;
    @JsonProperty("admin_password")
    private String admin_password;
}
