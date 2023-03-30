package com.ayseozcan.service;

import com.ayseozcan.repository.PostRepository;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {

        this.postRepository = new PostRepository();
    }
    public void getAllTweet(){
        postRepository.getAllTweet();
    }
}
