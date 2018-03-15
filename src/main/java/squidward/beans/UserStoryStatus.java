package squidward.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_story_status")
public class UserStoryStatus implements Serializable {
    private enum StatusType {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="uss_id")
    private int ussId;

    @Column(name="status_type")
    private StatusType statusType;

    public UserStoryStatus() {
        super();
    }

    public int getUssId() {
        return ussId;
    }

    public void setUssId(int ussId) {
        this.ussId = ussId;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
