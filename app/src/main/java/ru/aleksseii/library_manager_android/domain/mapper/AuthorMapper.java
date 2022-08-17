package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Author;

public class AuthorMapper {

    public static Author authorFromJson(JSONObject authorJSON) throws JSONException {

        return new Author(
                authorJSON.getInt("id"),
                authorJSON.getString("name")
        );
    }

    public static Author authorFromBookJson(JSONObject bookJSON) throws JSONException {

        return AuthorMapper.authorFromJson(bookJSON.getJSONObject("authorDTO"));
    }
}
