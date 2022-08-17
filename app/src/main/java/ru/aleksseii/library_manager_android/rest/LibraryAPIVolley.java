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

import ru.aleksseii.library_manager_android.domain.Author;
import ru.aleksseii.library_manager_android.domain.Book;
import ru.aleksseii.library_manager_android.domain.Genre;
import ru.aleksseii.library_manager_android.domain.mapper.AuthorMapper;
import ru.aleksseii.library_manager_android.domain.mapper.BookMapper;
import ru.aleksseii.library_manager_android.domain.mapper.GenreMapper;
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

                    JSONObject bookJSON = response.getJSONObject(i);
                    Book book = BookMapper.bookFromJson(bookJSON);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/author");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                getFillAuthorListListener(),
                errorListener
        );

        requestQueue.add(jsonArrayRequest);
    }

    @NonNull
    private Response.Listener<JSONArray> getFillAuthorListListener() {
        return (JSONArray response) -> {

            NoDb.AUTHOR_LIST.clear();

            try {

                for (int i = 0; i < response.length(); i++) {

                    JSONObject authorJSON = response.getJSONObject(i);
                    Author author = AuthorMapper.authorFromJson(authorJSON);
                    NoDb.AUTHOR_LIST.add(author);
                }
                Log.d(TAG, NoDb.AUTHOR_LIST.toString());

            } catch (JSONException jsonEX) {
                jsonEX.printStackTrace();
            }
        };
    }

    @Override
    public void fillGenreList() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/genre");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                getFillGenreListListener(),
                errorListener
        );

        requestQueue.add(jsonArrayRequest);
    }

    @NonNull
    private Response.Listener<JSONArray> getFillGenreListListener() {
        return (JSONArray response) -> {

            try {

                for (int i = 0; i < response.length(); i++) {

                    JSONObject genreJSON = response.getJSONObject(i);
                    Genre genre = GenreMapper.genreFromJson(genreJSON);
                    NoDb.GENRE_LIST.add(genre);
                }
                Log.d(TAG, NoDb.GENRE_LIST.toString());

            } catch (JSONException jsonEx) {
                jsonEx.printStackTrace();
            }
        };
    }

    @Override
    public void addBook(Book book) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/book");

        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                getUpdateBookListListener(),
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

        requestQueue.add(postRequest);
    }

    @Override
    public void updateBook(long id, String newBookName, String newAuthorName, String newGenreName) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/book/").concat(Long.toString(id));

        StringRequest putRequest = new StringRequest(
                Request.Method.PUT,
                url,
                getUpdateBookListListener(),
                errorListener) {

            @NonNull
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("book", newBookName);
                params.put("author", newAuthorName);
                params.put("genre", newGenreName);

                return params;
            }
        };

        requestQueue.add(putRequest);
    }

    @Override
    public void deleteBook(long id) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("/book/").concat(Long.toString(id));

        StringRequest deleteRequest = new StringRequest(
                Request.Method.DELETE,
                url,
                getUpdateBookListListener(),
                errorListener
        );

        requestQueue.add(deleteRequest);
    }

    @NonNull
    private Response.Listener<String> getUpdateBookListListener() {
        return (String response) -> {

            fillBookList();
            Log.d(TAG, response);
        };
    }
}
