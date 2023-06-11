package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * Class - controller for working with an authorized user and his data, containing a set of API endpoints
 *
 * @see UserService
 * @see AuthService
 * @see ImageService
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ImageService imageService;

    @Operation(
            summary = "Password update",
            description = "Changing the user's password from the request body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. New password set"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
    }
    )
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        authService.changePassword(newPasswordDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get information about an authorized user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. User data received",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
    }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto currentUserDto = userService.getUserDto();
        return ResponseEntity.ok().body(currentUserDto);
    }

    @Operation(
            summary = "Update authorized user information",
            description = "Updating user data from the request body"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "OK. User data updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
    }
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto newUserDto = userService.updateUserDto(userDto);
        return ResponseEntity.ok().body(newUserDto);
    }

    @Operation(
            summary = "Update authorized user image",
            description = "Updating the user image from the request body"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. User image updated", content = {@Content()}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateUserImage(@RequestPart MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get user image",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }
}
