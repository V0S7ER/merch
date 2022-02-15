package com.cod.merch.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "contest")
@Getter
@Setter
@AllArgsConstructor
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    Date date;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "wonContests")
    private List<User> winners;

    public Contest() {

    }

    //TODO: add WinnerList, add ParticipantList

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contest contest = (Contest) o;
        return id != null && Objects.equals(id, contest.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
