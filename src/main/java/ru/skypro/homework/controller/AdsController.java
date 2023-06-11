package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

/**
 * Class - controller for working with ads and comments, containing a set of API endpoints
 *
 * @see AdService
 * @see CommentService
 * @see ImageService
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdService adService;
    private final CommentService commentService;
    private final ImageService imageService;

    @Operation(
            summary = "Get all ads",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    })
            })
    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAdsDto());
    }

    @Operation(
            summary = "Add ad",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@RequestPart CreateAdsDto properties, @RequestPart MultipartFile image) {
        return ResponseEntity.ok(adService.createAds(properties, image));
    }

    @Operation(
            summary = "Get comments of the ad",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperCommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Comments"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        ResponseWrapperCommentDto comments = commentService.getCommentsDto(id);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(
            summary = "Add comment to ad",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            },
            tags = "Comments"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = commentService.createCommentDto(id, commentDto);
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "Get information about the ad",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FullAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        return ResponseEntity.ok(adService.getFullAdDto(id));
    }

    @Operation(
            summary = "Remove ad",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        adService.removeAdDto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Update ad information",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @Parameter(required = true)
            @RequestBody CreateAdsDto createAdsDto) {
        return ResponseEntity.ok(adService.updateAdDto(id, createAdsDto));
    }

    @Operation(
            summary = "Remove comment",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            },
            tags = "Comments"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer adId,
            @Parameter(description = "id комментария", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer commentId) {
        if (commentService.removeCommentDto(adId, commentId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(
            summary = "Update comment",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Comments"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer adId,
            @Parameter(description = "id of the comment", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer commentId,
            @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = commentService.updateCommentDto(adId, commentId, commentDto);
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "Get authorized user ads",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            })
    @GetMapping(value = "/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        return ResponseEntity.ok(adService.getAllUserAdsDto());
    }

    @Operation(
            summary = "Update ad image",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    array = @ArraySchema(schema = @Schema(type = "string", format = "byte")))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(
            @Parameter(description = "id of the ad", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @Parameter(schema = @Schema(type = "string", format = "binary"))
            @RequestPart MultipartFile image) {
        adService.updateImageAdDto(id, image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get ad image",
            tags = "Ads",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }
}
