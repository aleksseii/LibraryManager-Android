package ru.aleksseii.library_manager_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ru.aleksseii.library_manager_android.R;
import ru.aleksseii.library_manager_android.domain.Genre;
import ru.aleksseii.library_manager_android.nodb.NoDb;

public class GenreSpinnerAdapter extends ArrayAdapter<Genre> {

    public GenreSpinnerAdapter(@NonNull Context context,
                               @NonNull List<Genre> genres) {

        super(context, R.layout.spinner_item, genres);
    }

    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(this.getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        TextView tvSpinnerItem = convertView.findViewById(R.id.tv_spinner_item);
        tvSpinnerItem.setText(NoDb.GENRE_LIST.get(position).getName());

        return convertView;
    }
}
