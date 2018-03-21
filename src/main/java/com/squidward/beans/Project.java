package com.squidward.beans;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="project")
@Getter @Setter @ToString
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="project_id")
    private int id;

    @Column(name="project_name")
    private String name;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @ManyToMany(mappedBy="projects", cascade = {CascadeType.ALL})
    private Set<User> users;

    @OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="owner")
    private User owner;
}
