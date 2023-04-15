package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperComment {
    private int count;
    private Collection<Comment> results;

    public ResponseWrapperComment(Collection<Comment> results) {
        this.count = results.size();
        this.results = results;
    }
}
