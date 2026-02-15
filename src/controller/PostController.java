package controller;

import domain.PostDomain;
import service.PostService;

import java.util.List;
import java.util.Optional;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) { //구글링
        this.postService = postService;
    }

    public PostDomain create(String title, String content, String author) {
        return postService.create(title, content, author);
    }

    public List<PostDomain> list() {
        return postService.list();
    }

    public Optional<PostDomain> findById(long id) {
        return postService.findById(id);
    }

    public boolean update(long id, String title, String content) {
        return postService.update(id, title, content);
    }

    public boolean delete(long id) {
        return postService.delete(id);
    }
}
