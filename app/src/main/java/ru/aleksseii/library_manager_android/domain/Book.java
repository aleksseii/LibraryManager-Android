package ru.aleksseii.library_manager_android.domain;

import java.util.List;

public class Book {

    private long id;

    private String name;

    private Author author;

    private Genre genre;

    private List<Comment> comments;

    private Book() {
    }

    public Book(long id, String name, Author author, Genre genre, List<Comment> comments) {
        this(name, author, genre, comments);
        this.id = id;
    }

    public Book(String name, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public static Builder builder() {
        return new Book().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(long id) {
            Book.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Book.this.name = name;
            return this;
        }

        public Builder author(Author author) {
            Book.this.author = author;
            return this;
        }

        public Builder genre(Genre genre) {
            Book.this.genre = genre;
            return this;
        }

        public Builder comments(List<Comment> comments) {
            Book.this.comments = comments;
            return this;
        }

        public Book build() {
            return Book.this;
        }
    }
}
