package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Genre;

public class GenreMapper {

    public static Genre genreFromJson(JSONObject genreJson) throws JSONException {

        return new Genre(
                genreJson.getInt("id"),
                genreJson.getString("name")
        );
    }

    public static Genre genreFromBookJson(JSONObject bookJson) throws JSONException {

        return GenreMapper.genreFromJson(bookJson.getJSONObject("genreDTO"));
    }
}
