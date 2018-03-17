package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="squidward_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name="user_name")
    private String username;

    @Column(name="email")
    private String email;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="map_of_projects",
            joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns= {@JoinColumn(name="project_id")})
    private Set<Project> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
