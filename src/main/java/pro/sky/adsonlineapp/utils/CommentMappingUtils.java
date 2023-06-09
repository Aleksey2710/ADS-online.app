package pro.sky.adsonlineapp.utils;

import org.springframework.stereotype.Service;
import pro.sky.adsonlineapp.dto.CommentDto;
import pro.sky.adsonlineapp.model.Comment;

import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Бизнес-логика по маппингу комментариев (Comment).
 */
@Service
public class CommentMappingUtils {

    public CommentDto mapToDto(Comment entity) {

        TimeZone tz = TimeZone.getDefault();
        LocalDateTime ldt = LocalDateTime.ofInstant(entity.getCreatedAt(), tz.toZoneId());

        CommentDto dto = new CommentDto();
        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setPk(entity.getId());

        if (entity.getAuthor().getImage() != null) {
            dto.setAuthorImage(String.format("/ads/image/%s", entity.getAuthor().getImage()));
        } else {
            dto.setAuthorImage(null);
        }

        dto.setCreatedAt(ldt);
        dto.setText(entity.getText());

        return dto;
    }
}
