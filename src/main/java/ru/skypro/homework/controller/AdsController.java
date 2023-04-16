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

import javax.validation.constraints.NotNull;
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
                                    schema = @Schema(implementation = ResponseWrapperAds.class))
                    })
            })
    @GetMapping()
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        List<Ads> adsList = new ArrayList<>();
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds(adsList);
        return ResponseEntity.ok().body(responseWrapperAds);
    }

    @Operation(
            summary = "addAds",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = Ads.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ads> addAdds(@RequestPart CreateAds properties, @RequestPart MultipartFile image) {
        Ads ad = new Ads();
        return ResponseEntity.ok().body(ad);
    }

    @Operation(
            summary = "getComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperComment.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("ad_pk") String adPk) {
        List<Comment> commentsList = new ArrayList<>();
        ResponseWrapperComment comments = new ResponseWrapperComment(commentsList);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(
            summary = "addComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<Comment> addComments(@PathVariable("ad_pk") String adPk,
                                               @RequestBody Comment comment) {
        Comment newComment = new Comment();
        return ResponseEntity.ok().body(newComment);
    }

    @Operation(
            summary = "getFullAd",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = FullAds.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        FullAds newFullAds = new FullAds();
        return ResponseEntity.ok().body(newFullAds);
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
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = Ads.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable("id") Integer id, @RequestBody CreateAds createAds) {
        Ads newAds = new Ads();
        return ResponseEntity.ok().body(newAds);
    }

    @Operation(
            summary = "getComments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("ad_pk") String adPk, @PathVariable("id") int id) {
        Comment newComment = new Comment();
        return ResponseEntity.ok().body(newComment);
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
                            @Content(mediaType = MediaType.ALL_VALUE, schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable("ad_pk") String adPk,
                                                     @PathVariable("id") int id,
                                                     @RequestBody Comment comment) {
        Comment newComment = new Comment();
        return ResponseEntity.ok().body(newComment);
    }

    @Operation(
            summary = "getAdsMe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAds.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping(value = "/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestBody Authentication authentication) {
        List<Ads> adsList = new ArrayList<>();
        ResponseWrapperAds ads = new ResponseWrapperAds(adsList);
        return ResponseEntity.ok(ads);
    }
}
