package com.ayseozcan.controller;

import com.ayseozcan.service.PostService;

public class PostController {

    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    public void getAllTweet(){
        postService.getAllTweet();
    }
}
