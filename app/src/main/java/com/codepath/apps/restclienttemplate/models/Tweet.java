package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import androidx.room.ColumnInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public String media;
    public long id;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
            Tweet tweet = new Tweet();
            tweet.body = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.id = jsonObject.getLong("id");
            String mediaUrl = null;
            if(jsonObject.getJSONObject("entities").has("media")) {
                mediaUrl = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
            }
            if (!isEmpty(mediaUrl)) {
                tweet.media = mediaUrl;
            }
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i<jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
