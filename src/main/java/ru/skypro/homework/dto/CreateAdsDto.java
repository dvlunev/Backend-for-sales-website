package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс DTO для передачи информации об объявлении при создании или изменении
 */
@Data
public class CreateAdsDto {
    //описание объявления
    private String description;
    //цена объявления
    private int price;
    //заголовок объявления
    private String title;
}
