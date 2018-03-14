package com.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_story")
public class UserStory implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="us_id")
    private int usId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uss_id")
    private int ussId;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sprint_id")
    private int sprintId;

    public UserStory() {
        super();
    }

    public int getUsId() {
        return usId;
    }

    public void setUsId(int usId) {
        this.usId = usId;
    }

    public int getUssId() {
        return ussId;
    }

    public void setUssId(int ussId) {
        this.ussId = ussId;
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

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }
}
