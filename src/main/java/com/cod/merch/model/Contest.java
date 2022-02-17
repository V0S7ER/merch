package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "contest")
@Getter
@Setter
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date date;

    private Long cost;
    //fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contest", fetch = FetchType.LAZY)
    private List<User2Contest> user2ContestList; //OneToMany

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "wonContests", fetch = FetchType.LAZY)
    private List<User> winners; //ManyToMany

    public Contest(String name, Date date, Long cost) {
        this.name = name;
        this.date = date;
        this.cost = cost;
    }

    public Contest() {

    } //Constructors
}
