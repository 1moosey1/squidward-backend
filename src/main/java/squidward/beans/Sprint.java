package squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sprint")
public class Sprint implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="sprint_id")
    private int sprintId;

    @Column(name="sprint_number")
    private int sprintNumber;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    private int projectId;

    @Column(name="release")
    private boolean release;

    public Sprint() {
        super();
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
}
