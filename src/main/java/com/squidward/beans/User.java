package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

    @Column(name="email")
    private String email;

    public User() {
        super();
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
