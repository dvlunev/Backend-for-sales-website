package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

/**
 * Класс DTO для передачи списка объявлений
 */
@Data
public class ResponseWrapperAdsDto {
    //общее количество объявлений
    private int count;
    private Collection<AdsDto> results;

    public ResponseWrapperAdsDto(Collection<AdsDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
