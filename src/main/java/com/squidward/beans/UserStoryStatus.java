package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_story_status")
public class UserStoryStatus implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="uss_id")
    private int id;

    @Column(name="status_type")
    private StatusType statusType;

    public UserStoryStatus() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
