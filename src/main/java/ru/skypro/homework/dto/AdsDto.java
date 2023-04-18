package ru.skypro.homework.dto;

import lombok.Data;


@Data
public class AdsDto {
    private int id;
    private int author; //id автора объявления
    private String image; //ссылка на картинку объявления
    private int price;
    private String title;
}
