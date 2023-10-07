package bo.edu.ucb.todoappbackend.dto;

public class LabelDto {
    private Long labelId;
    private String labelName;
    private Long userId;

    public LabelDto() {} // importante: constructor por defecto / sin argumentos

    public LabelDto(Long labelId, String labelName, Long userId) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LabelDto{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
