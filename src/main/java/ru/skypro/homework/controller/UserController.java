package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Обновление пароля",
            description = "Изменение пароля пользователя из тела запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Установлен новый пароль"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto passwords) {
        NewPasswordDto newPassword = new NewPasswordDto();
        return ResponseEntity.ok().body(newPassword);
    }


    @Operation(
            summary = "Получить информацию об авторизованном пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Данные пользователя получены",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        if (!userService.isAuth())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        //условие для ответа 403 - Нет прав с учетом информации об авторизации
        // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Optional<UserDto> currentUserDto = userService.getUserDto();
        if (currentUserDto.isPresent()) {
            return ResponseEntity.ok().body(currentUserDto.get());
        } else return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Обновить информацию об авторизованном пользователе",
            description = "Обновление данных пользователя из тела запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "OK. Данные пользователя обновлены",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content()}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        if (!userService.isAuth())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        //условие для ответа 403 - Нет прав с учетом информации об авторизации
        // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Optional<UserDto> currentUserDto = userService.getUserDto();
        //сравниваем текущего авторизованного пользователя и userDto
        if (currentUserDto.equals(Optional.of(userDto)))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        Optional<UserDto> newUserDto = userService.updateUserDto(userDto);
        if (newUserDto.isPresent()) {
            return ResponseEntity.ok().body(newUserDto.get());
        } else return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Обновить аватар авторизованного пользователя",
            description = "Обновление изображения пользователя из тела запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Изображение пользователя обновлено", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content()})
    }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(
            @Parameter(schema = @Schema(type = "string", format = "binary"))
            @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
