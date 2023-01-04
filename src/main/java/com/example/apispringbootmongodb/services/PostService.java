package com.example.apispringbootmongodb.services;

import com.example.apispringbootmongodb.domain.Post;
import com.example.apispringbootmongodb.repository.PostRepository;
import com.example.apispringbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitle(text);
    }

}
