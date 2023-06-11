package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing information about an ad
 */
@Data
public class AdsDto {
    //ad id
    private int pk;
    //ad author`s id
    private int author;
    //image link
    private String image;
    //ad price
    private int price;
    //ad header
    private String title;
}
