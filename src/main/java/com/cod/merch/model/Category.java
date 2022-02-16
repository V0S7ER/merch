package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description; //fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    private List<Item2Category> item2CategoryList;//OneToMany

    @ManyToMany(mappedBy = "categoryList", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Item> itemList; //ManyToMany
}
