package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Author;

public class AuthorMapper {

    public static Author authorFromJson(JSONObject authorJson) throws JSONException {

        return new Author(
                authorJson.getInt("id"),
                authorJson.getString("name")
        );
    }

    public static Author authorFromBookJson(JSONObject bookJson) throws JSONException {

        return AuthorMapper.authorFromJson(bookJson.getJSONObject("authorDTO"));
    }
}
