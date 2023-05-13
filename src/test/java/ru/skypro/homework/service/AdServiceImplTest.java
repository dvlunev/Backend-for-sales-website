package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserForbiddenException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private AdRepository adRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private AdMapper adMapper;
    private final String USERNAME = "testUser";
    private final String DIF_USERNAME = "differentUser@mail.com";
    private final String PASSWORD = "testPassword";
    private final String DIF_PASSWORD = "differentTestPassword";
    private final Role userRole = Role.USER;
    private final Role adminRole = Role.ADMIN;
    private final User testUser = new User();
    private final User diffUser = new User();
    private final Ad testAd = new Ad();
    private final CreateAdsDto createAdsDto = new CreateAdsDto();

    @BeforeEach
    public void setUp() {
        testUser.setUsername(USERNAME);
        testUser.setPassword(PASSWORD);
        testUser.setRole(userRole);

        diffUser.setUsername(DIF_USERNAME);
        diffUser.setPassword(DIF_PASSWORD);
        diffUser.setRole(userRole);

        testAd.setId(1);
        testAd.setDescription("test description");
        testAd.setTitle("test title");
        testAd.setPrice(50);

        createAdsDto.setDescription("New Description");
        createAdsDto.setTitle("New Title");
        createAdsDto.setPrice(500);
    }

    @Test
    @DisplayName("Проверка доступа к объявлению для админа")
    public void checkAccessForAdminTest() {
        User testAdmin = new User(USERNAME, PASSWORD, adminRole);
        testAd.setAuthor(testAdmin);

        when(userService.findAuthUser()).thenReturn(Optional.of(testAdmin));
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));

        boolean result = adService.checkAccess(testAd.getId());

        assertTrue(result);

        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка доступа к объявлению для создателя объявления")
    public void checkAccessForAuthorTest() {
        testAd.setAuthor(testUser);

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));

        boolean result = adService.checkAccess(testAd.getId());

        assertTrue(result);

        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка доступа к объявлению для другого пользователя")
    public void checkAccessForDifferentUserTest() {
        testAd.setAuthor(diffUser);

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));

        boolean result = adService.checkAccess(testAd.getId());

        assertFalse(result);

        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка доступа к объявлению, когда объявление не найдено")
    public void checkAccessForNonExistentAdTest() {
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adService.checkAccess(testAd.getId()));

        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка возвращения списка объявлений")
    public void getAllAdsDtoTest() {
        List<Ad> adList = Collections.singletonList(new Ad());
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());

        when(adRepository.findAll()).thenReturn(adList);
        when(adMapper.mapAdListToAdDtoList(adList)).thenReturn(adsDtoList);

        ResponseWrapperAdsDto result = adService.getAllAdsDto();

        assertNotNull(result);
        assertEquals(adsDtoList, result.getResults());

        verify(adRepository, times(1)).findAll();
        verify(adMapper, times(1)).mapAdListToAdDtoList(adList);
    }

    @Test
    @DisplayName("Проверка создания объявления")
    public void createAdsTest() {
        CreateAdsDto adDto = new CreateAdsDto();
        adDto.setDescription("test description");
        adDto.setTitle("test title");
        adDto.setPrice(50);
        testAd.setAuthor(testUser);

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adMapper.mapCreatedAdsDtoToAd(adDto)).thenReturn(testAd);
        when(imageService.saveImage(any(MultipartFile.class))).thenReturn(new Image());
        when(adRepository.save(any(Ad.class))).thenReturn(new Ad());
        when(adMapper.mapAdToAdDto(any(Ad.class))).thenReturn(new AdsDto());

        AdsDto result = adService.createAds(adDto, new MockMultipartFile("test", new byte[20]));

        assertNotNull(result);

        verify(adRepository, times(1)).save(any(Ad.class));
        verify(adMapper, times(1)).mapCreatedAdsDtoToAd(adDto);
        verify(adMapper, times(1)).mapAdToAdDto(any(Ad.class));
    }

    @Test
    @DisplayName("Проверка получения всех данных из объявления")
    public void getFullAdDtoTest() {
        FullAdsDto fullAdsDto = new FullAdsDto();

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(adMapper.mapAdToFullAdsDTo(testAd)).thenReturn(fullAdsDto);

        FullAdsDto result = adService.getFullAdDto(testAd.getId());

        assertEquals(fullAdsDto, result);

        verify(adRepository, times(1)).findById(testAd.getId());
        verify(adMapper, times(1)).mapAdToFullAdsDTo(testAd);
    }

    @Test
    @DisplayName("Проверка удаления объявления, когда оно существует и когда у пользователя есть к нему доступ")
    public void removeAdDtoWhenAdDoesExistAndUserAccessTest() {
        testAd.setAuthor(testUser);

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));

        assertTrue(adService.removeAdDto(testAd.getId()));

        verify(adRepository, times(1)).findById(testAd.getId());
        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).deleteById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка невозможности удаления объявления, когда оно существует и когда у пользователя нет к нему доступа")
    public void removeAdDtoWhenAdDoesExistAndUserDoesntHaveAccessTest() {
        testAd.setAuthor(testUser);

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(userService.findAuthUser()).thenReturn(Optional.of(diffUser));

        assertThrows(UserForbiddenException.class, () -> adService.removeAdDto(testAd.getId()));

        verify(adRepository, times(1)).findById(testAd.getId());
        verify(userService, times(1)).findAuthUser();
    }

    @Test
    @DisplayName("Проверка невозможности удаления объявления, когда оно не существует")
    public void removeAdDtoWhenAdDoesntExistTest() {
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adService.removeAdDto(testAd.getId()));

        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка редактирования объявления, когда оно существует и когда у пользователя есть к нему доступ")
    public void updateAdDtoWhenAdDoesExistAndUserAccessTest() {
        testAd.setAuthor(testUser);

        AdsDto adsDto = new AdsDto();

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adMapper.mapAdToAdDto(adRepository.save(testAd))).thenReturn(adsDto);

        AdsDto newAdsDto = adService.updateAdDto(testAd.getId(), createAdsDto);

        assertEquals(testAd.getTitle(), createAdsDto.getTitle());
        assertEquals(testAd.getPrice(), createAdsDto.getPrice());
        assertEquals(testAd.getDescription(), createAdsDto.getDescription());
        assertEquals(adsDto, newAdsDto);

        verify(adRepository, times(2)).findById(testAd.getId());
        verify(userService, times(1)).findAuthUser();
    }

    @Test
    @DisplayName("Проверка невозможности редактирования объявления, " +
            "когда оно существует и когда у пользователя нет к нему доступа")
    public void updateAdDtoWhenAdDoesExistAndUserDoesntHaveAccessTest() {
        testAd.setAuthor(testUser);

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(userService.findAuthUser()).thenReturn(Optional.of(diffUser));

        assertThrows(UserForbiddenException.class, () -> adService.updateAdDto(testAd.getId(), createAdsDto));

        verify(adRepository, times(2)).findById(testAd.getId());
        verify(userService, times(1)).findAuthUser();
    }

    @Test
    @DisplayName("Проверка невозможности редактирования объявления, когда оно не существует")
    public void updateAdDtoWhenAdDoesntExistTest() {
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adService.updateAdDto(testAd.getId(), createAdsDto));

        verify(adRepository, times(1)).findById(testAd.getId());
    }

    @Test
    @DisplayName("Проверка возвращения списка объявлений авторизованного пользователя, когда они есть")
    public void getAllUserAdsDtoHasAdsTest() {
        testAd.setAuthor(testUser);
        List<Ad> adList = Collections.singletonList(testAd);
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());

        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adRepository.findAll()).thenReturn(adList);
        when(adMapper.mapAdListToAdDtoList(adList)).thenReturn(adsDtoList);

        ResponseWrapperAdsDto result = adService.getAllUserAdsDto();

        assertNotNull(result);
        assertEquals(adsDtoList, result.getResults());

        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).findAll();
        verify(adMapper, times(1)).mapAdListToAdDtoList(adList);
    }

    @Test
    @DisplayName("Проверка возвращения списка объявлений авторизованного пользователя, когда их нет")
    public void getAllUserAdsDtoHasNoAdsTest() {
        when(userService.findAuthUser()).thenReturn(Optional.of(testUser));
        when(adRepository.findAll()).thenReturn(Collections.emptyList());
        when(adMapper.mapAdListToAdDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        ResponseWrapperAdsDto result = adService.getAllUserAdsDto();

        assertNotNull(result);
        assertEquals(Collections.emptyList(), result.getResults());

        verify(userService, times(1)).findAuthUser();
        verify(adRepository, times(1)).findAll();
        verify(adMapper, times(1)).mapAdListToAdDtoList(Collections.emptyList());
    }

    @Test
    @DisplayName("Обновление фотографии объявления, когда оно существует")
    public void updateImageAdDtoFoundTest() {
        testAd.setAuthor(testUser);
        testAd.setImage(new Image("imageOld", new byte[20]));
        MockMultipartFile image = new MockMultipartFile("image", new byte[30]);

        when(adRepository.findById(testAd.getId())).thenReturn(Optional.of(testAd));
        when(imageService.updateImage(image, testAd.getImage())).thenReturn(new Image());

        adService.updateImageAdDto(testAd.getId(), image);

        verify(adRepository, times(1)).findById(testAd.getId());
        verify(adRepository, times(1)).save(testAd);
    }

    @Test
    @DisplayName("Обновление фотографии объявления, когда оно не существует")
    public void updateImageAdDtoNotFoundTest() {
        when(adRepository.findById(testAd.getId())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class,
                () -> adService.updateImageAdDto(testAd.getId(),
                        new MockMultipartFile("image", new byte[30])));

        verify(adRepository, times(1)).findById(testAd.getId());
    }
}
