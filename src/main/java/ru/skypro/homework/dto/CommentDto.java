package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing information about a comment
 */
@Data
public class CommentDto {
    //comment id
    private int pk;
    //comment author`s id
    private int author;
    //link commenter`s image
    private String authorImage;
    //commenter`s name
    private String authorFirstName;
    //comment`s date and time creating in milliseconds from 00:00:00 01.01.1970
    private long createdAt;
    //comment text
    private String text;
}
