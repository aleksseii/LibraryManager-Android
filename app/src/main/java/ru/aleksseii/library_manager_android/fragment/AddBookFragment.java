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
import ru.aleksseii.library_manager_android.adapter.GenreSpinnerAdapter;
import ru.aleksseii.library_manager_android.domain.Author;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.Genre;
import ru.aleksseii.library_manager_android.nodb.NoDb;
import ru.aleksseii.library_manager_android.rest.LibraryAPIVolley;


@SuppressWarnings("FieldCanBeLocal")
public class AddBookFragment extends Fragment {

    private AuthorSpinnerAdapter authorSpinnerAdapter;

    private GenreSpinnerAdapter genreSpinnerAdapter;

    private AppCompatSpinner spinnerAuthor;

    private AppCompatSpinner spinnerGenre;

    private AppCompatButton buttonConfirmAddingBook;

    private EditText etBookName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        authorSpinnerAdapter = new AuthorSpinnerAdapter(this.requireContext(), NoDb.AUTHOR_LIST);
        genreSpinnerAdapter = new GenreSpinnerAdapter(this.requireContext(), NoDb.GENRE_LIST);

        spinnerAuthor = view.findViewById(R.id.spinner_author);
        spinnerGenre = view.findViewById(R.id.spinner_genre);
        buttonConfirmAddingBook = view.findViewById(R.id.confirm_adding);
        etBookName = view.findViewById(R.id.et_book_name);

        spinnerAuthor.setAdapter(authorSpinnerAdapter);
        spinnerGenre.setAdapter(genreSpinnerAdapter);

        buttonConfirmAddingBook.setOnClickListener(getButtonConfirmAddingBookListener());

        return view;
    }

    @NonNull
    private View.OnClickListener getButtonConfirmAddingBookListener() {
        return (View view) -> {

            String bookName = etBookName.getText().toString();

            if (bookName.isEmpty() ||
                    bookName.length() > UpdateBookFragment.MAX_BOOK_NAME_LENGTH) {

                String errorText = bookName.isEmpty() ?
                        "New book requires name!" : "Your book name is too long!";

                etBookName.requestFocus();
                etBookName.setError(errorText);
                return;
            }

            Author author = (Author) spinnerAuthor.getSelectedItem();
            Genre genre = (Genre) spinnerGenre.getSelectedItem();
            Book book = Book.builder()
                    .name(bookName)
                    .author(author)
                    .genre(genre)
                    .build();

            LibraryAPIVolley libraryAPIVolley = new LibraryAPIVolley(this.getContext());
            libraryAPIVolley.addBook(book);

            this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .remove(AddBookFragment.this)
                    .commit();

            Toast.makeText(this.requireContext(),
                    "New book successfully added!",
                    Toast.LENGTH_LONG).show();
        };
    }
}
