package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

/**
 * DTO class for passing list of ads
 */
@Data
public class ResponseWrapperAdsDto {
    //total ads count
    private int count;
    private Collection<AdsDto> results;

    public ResponseWrapperAdsDto(Collection<AdsDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
