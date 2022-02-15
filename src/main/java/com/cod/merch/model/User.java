package com.cod.merch.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String password;

    private Boolean sex; //0 - female, 1 - male

    @Column(name = "admin")
    private Boolean isAdmin;

    private String phone;

    private Long balance;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Department.class)
    @JoinColumn(name = "department_id")
    private Department department;

    private Date birthday;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user2achieve",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achieve_id"))
    private List<Achievement> achievementList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user2item",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> wishList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user2contest",
    joinColumns = @JoinColumn(name = "user_ir"),
    inverseJoinColumns = @JoinColumn(name = "contest_id"))
    private List<Contest> wonContests;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
