package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Genre;

public class GenreMapper {

    public static Genre genreFromJson(JSONObject genreJSON) throws JSONException {

        return new Genre(
                genreJSON.getInt("id"),
                genreJSON.getString("name")
        );
    }

    public static Genre genreFromBookJson(JSONObject bookJSON) throws JSONException {

        return GenreMapper.genreFromJson(bookJSON.getJSONObject("genreDTO"));
    }
}
