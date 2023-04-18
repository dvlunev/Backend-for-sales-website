package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@Tag(name = "Изображения")

public class ImageController {

    @Operation(summary = "Изменение изображения объявления")
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
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content()})
    })
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(
            @Parameter(description = "id объявления", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int32"))
            @PathVariable int id,
            @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
