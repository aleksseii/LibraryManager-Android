package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Book;

public class BookMapper {

    public static Book bookFromJson(JSONObject bookJson) throws JSONException {

        return Book.builder()
                .id(bookJson.getInt("id"))
                .name(bookJson.getString("name"))
                .author(AuthorMapper.authorFromBookJson(bookJson))
                .genre(GenreMapper.genreFromBookJson(bookJson))
                .comments(CommentMapper.commentsFromBookJson(bookJson))
                .build();
    }
}
