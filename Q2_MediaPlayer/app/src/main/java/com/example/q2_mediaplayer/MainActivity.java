package com.example.q2_mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;

    Button openFile, openUrl, play, pause, stop, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFile = findViewById(R.id.openFile);
        openUrl = findViewById(R.id.openUrl);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        restart = findViewById(R.id.restart);
        videoView = findViewById(R.id.videoView);

        // 🎵 AUDIO (sample.mp3 from res/raw)
        openFile.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.sample);
            mediaPlayer.start(); // ✅ IMPORTANT FIX
        });

        // 🎬 VIDEO (from URL)
        openUrl.setOnClickListener(v -> {
            String url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
            videoView.setVideoURI(Uri.parse(url));
            videoView.start(); // ✅ IMPORTANT FIX
        });

        // ▶ PLAY
        play.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.start();
            if (videoView != null) videoView.start();
        });

        // ⏸ PAUSE
        pause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
            if (videoView.isPlaying()) videoView.pause();
        });

        // ⏹ STOP
        stop.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            videoView.stopPlayback();
        });

        // 🔄 RESTART
        restart.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start(); // optional but better
            }
            videoView.seekTo(0);
            videoView.start(); // optional but better
        });
    }
}