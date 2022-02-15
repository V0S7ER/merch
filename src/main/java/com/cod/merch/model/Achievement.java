package com.cod.merch.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "achievement")
@Getter
@Setter
@AllArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long cost;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "achievementList", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> receivedUsers;

    public Achievement() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Achievement that = (Achievement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
