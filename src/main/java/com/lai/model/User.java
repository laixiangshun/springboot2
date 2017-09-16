package com.lai.model;

import javax.persistence.*;

/**
 * Created by lailai on 2017/9/15.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false,name = "name")
    private String name;

    @Column(nullable = false,name = "age")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public User(String name,Integer age){
        this.name=name;
        this.age=age;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
