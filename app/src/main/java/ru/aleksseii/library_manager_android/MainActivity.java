package ru.aleksseii.library_manager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ru.aleksseii.library_manager_android.domain.Author;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.Genre;
import ru.aleksseii.library_manager_android.nodb.NoDb;
import ru.aleksseii.library_manager_android.rest.LibraryAPIVolley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LibraryAPIVolley libraryAPIVolley = new LibraryAPIVolley(this);
        libraryAPIVolley.deleteBook(1L);
//        libraryAPIVolley.addBook(Book.builder()
//                .name("qwerty")
//                .author(new Author("First author name"))
//                .genre(new Genre("First genre name"))
//                .build());
    }
}