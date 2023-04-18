package ru.skypro.homework.entity;

public class Comment {
    private int id;
    private int adId; //id объявления
    private int authorId; //id автора комментария
    private String createdAt; //дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private String text;
}
