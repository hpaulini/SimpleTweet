package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"))
public class Tweet {

    @PrimaryKey
    @ColumnInfo
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public long userId;

    @Ignore
    public User user;

    @ColumnInfo
    public String media;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
            Tweet tweet = new Tweet();
            tweet.body = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.id = jsonObject.getLong("id");
            User user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.user = user;
            tweet.userId = user.id;
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
