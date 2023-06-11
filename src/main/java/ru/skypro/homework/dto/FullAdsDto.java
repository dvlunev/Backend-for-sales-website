package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing full information about an ad
 */
@Data
public class FullAdsDto {
    //ad id
    private int pk;
    //author`s ad name
    private String authorFirstName;
    //author`s ad surname
    private String authorLastName;
    //author`s ad login
    private String email;
    //author`s ad phone
    private String phone;
    //ad header
    private String title;
    //ad description
    private String description;
    //image link
    private String image;
    //ad price
    private int price;

}
