package com.amigoscode.post;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

// TODO: 14 - Create a REST client to call the jsonplaceholder API:
//  - Create a RestClient bean or use RestClient.create("https://jsonplaceholder.typicode.com")
//  - Add a method getPosts() that calls GET /posts and returns List<Post>
//  - Add a method getPostById(Long id) that calls GET /posts/{id} and returns Post
//  Hint:
//    RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
//    Post post = restClient.get()
//        .uri("/posts/{id}", id)
//        .retrieve()
//        .body(Post.class);

@RestController
public class PostClient {

    private final RestClient restClient;

    public PostClient() {
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    }

    public List<Post> getPosts(){
        return List.of(Objects.requireNonNull(restClient.get()
                .uri("/posts")
                .retrieve()
                .body(Post[].class)));
    }

    public Post getPostById(Long id){
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(Post.class);
    }

}
