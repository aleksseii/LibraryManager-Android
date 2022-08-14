package ru.aleksseii.library_manager_android.domain;

public class Comment {

    private long id;

    private String content;

    public Comment(long id, String content) {
        this(content);
        this.id = id;
    }

    public Comment(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
