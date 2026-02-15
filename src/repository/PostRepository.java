package repository;

import domain.PostDomain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<PostDomain> findAll();
    Optional<PostDomain> findById(long id);
    void save(PostDomain post);
    void deleteById(long id);
    PostDomain create(String title,String content,String author);
}
