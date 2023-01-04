package com.example.apispringbootmongodb.config;

import com.example.apispringbootmongodb.domain.Post;
import com.example.apispringbootmongodb.domain.User;
import com.example.apispringbootmongodb.dto.AuthorDTO;
import com.example.apispringbootmongodb.dto.CommentDTO;
import com.example.apispringbootmongodb.repository.PostRepository;
import com.example.apispringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu porto velho", "Sdd da minha ex", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("21/03/2018"), "Partiu", "Sdd da minha ex", new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO("Topson hein", sdf.parse("21/03/2010"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aí é foda mesmo hein", sdf.parse("21/05/2020"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("O que é a vida", sdf.parse("22/04/2020"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

    }
}
