package ru.aleksseii.library_manager_android.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import ru.aleksseii.library_manager_android.R;
import ru.aleksseii.library_manager_android.adapter.AuthorSpinnerAdapter;
import ru.aleksseii.library_manager_android.adapter.BookAdapter;
import ru.aleksseii.library_manager_android.adapter.GenreSpinnerAdapter;
import ru.aleksseii.library_manager_android.domain.Author;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.Genre;
import ru.aleksseii.library_manager_android.nodb.NoDb;
import ru.aleksseii.library_manager_android.rest.LibraryAPIVolley;


@SuppressWarnings("FieldCanBeLocal")
public class UpdateBookFragment extends Fragment {

    public static final int MAX_BOOK_NAME_LENGTH = 30;

    private AuthorSpinnerAdapter authorSpinnerAdapter;

    private GenreSpinnerAdapter genreSpinnerAdapter;

    private AppCompatSpinner spinnerAuthor;

    private AppCompatSpinner spinnerGenre;

    private AppCompatButton buttonChangeBook;

    private EditText etBookName;

    private Book book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_book, container, false);

        book = (Book) (getArguments() != null ?
                getArguments().getSerializable(BookAdapter.BOOK_KEY) : null);

        authorSpinnerAdapter = new AuthorSpinnerAdapter(this.requireContext(), NoDb.AUTHOR_LIST);
        genreSpinnerAdapter = new GenreSpinnerAdapter(this.requireContext(), NoDb.GENRE_LIST);

        spinnerAuthor = view.findViewById(R.id.spinner_new_author);
        spinnerGenre = view.findViewById(R.id.spinner_new_genre);
        buttonChangeBook = view.findViewById(R.id.confirm_changing);
        etBookName = view.findViewById(R.id.et_new_book_name);

        spinnerAuthor.setAdapter(authorSpinnerAdapter);
        spinnerGenre.setAdapter(genreSpinnerAdapter);

        if (book != null) {
            etBookName.setText(book.getName());
        }

        buttonChangeBook.setOnClickListener(getButtonChangeBookListener());

        return view;
    }

    @NonNull
    private View.OnClickListener getButtonChangeBookListener() {
        return (View view) -> {

            String newBookName = etBookName.getText().toString();

            if (newBookName.isEmpty() || newBookName.length() > MAX_BOOK_NAME_LENGTH) {

                String errorText = newBookName.isEmpty() ?
                        "Book requires name!" : "Your book name is too long!";

                etBookName.requestFocus();
                etBookName.setError(errorText);
                return;
            }

            String newAuthorName = ((Author) spinnerAuthor.getSelectedItem()).getName();
            String newGenreName = ((Genre) spinnerGenre.getSelectedItem()).getName();

            LibraryAPIVolley libraryAPIVolley = new LibraryAPIVolley(this.getContext());
            libraryAPIVolley.updateBook(
                    book.getId(),
                    newBookName,
                    newAuthorName,
                    newGenreName
            );

            this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .remove(UpdateBookFragment.this)
                    .commit();

            Toast.makeText(this.requireContext(),
                    "Book successfully updated!",
                    Toast.LENGTH_LONG).show();
        };
    }
}