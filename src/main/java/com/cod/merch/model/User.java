package com.cod.merch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    @Column(nullable = true)
    private String photo;
    private Date birthday;
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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "user2achieve",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<Achievement> achievementList;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "wish",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> wishList;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "user2contest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id"))
    private List<Contest> wonContests;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "basket",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> basket; //ManyToMany

    public User(String name, String surname, String password, Date birthday, boolean sex, Department department, String email, String photo) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.sex = sex;
        this.department = department;
        this.email = email;
        this.birthday = birthday;
        this.photo = photo;
        isAdmin = false;
        balance = 0L;
    }

    public User() {

    } //Constructors

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
        if (balance >= item.getPrice()) {
            basket.remove(item);
            balance -= item.getPrice();
            return true;
        }
        return false;
    }  //Basket communicate

    public void addWonContest(Contest contest) {
        wonContests.add(contest);
        balance += contest.getCost();
    }

    public void removeWonContest(Contest contest) {
        if (wonContests.contains(contest)) {
            wonContests.remove(contest);
            //balance-=contest.getCost(); //Аналогично удалению ачивки: баланс НЕ МЕНЯЕМ, если уж ошиблись
        }
    }//Contest communicate

    public void addAchievement(Achievement achievement) {
        achievementList.add(achievement);
        balance += achievement.getCost();
    }

    public void removeAchievement(Achievement achievement) {
        if (achievementList.contains(achievement)) {
            achievementList.remove(achievement);
            //balance-=achievement.getCost(); //Если юзеру было добавлена ачивка, то баланс уже нельзя уменьшить путем удаления ачивки, иначе мог бы быть отрицательный баланс
        }
    }//Achievement communicate
}
