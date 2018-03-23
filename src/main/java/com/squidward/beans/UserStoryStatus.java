package com.squidward.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_story_status")
@Getter @Setter @ToString
public class UserStoryStatus implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="uss_id")
    private int id;

    @Column(name="status_type")
    private String statusType;
}
