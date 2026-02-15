package repository;

import domain.PostDomain;

import java.util.*;

public class MemoryPostRepository implements PostRepository {
    private final Map<Long, PostDomain> posts = new LinkedHashMap<>();
    private long num = 0L;

    @Override
    public List<PostDomain> findAll() {
        return  new ArrayList<>(posts.values());
    }

    @Override
    public Optional<PostDomain> findById(long id) {
        return  Optional.ofNullable(posts.get(id));
    }

    @Override
    public void save(PostDomain post) {
        posts.put(post.getId(), post);
    }


    @Override
    public void deleteById(long id) {
        posts.remove(id);

    }

    @Override
    public PostDomain create(String title, String content, String author) {
        long id = ++num;
        PostDomain post = PostDomain.create(id, title, content, author);
        save(post);
        return post;
    }
}
