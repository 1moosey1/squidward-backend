package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="project_user")
public class ProjectUser implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="project_user_id")
    private int projectUserId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    private int projectId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private int userId;
}
