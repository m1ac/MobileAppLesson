package com.example.mobileapplessoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class ExoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();
        StyledPlayerView styledPlayerView = findViewById(R.id.exoPlayerView);
        styledPlayerView.setPlayer(exoPlayer);

        exoPlayer.setMediaItem(MediaItem.fromUri("https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/720/Big_Buck_Bunny_720_10s_1MB.mp4"));
        exoPlayer.prepare();
        exoPlayer.play();
    }
}