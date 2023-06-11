package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing information about an ad when creating or modifying
 */
@Data
public class CreateAdsDto {
    //ad description
    private String description;
    //ad price
    private int price;
    //ad header
    private String title;
}
