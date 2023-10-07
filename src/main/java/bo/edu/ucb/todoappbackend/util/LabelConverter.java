package bo.edu.ucb.todoappbackend.util;

import bo.edu.ucb.todoappbackend.dto.LabelDto;
import bo.edu.ucb.todoappbackend.entity.Label;
import bo.edu.ucb.todoappbackend.entity.User;

public class LabelConverter {

    public static LabelDto entityToDto(Label label) {
        LabelDto labelDto = new LabelDto();
        labelDto.setLabelId(label.getLabelId());
        labelDto.setLabelName(label.getLabelName());
        labelDto.setUserId(label.getUser().getUserId());
        return labelDto;
    }

    public static Label dtoToEntity(LabelDto labelDto, User user) {
        Label label = new Label();
        label.setLabelId(labelDto.getLabelId());
        label.setLabelName(labelDto.getLabelName());
        label.setUser(user);
        return label;
    }
}
