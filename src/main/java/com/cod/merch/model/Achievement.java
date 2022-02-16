package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "achievement")
@Getter
@Setter
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long cost;

    private String description;

    private String photo;
    //fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achievement", fetch = FetchType.LAZY)
    private List<User2Achieve> user2AchieveList; //OneToMany

    @ManyToMany(mappedBy = "achievementList", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> receivedUsers; //ManyToMany

    public Achievement(String name, Long cost, String description, String photo) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.photo = photo;
    }

    public Achievement() {

    }
}
