package ru.aleksseii.library_manager_android.nodb;

import java.util.ArrayList;
import java.util.List;

import ru.aleksseii.library_manager_android.domain.Book;

public class NoDb {

    private NoDb() {
    }

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final List<Book> AUTHOR_LIST = new ArrayList<>();

    public static final List<Book> GENRE_LIST = new ArrayList<>();

    public static final List<Book> COMMENT_LIST = new ArrayList<>();
}
