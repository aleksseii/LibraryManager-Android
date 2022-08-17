package ru.aleksseii.library_manager_android.nodb;

import java.util.ArrayList;
import java.util.List;

import ru.aleksseii.library_manager_android.domain.Author;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.Comment;
import ru.aleksseii.library_manager_android.domain.Genre;

public class NoDb {

    private NoDb() {
    }

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final List<Author> AUTHOR_LIST = new ArrayList<>();

    public static final List<Genre> GENRE_LIST = new ArrayList<>();

    public static final List<Comment> COMMENT_LIST = new ArrayList<>();
}
