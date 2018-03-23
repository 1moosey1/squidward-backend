package com.squidward.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="squidward_user")
@Getter @Setter @ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name="user_name")
    private String username;

    @NotNull @Email
    @Column(name="email")
    private String email;

    @NotNull
    @Size(min=6)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password")
    private String password;

    @JsonIgnore
    @Column(name="oauth_token")
    private String oAuthToken;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="map_of_projects",
            joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns= {@JoinColumn(name="project_id")})
    private Set<Project> projects;
}
