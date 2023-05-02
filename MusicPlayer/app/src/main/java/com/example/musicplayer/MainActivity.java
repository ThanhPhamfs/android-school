package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.player2022.player.Player;

public class MainActivity extends AppCompatActivity {
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = findViewById(R.id.player);
        player.setPlayerListener(new Player.PlayerListener() {
            @Override
            public void pressPreviousButton() {

            }

            @Override
            public void pressPlayButton() {

            }

            @Override
            public void pressPauseButton() {

            }

            @Override
            public void pressStopButton() {

            }

            @Override
            public void pressNextButton() {

            }
        });
    }
}