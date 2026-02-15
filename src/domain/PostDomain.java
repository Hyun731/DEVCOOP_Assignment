package domain;

public class PostDomain {
    private long id;
    private String author;
    private String title;
    private String content;

    private PostDomain() {}

    public static PostDomain create(long id, String title, String content,String author) {
        PostDomain postDomain = new PostDomain();
        postDomain.id = id;
        postDomain.title = title;
        postDomain.content = content;
        postDomain.author = author;
        return postDomain;
    }

    public void update(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
