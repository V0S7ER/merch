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

    private String description; //fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achievement")
    private List<User2Achieve> user2AchieveList; //OneToMany

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "achievementList", fetch = FetchType.LAZY)
    private List<User> receivedUsers; //ManyToMany

    public Achievement(String name, Long cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public Achievement() {

    }
}
