package com.example.player2022.player;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.player2022.R;

public class Player extends LinearLayout {
    private ViewGroup playerLayout;
    private PlayerListener playerListener;

    public void setPlayerListener(PlayerListener playerListener) {
        this.playerListener = playerListener;
    }

    private OnClickListener onClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
//            Log.d("dd", "dddd");
            if (view.isSelected()) {
                view.setSelected(false);
            } else {
                view.setSelected(true);
                for (int i = 0; i < playerLayout.getChildCount(); i++) {
                    if (view.getId() != playerLayout.getChildAt(i).getId()) {
                        playerLayout.getChildAt(i).setSelected(false);
                    }
                }

            }

            //Event processing
            if (playerListener == null) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_layout);
                TextView tvNotify = dialog.findViewById(R.id.tvNotify);
                Button btnOk = dialog.findViewById(R.id.btnOK);
                Button btnCancle = dialog.findViewById(R.id.btnCancle);
                tvNotify.setText("You must implement the player");
                btnCancle.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                    }
                });

                btnOk.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                    }
                });
                dialog.show();
            } else {
                if (view.isSelected()){
//                    switch (view.getId()){
//                        case R.id.imgNext:
//                            playerListener.pressNextButton();
//                            break;
//                        case R.id.imgPause:
//                            playerListener.pressPauseButton();
//                            break;
//                        case R.id.imgPrevious:
//                            playerListener.pressPreviousButton();
//                            break;
//                        case R.id.imgPlay:
//                            playerListener.pressPlayButton();
//                            break;
//                        case R.id.imgStop:
//                            playerListener.pressStopButton();
//                            break;
//                    }
                    if (view.getId() == R.id.imgNext){
                        playerListener.pressNextButton();
                    }else if(view.getId() == R.id.imgPause){
                        playerListener.pressPauseButton();
                    }else if(view.getId() == R.id.imgPrevious){
                        playerListener.pressPreviousButton();
                    }else if(view.getId() == R.id.imgPlay){
                        playerListener.pressPlayButton();
                    }else if(view.getId() == R.id.imgStop){
                        playerListener.pressStopButton();
                    }
                }
            }
        }
    };

    //    Constructor
    public Player(Context context) {
        super(context);
        initialization(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialization(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization(context);
    }

    //    Initialization
    private void initialization(Context context) {
        inflate(context, R.layout.player_layout, this);
        playerLayout = (ViewGroup) getChildAt(0);
        ImageView imgPrev = findViewById(R.id.imgPrevious);
        ImageView imgPlay = findViewById(R.id.imgPlay);
        ImageView imgPause = findViewById(R.id.imgPause);
        ImageView imgStop = findViewById(R.id.imgStop);
        ImageView imgNext = findViewById(R.id.imgNext);
        imgPrev.setOnClickListener(onClick);
        imgPlay.setOnClickListener(onClick);
        imgPause.setOnClickListener(onClick);
        imgStop.setOnClickListener(onClick);
        imgNext.setOnClickListener(onClick);

    }

    public interface PlayerListener {
        public void pressPreviousButton();

        public void pressPlayButton();

        public void pressPauseButton();

        public void pressStopButton();

        public void pressNextButton();

    }
}
