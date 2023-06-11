package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for passing data during user password change
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDto {
    //current password
    private String currentPassword;
    //new password
    private String newPassword;
}
