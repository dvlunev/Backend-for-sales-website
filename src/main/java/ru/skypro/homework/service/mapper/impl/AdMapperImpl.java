package ru.skypro.homework.service.mapper.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.impl.UserServiceImpl;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс - сервис-маппер, содержащий реализацию интерфейса {@link AdMapper}
 */
@Component
public class AdMapperImpl implements AdMapper {
    /**
     * Метод, преобразующий объект класса Ad в объект класса AdsDto.
     *
     * @param ad
     * @return AdsDto
     */
    @Override
    public AdsDto mapAdToAdDto(Ad ad) {
        AdsDto adsDto = new AdsDto();
        adsDto.setPk(ad.getId());
        adsDto.setAuthor(ad.getAuthor().getId());
        adsDto.setPrice(ad.getPrice());
        adsDto.setImage("/ads/" + ad.getImage().getId() + "/image");
        adsDto.setTitle(ad.getTitle());
        return adsDto;
    }

    /**
     * Метод, преобразующий объект класса AdsDto в объект класса Ad.
     *
     * @param adsDto
     * @return Ad
     */
    @Override
    public Ad mapAdsDtoToAd(AdsDto adsDto) {
        Ad mappedAd = new Ad();
        mappedAd.setId(adsDto.getPk());
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setId(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }

    /**
     * Метод, преобразующий объект класса Ad в объект класса FullAdsDto.
     *
     * @param ad
     * @return FullAdsDto
     */
    @Override
    public FullAdsDto mapAdToFullAdsDTo(Ad ad) {
        FullAdsDto fullAdsDto = new FullAdsDto();
        fullAdsDto.setPk(ad.getId());
        fullAdsDto.setAuthorFirstName(ad.getAuthor().getFirstName());
        fullAdsDto.setAuthorLastName(ad.getAuthor().getLastName());
        fullAdsDto.setEmail(ad.getAuthor().getEmail());
        fullAdsDto.setPhone(ad.getAuthor().getPhone());
        fullAdsDto.setTitle(ad.getTitle());
        fullAdsDto.setDescription(ad.getDescription());
        fullAdsDto.setImage("/ads/" + ad.getImage().getId() + "/image");
        fullAdsDto.setPrice(ad.getPrice());
        return fullAdsDto;
    }

    /**
     * Метод, преобразующий объект класса CreateAdsDto в объект класса Ad.
     *
     * @param createAdsDto
     * @return Ad
     */
    @Override
    public Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto) {
        Ad ad = new Ad();
        ad.setTitle(createAdsDto.getTitle());
        ad.setDescription(createAdsDto.getDescription());
        ad.setPrice(createAdsDto.getPrice());
        return ad;
    }

    /**
     * Метод, преобразующий объект класса Collection<Ad> в объект класса Collection<AdsDto>.
     *
     * @param adCollection
     * @return Collection<AdsDto>
     */
    @Override
    public Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection) {
        List<AdsDto> dtoList = new ArrayList<AdsDto>(adCollection.size());
        for (Ad ad : adCollection) {
            dtoList.add(mapAdToAdDto(ad));
        }
        return dtoList;
    }
}