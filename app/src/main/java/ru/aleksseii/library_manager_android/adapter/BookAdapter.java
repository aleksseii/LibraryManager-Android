package ru.aleksseii.library_manager_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.aleksseii.library_manager_android.R;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.nodb.NoDb;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private final LayoutInflater inflater;

    private final List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;

        this.inflater = LayoutInflater.from(context);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvAuthor;
        private final TextView tvGenre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_book_name);
            tvAuthor = itemView.findViewById(R.id.tv_author_name);
            tvGenre = itemView.findViewById(R.id.tv_genre_name);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.book_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Book book = NoDb.BOOK_LIST.get(position);

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(book.getName());
        myViewHolder.tvAuthor.setText(book.getAuthor().getName());
        myViewHolder.tvGenre.setText(book.getGenre().getName());
    }

    @Override
    public int getItemCount() {

        return bookList.size();
    }
}
