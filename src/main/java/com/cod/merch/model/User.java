package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Boolean sex; //0 - female, 1 - male
    @Column(name = "admin")
    private Boolean isAdmin;
    @Column(columnDefinition = "")
    private Long balance;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "department_id")
    private Department department;//Fields

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<User2Achieve> user2AchieveList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<User2Contest> user2ContestList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Basket> user2itemBasket;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Wish> user2itemWish; //OneToMany c связывающими таблицами

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user2achieve",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achieve_id"))
    private List<Achievement> achievementList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "wish",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> wishList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user2contest",
            joinColumns = @JoinColumn(name = "user_ir"),
            inverseJoinColumns = @JoinColumn(name = "contest_id"))
    private List<Contest> wonContests;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "basket",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> basket; //ManyToMany

    public User(String name, String surname, String password, boolean sex, Department department, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.sex = sex;
        this.department = department;
        this.email = email;
        isAdmin = false;
        balance = 0L;
    }

    public User() {

    } //Costructors

    public void addToWish(Item item) {
        wishList.add(item);
    }

    public void removeFromWish(Item item) {
        wishList.remove(item);
    } //Wish communicate

    public void addToBasket(Item item) {
        basket.add(item);
    }

    public void removeFromBasket(Item item) {
        basket.remove(item);
    }

    public boolean buyFromBasket(Item item) {
        if(balance >= item.getPrice()) {
            basket.remove(item);
            balance-=item.getPrice();
            return true;
        }
        return false;
    }  //Basket communicate

    public void addWonContest(Contest contest) {
        wonContests.add(contest);
        balance += 100L;
    } //Contest communicate

    public void addAchievement(Achievement achievement) {
        achievementList.add(achievement);
    } //Achievement communicate
}
