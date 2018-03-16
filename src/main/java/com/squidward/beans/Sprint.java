package com.squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sprint")
public class Sprint implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="sprint_id")
    private int id;

    @Column(name="sprint_number")
    private int number;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    private Project project;

    @Column(name="release")
    private boolean release;

    public Sprint() { super(); }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
}
