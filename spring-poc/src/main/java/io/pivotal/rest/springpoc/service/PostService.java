package io.pivotal.rest.springpoc.service;

import io.pivotal.rest.springpoc.bean.Post;
import io.pivotal.rest.springpoc.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post){
        return postRepository.save(post);
    }


}
