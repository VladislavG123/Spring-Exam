package com.step.filmio.controllers;

import java.util.Date;

public class CommentViewModel {
    private Long id;

    private String content;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public CommentViewModel() {
    }

    public CommentViewModel(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }
}
