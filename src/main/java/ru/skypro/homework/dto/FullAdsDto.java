package ru.skypro.homework.dto;

import lombok.Data;


@Data
public class FullAdsDto {
    private int id;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String phone;
    private String title;
    private String description;
    private String image;//ссылка на картинку объявления
    private int price;

}
