package ru.aleksseii.library_manager_android.domain.mapper;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.aleksseii.library_manager_android.domain.Comment;

public class CommentMapper {

    public static Comment commentFromJson(JSONObject commentJSON) throws JSONException {

        return new Comment(
                commentJSON.getInt("id"),
                commentJSON.getString("content")
        );
    }

    public static List<Comment> commentsFromBookJson(JSONObject bookJSON) throws JSONException {

        List<Comment> comments = new ArrayList<>();
        JSONArray commentsJson = bookJSON.getJSONArray("commentDTOList");
        for (int i = 0; i < commentsJson.length(); i++) {

            comments.add(commentFromJson(commentsJson.getJSONObject(i)));
        }

        return comments;
    }
}
