package ru.aleksseii.library_manager_android.domain.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import ru.aleksseii.library_manager_android.domain.Book;

public class BookMapper {

    public static Book bookFromJson(JSONObject bookJSON) throws JSONException {

        return Book.builder()
                .id(bookJSON.getInt("id"))
                .name(bookJSON.getString("name"))
                .author(AuthorMapper.authorFromBookJson(bookJSON))
                .genre(GenreMapper.genreFromBookJson(bookJSON))
                .comments(CommentMapper.commentsFromBookJson(bookJSON))
                .build();
    }
}
