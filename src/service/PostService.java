package service;

import domain.PostDomain;
import repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) { //구글링
        this.postRepository = postRepository;
    }

    public PostDomain create(String title, String content, String author) {
        return postRepository.create(title, content, author);
    }

    public List<PostDomain> list() {
        return postRepository.findAll();
    }

    public Optional<PostDomain> findById(long id) {
        return postRepository.findById(id);
    }

    public boolean update(long id, String title, String content) {
        Optional<PostDomain> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            return false;
        }

        PostDomain post = postOpt.get();
        post.update(title, content);
        postRepository.save(post);
        return true;
    }

    public boolean delete(long id) {
        Optional<PostDomain> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            return false;
        }

        postRepository.deleteById(id);
        return true;
    }
}
