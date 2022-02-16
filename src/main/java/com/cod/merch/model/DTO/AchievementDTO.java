package com.cod.merch.model.DTO;

import com.cod.merch.model.Achievement;
import lombok.Data;

@Data
public class AchievementDTO {
    private Long id;
    private String name;
    private String description;
    private Long cost;
    private String photo;

    public AchievementDTO(Achievement achievement) {
        id = achievement.getId();
        name = achievement.getName();
        description = achievement.getDescription();
        cost = achievement.getCost();
        photo = achievement.getPhoto();
    }
}
