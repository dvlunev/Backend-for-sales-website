package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @Operation(
            summary = "Получить все объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    })
            },
            tags = "Объявления"
    )
    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        List<AdsDto> adsDtoList = new ArrayList<>();
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto(adsDtoList);
        return ResponseEntity.ok().body(responseWrapperAdsDto);
    }

    @Operation(
            summary = "Добавить объявление",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
            },
            tags = "Объявления"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(
            @Parameter(required = true)
            @ModelAttribute CreateAdsDto properties,
            @Parameter(schema = @Schema(type = "string", format = "binary"))
            @RequestBody MultipartFile image) {
        AdsDto ad = new AdsDto();
        return ResponseEntity.status(201).body(ad);
    }

    @Operation(
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperCommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Комментарии"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        List<CommentDto> commentsList = new ArrayList<>();
        ResponseWrapperCommentDto comments = new ResponseWrapperCommentDto(commentsList);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            },
            tags = "Комментарии"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = new CommentDto();
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FullAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Объявления"
    )
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        FullAdsDto newFullAdsDto = new FullAdsDto();
        return ResponseEntity.ok().body(newFullAdsDto);
    }

    @Operation(
            summary = "Удалить объявление",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
            },
            tags = "Объявления"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content()),
            },
            tags = "Объявления"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @Parameter(required = true)
            @RequestBody CreateAdsDto createAdsDto) {
        AdsDto newAdsDto = new AdsDto();
        return ResponseEntity.ok().body(newAdsDto);
    }

    @Operation(
            summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            },
            tags = "Комментарии"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer adId,
            @Parameter(description = "id комментария", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Комментарии"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer adId,
            @Parameter(description = "id комментария", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer commentId,
            @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = new CommentDto();
        return ResponseEntity.ok().body(newCommentDto);
    }

    @Operation(
            summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
            },
            tags = "Объявления"
    )
    @GetMapping(value = "/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        List<AdsDto> adsDtoList = new ArrayList<>();
        ResponseWrapperAdsDto ads = new ResponseWrapperAdsDto(adsDtoList);
        return ResponseEntity.ok(ads);
    }

    @Operation(
            summary = "Обновить картинку объявления",
            tags = "Объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    array = @ArraySchema(schema = @Schema(type = "string", format = "byte"))
                            )
                    }),
            //возвращает файл
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable Integer id,
            @Parameter(schema = @Schema(type = "string", format = "binary"))
            @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
