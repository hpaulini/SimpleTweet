package com.codepath.apps.restclienttemplate;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcel;
import org.parceler.Parcels;

import static android.text.TextUtils.isEmpty;

public class TweetDetails extends AppCompatActivity {

    Tweet tweet;

    ImageView ivProfileImageDetail;
    TextView tvBodyDetail;
    TextView tvScreenNameDetail;
    TextView tvNameDetail;
    TextView tvDateDetail;
    ImageView ivMediaDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_details);
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        ivProfileImageDetail = (ImageView) findViewById(R.id.ivProfileImageDetail);
        tvBodyDetail = (TextView) findViewById(R.id.tvBodyDetail);
        tvScreenNameDetail = (TextView) findViewById(R.id.tvScreenNameDetail);
        tvNameDetail = (TextView) findViewById(R.id.tvNameDetail);
        tvDateDetail = (TextView) findViewById(R.id.tvDateDetail);
        ivMediaDetail = (ImageView) findViewById(R.id.ivMediaDetail);

        tvBodyDetail.setText(tweet.body);
        tvScreenNameDetail.setText("@"+tweet.user.screenName);
        tvNameDetail.setText(tweet.user.name);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImageDetail);
        tvDateDetail.setText(getRelativeTimeAgo(tweet.createdAt));
        if(!isEmpty(tweet.media)){
            Glide.with(this).load(tweet.media).into(ivMediaDetail);
            ivMediaDetail.setVisibility(View.VISIBLE);
        } else{
            ivMediaDetail.setVisibility(View.GONE);
        }
      }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String formattedTime = TimeFormatter.getTimeDifference(rawJsonDate);
        return formattedTime;
    }
}
