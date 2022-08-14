package ru.aleksseii.library_manager_android.domain;

public class Author {

    private long id;

    private String name;

    public Author(long id, String name) {
        this(name);
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
