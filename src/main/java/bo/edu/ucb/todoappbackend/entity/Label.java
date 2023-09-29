package bo.edu.ucb.todoappbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "labels")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long labelId;

    @Column(name = "label_name", nullable = false)
    private String labelName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Label() {} // constructor por defecto, necesario siempre

    public Label(String labelName, User user) {
        this.labelName = labelName;
        this.user = user;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", user=" + user +
                '}';
    }
}
