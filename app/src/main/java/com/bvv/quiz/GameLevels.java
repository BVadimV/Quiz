package com.bvv.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClickBack(View view) {
        Intent intentBack = new Intent(this, MainActivity.class);
        startActivity(intentBack);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent intentBack = new Intent(this, MainActivity.class);
        startActivity(intentBack);
        finish();
    }

    public void onClickFirstLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level1.class);
        startActivity(intent);
        finish();
    }

    public void onClickSecondLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level2.class);
        startActivity(intent);
        finish();
    }

    public void onClickThirdLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level3.class);
        startActivity(intent);
        finish();
    }

    public void onClickFourthLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level4.class);
        startActivity(intent);
        finish();
    }

    public void onClickFifthLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level5.class);
        startActivity(intent);
        finish();
    }

    public void onClickSixthLevel(View view) {
        Intent intent = new Intent(GameLevels.this, Level6.class);
        startActivity(intent);
        finish();
    }
}
