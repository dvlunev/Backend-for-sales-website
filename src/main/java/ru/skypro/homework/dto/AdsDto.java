package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    private int id;
    private int author; //id автора объявления
    private String image; //ссылка на картинку объявления
    private int price;
    private String title;
}
