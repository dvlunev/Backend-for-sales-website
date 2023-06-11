package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

/**
 * DTO class for passing a list of comments of the ad
 */
@Data
public class ResponseWrapperCommentDto {
    //total comments count
    private int count;
    private Collection<CommentDto> results;

    public ResponseWrapperCommentDto(Collection<CommentDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
