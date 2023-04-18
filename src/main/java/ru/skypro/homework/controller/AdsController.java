package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    @Operation(
            summary = "getAllAds",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    })
            })
    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        List<AdsDto> adsDtoList = new ArrayList<>();
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto(adsDtoList);
        return ResponseEntity.ok().body(responseWrapperAdsDto);
    }

    @Operation(
            summary = "addAds",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAdds(@RequestPart CreateAdsDto properties, @RequestPart MultipartFile image) {
        AdsDto ad = new AdsDto();
        return ResponseEntity.ok().body(ad);
    }

    @Operation(
            summary = "getComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperCommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable("ad_pk") String adPk) {
        List<CommentDto> commentsList = new ArrayList<>();
        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(commentsList);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(
            summary = "addComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk") String adPk,
                                                  @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = new CommentDto();
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "getFullAd",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = FullAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable("id") Integer id) {
        FullAdsDto newFullAdsDto = new FullAdsDto();
        return ResponseEntity.ok().body(newFullAdsDto);
    }

    @Operation(
            summary = "removeAds",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "updateAds",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id, @RequestBody CreateAdsDto createAdsDto) {
        AdsDto newAdsDto = new AdsDto();
        return ResponseEntity.ok().body(newAdsDto);
    }

    @Operation(
            summary = "getComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("ad_pk") String adPk, @PathVariable("id") int id) {
        CommentDto newCommentDto = new CommentDto();
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "deleteComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") String adPk, @PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "updateComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable("ad_pk") String adPk,
                                                     @PathVariable("id") int id,
                                                     @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = new CommentDto();
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "getAdsMe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping(value = "/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@RequestBody Authentication authentication) {
        List<AdsDto> adsDtoList = new ArrayList<>();
        ResponseWrapperAdsDto ads = new ResponseWrapperAdsDto(adsDtoList);
        return ResponseEntity.ok(ads);
    }
}
