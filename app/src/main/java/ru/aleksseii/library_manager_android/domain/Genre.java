package ru.aleksseii.library_manager_android.domain;

import androidx.annotation.NonNull;

public class Genre {

    private long id;

    private String name;

    public Genre(long id, String name) {
        this(name);
        this.id = id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
