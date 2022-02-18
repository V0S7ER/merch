package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private String description;

    private String photo;

    //Fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Item2Category> item2categoryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Wish> user2itemWish;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Basket> user2itemBasket; //OneToMany

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "item2category",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "wishList", fetch = FetchType.LAZY)
    private List<User> wantedUsers;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "basket", fetch = FetchType.LAZY)
    private List<User> basketUsers; //ManyToMany

    public Item(String name, Long price, String description, String photo) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
    }

    public Item() {

    }

    public boolean addCategory(Category category) {
        try {
            categoryList.add(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
