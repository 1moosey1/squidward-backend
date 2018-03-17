package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_story")
public class UserStory implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="us_id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uss_id")
    private UserStoryStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sprint_id")
    private Sprint sprint;

    @Column(name="story")
    private String story;

    @Column(name="difficulty")
    private int difficulty;

    @Column(name="done_tag")
    private String doneTag;

    @Column(name="start_tag")
    private String startTag;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="done_date")
    private Date doneDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserStoryStatus getStatus() {
        return status;
    }

    public void setStatus(UserStoryStatus status) {
        this.status = status;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDoneTag() {
        return doneTag;
    }

    public void setDoneTag(String doneTag) {
        this.doneTag = doneTag;
    }

    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }
}
