package ru.aleksseii.library_manager_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import ru.aleksseii.library_manager_android.adapter.BookAdapter;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.nodb.NoDb;
import ru.aleksseii.library_manager_android.rest.LibraryAPIVolley;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvBooks;

    private BookAdapter bookAdapter;

    private ItemTouchHelper.SimpleCallback simpleCallback;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LibraryAPIVolley libraryAPIVolley = new LibraryAPIVolley(this);
        libraryAPIVolley.fillBookList();

        rvBooks = findViewById(R.id.rv_books);
        bookAdapter = new BookAdapter(this, NoDb.BOOK_LIST);
        rvBooks.setAdapter(bookAdapter);

        initSimpleCallbackWith(libraryAPIVolley);

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvBooks);
    }

    private void initSimpleCallbackWith(LibraryAPIVolley libraryAPIVolley) {

        simpleCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.ACTION_STATE_IDLE,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,int direction) {

                Book book = NoDb.BOOK_LIST.get(viewHolder.getAdapterPosition());

                if (direction == ItemTouchHelper.LEFT) {

                    libraryAPIVolley.deleteBook(book.getId());
                }
            }
        };
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateAdapter() {

        bookAdapter.notifyDataSetChanged();
    }
}
