package pro.sky.adsonlineapp.utils;

import org.springframework.stereotype.Service;
import pro.sky.adsonlineapp.dto.FullAds;
import pro.sky.adsonlineapp.model.Ad;
import pro.sky.adsonlineapp.utils.MappingUtils;

/**
 * Бизнес-логика по маппингу объявлений (FullAds).
 */
@Service
public class FullAdsMappingUtils {

    public FullAds mapToDto(Ad entity) {

        FullAds dto = new FullAds();
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        dto.setEmail(entity.getAuthor().getEmail());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setPk(entity.getId());
        dto.setPhone(entity.getAuthor().getPhone());
//        dto.setImage(entity.getPicture);

        return dto;
    }
}
