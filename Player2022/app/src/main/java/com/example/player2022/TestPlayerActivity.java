package com.example.player2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.player2022.player.Player;

public class TestPlayerActivity extends AppCompatActivity {
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_player_layout);
        player = findViewById(R.id.player);
//        player.setPlayerListener(new Player.PlayerListener() {
//            @Override
//            public void pressPreviousButton() {
//
//            }
//
//            @Override
//            public void pressPlayButton() {
//
//            }
//
//            @Override
//            public void pressPauseButton() {
//
//            }
//
//            @Override
//            public void pressStopButton() {
//
//            }
//
//            @Override
//            public void pressNextButton() {
//
//            }
//        });
    }
}