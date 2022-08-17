package ru.aleksseii.library_manager_android.rest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.mapper.BookMapper;
import ru.aleksseii.library_manager_android.nodb.NoDb;

public class LibraryAPIVolley implements BookAPI, AuthorAPI, GenreAPI {

    private static final String TAG = "LibraryAPIVolley";

    // getting ip-address: in cmd enter 'ipconfig' and get value of IPv4-address from there
    // ':{port}' where 'port' is port of working server
    public static final String BASE_URL = "http://192.168.163.3:8081";

    private final Context context;

    private final Response.ErrorListener errorListener;

    public LibraryAPIVolley(Context context) {
        this.context = context;

        errorListener = error -> Log.d(TAG, error.toString());
    }

    @Override
    public void fillBookList() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/book");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                getFillBookListListener(),
                errorListener
        );

        requestQueue.add(jsonArrayRequest);
    }

    @NonNull
    private Response.Listener<JSONArray> getFillBookListListener() {
        return (JSONArray response) -> {

            NoDb.BOOK_LIST.clear();

            try {

                for (int i = 0; i < response.length(); i++) {

                    JSONObject bookJson = response.getJSONObject(i);
                    Book book = BookMapper.bookFromJson(bookJson);
                    NoDb.BOOK_LIST.add(book);
                }
                Log.d(TAG, NoDb.BOOK_LIST.toString());

            } catch (JSONException jsonEx) {
                jsonEx.printStackTrace();
            }
        };
    }

    @Override
    public void fillAuthorList() {

    }

    @Override
    public void fillGenreList() {

    }

    @Override
    public void addBook(Book book) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/book");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                getAddBookListener(),
                errorListener) {

            @NonNull
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("book", book.getName());
                params.put("author", book.getAuthor().getName());
                params.put("genre", book.getGenre().getName());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    @NonNull
    private Response.Listener<String> getAddBookListener() {
        return (String response) -> {

            fillBookList();
            Log.d(TAG, response);
        };
    }

    @Override
    public void updateBook(long id, String newBookName, String newAuthorName, String newGenreName) {

    }

    @Override
    public void deleteBook(long id) {

    }
}
