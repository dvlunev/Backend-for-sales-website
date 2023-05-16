package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.security.WebSecurityConfig;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebSecurityConfig.class})
public class AdsControllerTest {

    @InjectMocks
    AdsController adsController;
    @Mock
    private AdService adService;
    @Mock
    private CommentService commentService;
    @Mock
    private ImageService imageService;
    private final Ad testAd = new Ad();
    private final Comment testCom = new Comment();
    private final CommentDto commentDto = new CommentDto();
    private final CreateAdsDto createAdsDto = new CreateAdsDto();

    @BeforeEach
    public void setUp() {
        testAd.setId(1);
        testAd.setDescription("test description");
        testAd.setTitle("test title");
        testAd.setPrice(50);

        testCom.setId(2);

        commentDto.setPk(1);
        commentDto.setText("test text");

        createAdsDto.setDescription("New Description");
        createAdsDto.setTitle("New Title");
        createAdsDto.setPrice(500);
    }

    @Test
    public void getAllAdsTest() {
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());
        ResponseWrapperAdsDto adsDto = new ResponseWrapperAdsDto(adsDtoList);

        when(adService.getAllAdsDto()).thenReturn(adsDto);

        ResponseEntity<ResponseWrapperAdsDto> responseEntity = adsController.getAllAds();

        verify(adService).getAllAdsDto();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void addAdTest() throws Exception {
        AdsDto adsDto = new AdsDto();
        MultipartFile image = new MockMultipartFile("test.jpg", "test.jpg",
                "image/jpeg", "test image".getBytes());

        when(adService.createAds(createAdsDto, image)).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.addAd(createAdsDto, image);

        verify(adService).createAds(createAdsDto, image);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
        assertNotNull(adsDto);
    }

    @Test
    public void getCommentsTest() {
        List<CommentDto> commentDtoList = Collections.singletonList(new CommentDto());
        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(commentDtoList);

        when(commentService.getCommentsDto(testAd.getId())).thenReturn(comments);

        ResponseEntity<ResponseWrapperCommentDto> response = adsController.getComments(testAd.getId());

        verify(commentService).getCommentsDto(testAd.getId());

        assertEquals(comments, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void addCommentTest() {
        CommentDto newCommentDto = new CommentDto();

        when(commentService.createCommentDto(testAd.getId(), commentDto)).thenReturn(newCommentDto);

        ResponseEntity<CommentDto> response = adsController.addComment(testAd.getId(), commentDto);

        verify(commentService).createCommentDto(testAd.getId(), commentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newCommentDto, response.getBody());
        assertNotNull(newCommentDto);
    }

    @Test
    public void getFullAdTest() {
        FullAdsDto fullAdsDto = new FullAdsDto();

        when(adService.getFullAdDto(testAd.getId())).thenReturn(fullAdsDto);

        ResponseEntity<FullAdsDto> response = adsController.getAds(testAd.getId());

        verify(adService).getFullAdDto(testAd.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fullAdsDto, response.getBody());
    }

    @Test
    public void removeAdTest() {
        when(adService.removeAdDto(testAd.getId())).thenReturn(true);

        ResponseEntity<?> response = adsController.removeAd(testAd.getId());

        verify(adService).removeAdDto(testAd.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void updateAdTest() {
        AdsDto adsDto = new AdsDto();
        adsDto.setTitle("New Title");
        adsDto.setPrice(500);

        when(adService.updateAdDto(testAd.getId(), createAdsDto)).thenReturn(adsDto);

        ResponseEntity<AdsDto> response = adsController.updateAds(testAd.getId(), createAdsDto);

        verify(adService).updateAdDto(testAd.getId(), createAdsDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createAdsDto.getTitle(), Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals(createAdsDto.getPrice(), response.getBody().getPrice());
    }

    @Test
    public void deleteCommentWithOkTest() {
        when(commentService.removeCommentDto(testAd.getId(), testCom.getId())).thenReturn(true);

        ResponseEntity<Void> response = adsController.deleteComment(testAd.getId(), testCom.getId());

        verify(commentService).removeCommentDto(testAd.getId(), testCom.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteCommentWithNotFoundTest() {
        when(commentService.removeCommentDto(testAd.getId(), testCom.getId())).thenReturn(false);

        ResponseEntity<Void> response = adsController.deleteComment(testAd.getId(), testCom.getId());

        verify(commentService).removeCommentDto(testAd.getId(), testCom.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateCommentTest() {
        CommentDto newCommentDto = new CommentDto();
        newCommentDto.setPk(1);
        newCommentDto.setText("test text");

        when(commentService.updateCommentDto(testAd.getId(), testCom.getId(), commentDto)).thenReturn(newCommentDto);

        ResponseEntity<CommentDto> response = adsController.updateComment(testAd.getId(), testCom.getId(), commentDto);

        verify(commentService).updateCommentDto(testAd.getId(), testCom.getId(), commentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newCommentDto, response.getBody());
        assertEquals(commentDto.getPk(), Objects.requireNonNull(response.getBody()).getPk());
        assertEquals(commentDto.getText(), response.getBody().getText());
        assertEquals(commentDto.getAuthor(), response.getBody().getAuthor());
    }

    @Test
    public void getAdsMeTest() {
        List<AdsDto> adsDtoList = Collections.singletonList(new AdsDto());
        ResponseWrapperAdsDto adsDto = new ResponseWrapperAdsDto(adsDtoList);

        when(adService.getAllUserAdsDto()).thenReturn(adsDto);

        ResponseEntity<ResponseWrapperAdsDto> response = adsController.getAdsMe();

        verify(adService).getAllUserAdsDto();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDto, response.getBody());
    }

    @Test
    public void updateImageTest() throws IOException {
        MultipartFile image = new MockMultipartFile("image.jpg", new byte[]{1, 2, 3});

        doNothing().when(adService).updateImageAdDto(testAd.getId(), image);

        ResponseEntity<byte[]> response = adsController.updateImage(testAd.getId(), image);

        verify(adService).updateImageAdDto(testAd.getId(), image);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getImageTest() {
        String id = "1";
        byte[] mockImage = {1, 2, 3};

        when(imageService.getImageById(id)).thenReturn(mockImage);

        ResponseEntity<byte[]> response = adsController.getImage(id);

        verify(imageService).getImageById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertArrayEquals(mockImage, response.getBody());
    }
}
