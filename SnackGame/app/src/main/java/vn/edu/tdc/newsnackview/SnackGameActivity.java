package vn.edu.tdc.newsnackview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.snackgame.R;

import vn.edu.tdc.newsnackview.views.Snake;

public class SnackGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create snack view
        Snake view = new Snake(this,getResources().getColor(R.color.bugColor, getTheme()), getResources().getColor(R.color.foodColor, getTheme()));
        view.setBackgroundColor(getResources().getColor(R.color.bgrMainView, getTheme()));
        setContentView(view);
    }
}