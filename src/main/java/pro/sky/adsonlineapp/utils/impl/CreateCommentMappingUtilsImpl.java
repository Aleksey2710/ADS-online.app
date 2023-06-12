package pro.sky.adsonlineapp.utils.impl;

import org.springframework.stereotype.Service;
import pro.sky.adsonlineapp.dto.CreateComment;
import pro.sky.adsonlineapp.model.Ad;
import pro.sky.adsonlineapp.model.Comment;
import pro.sky.adsonlineapp.model.User;
import pro.sky.adsonlineapp.utils.MappingUtils;


@Service
public class CreateCommentMappingUtilsImpl implements MappingUtils<CreateComment, Comment> {

    @Override
    public CreateComment mapToDto(Comment entity) {
        CreateComment dto = new CreateComment();
        dto.setText(entity.getText());
        return dto;
    }

    @Override
    public Comment mapToEntity(CreateComment dto) {
        Comment entity = new Comment();
        Ad ad = new Ad();
        User author = new User();
        entity.setText(dto.getText());
        entity.setAd(ad);
        entity.setAuthor(author);
        return entity;
    }
}