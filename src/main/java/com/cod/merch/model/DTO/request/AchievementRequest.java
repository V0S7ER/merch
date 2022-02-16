package com.cod.merch.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class AchievementRequest {
    @Nullable
    private String name;
    @Nullable
    private Long cost;
    @Nullable
    private String description;
    @Nullable
    private String photo;

    private String admin_email;
    private String admin_password;
}
