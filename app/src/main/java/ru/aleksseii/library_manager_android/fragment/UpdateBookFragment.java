package ru.aleksseii.library_manager_android.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.aleksseii.library_manager_android.R;
import ru.aleksseii.library_manager_android.adapter.BookAdapter;
import ru.aleksseii.library_manager_android.domain.Book;


public class UpdateBookFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_book, container, false);

        Book book = (Book) (this.getArguments() != null ? this.getArguments().getSerializable(BookAdapter.BOOK_KEY) : null);

        Toast.makeText(this.getContext(),
                        book != null ? book.toString() : "null",
                        Toast.LENGTH_LONG).show();

        return view;
    }
}