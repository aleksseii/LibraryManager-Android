package ru.aleksseii.library_manager_android.rest;

import ru.aleksseii.library_manager_android.domain.Book;

public interface BookAPI {

    void fillBookList();

    void addBook(Book book);

    void updateBook(long id,
                    String newBookName,
                    String newAuthorName,
                    String newGenreName);

    void deleteBook(long id);
}
