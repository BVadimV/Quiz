package com.bvv.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    int numLeft;
    int numRight;
    Array array = new Array();
    Random random = new Random();
    Dialog dialog;
    Dialog dialogEnd;
    public int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView textLevel = findViewById(R.id.text_levels);
        textLevel.setText(R.string.level2);

        final ImageView imgLeft = (ImageView) findViewById(R.id.img_left);
        imgLeft.setClipToOutline(true);// Скругление картинки

        final ImageView imgRight = (ImageView) findViewById(R.id.img_right);
        imgRight.setClipToOutline(true);

        // Text for images
        final TextView textLeft = (TextView) findViewById(R.id.text_left);
        final TextView textRight = (TextView) findViewById(R.id.text_right);

        //развернуть игру на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this); // create new dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // title invisible
        dialog.setContentView(R.layout.previewdialog); // path to dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // устанавливаем прозрачность заднего фона
        dialog.setCancelable(false); // запрещаем выход при нажатии кнопки назад

        // устанавливаем картинку диалога
        ImageView previewimgTwo = dialog.findViewById(R.id.previewimg);
        previewimgTwo.setImageResource(R.drawable.previewimgtwo);

        // устанавливаем текст диалога
        TextView textDescription = dialog.findViewById(R.id.textdescription);
        textDescription.setText(R.string.leveltwo);

        //Кнопка закрывающая диалоговое окно
        TextView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish(); // При переходе назад, нужно зактыть Активность
                }catch(Exception e){}
                dialog.dismiss();
            }
        });

        Button btnContinue = (Button) dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //Закрываем диалоговое окно
            }
        });

        dialog.show();

        //вызов диалогового окна в конце уровня.
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); // задает растягивание для диалогового окна
        dialogEnd.setCancelable(false);

        // изменение текста в диалоговом окне.
        TextView textDescriptionEnd = (TextView) dialogEnd.findViewById(R.id.textdescriptionend);
        textDescriptionEnd.setText(R.string.leveltwoEnd);

        //Кнопка закрывающая диалоговое окно
        TextView btnClose2 = dialogEnd.findViewById(R.id.btn_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish(); // При переходе назад, нужно зактыть Активность
                }catch(Exception e){}
                dialogEnd.dismiss();
            }
        });

        Button btnContinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch(Exception e){}
                dialogEnd.dismiss(); //Закрываем диалоговое окно
            }
        });

        //---------------------------------------------------


        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4,
                R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11,
                R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19,
                R.id.point20
        };

        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        numLeft = random.nextInt(10); // Random for numLeft
        imgLeft.setImageResource(array.images2[numLeft]);
        textLeft.setText(array.texts2[numLeft]);

        do{numRight = random.nextInt(10);
        } while(numLeft == numRight);

        imgRight.setImageResource(array.images2[numRight]);
        textRight.setText(array.texts2[numRight]);

        // обрабатывание нажатия на левую картинку.
        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    // Касание
                    imgRight.setEnabled(false);// Блокируем правую картинку
                    if(numLeft>numRight){
                        imgLeft.setImageResource(R.drawable.img_true);
                    } else{
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                } if(event.getAction()==MotionEvent.ACTION_UP){
                    // Отжатие пальца
                    if(numLeft>numRight){
                        if(count < 20){
                            ++count;
                        }
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }
                            count -=2;
                        }
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20){
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10); // Random for numLeft
                        imgLeft.setImageResource(array.images2[numLeft]);
                        imgLeft.startAnimation(a);
                        textLeft.setText(array.texts2[numLeft]);

                        do{ numRight = random.nextInt(10);
                        } while(numLeft == numRight);

                        imgRight.setImageResource(array.images2[numRight]);
                        imgRight.startAnimation(a);
                        textRight.setText(array.texts2[numRight]);

                        imgRight.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // обрабатывание нажатия на правую картинку.
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    // Касание
                    imgLeft.setEnabled(false);// Блокируем правую картинку
                    if(numLeft<numRight){
                        imgRight.setImageResource(R.drawable.img_true);
                    } else{
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                } if(event.getAction()==MotionEvent.ACTION_UP){
                    // Отжатие пальца
                    if(numLeft<numRight){
                        if(count < 20){
                            ++count;
                        }
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }
                            count -=2;
                        }
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    // последний этап уровня
                    if(count == 20){
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10); // Random for numLeft
                        imgLeft.setImageResource(array.images2[numLeft]);
                        imgLeft.startAnimation(a);
                        textLeft.setText(array.texts2[numLeft]);

                        do{ numRight = random.nextInt(10);
                        } while(numLeft == numRight);

                        imgRight.setImageResource(array.images2[numRight]);
                        imgRight.startAnimation(a);
                        textRight.setText(array.texts2[numRight]);

                        imgLeft.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch(Exception e){

        }
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(Level2.this, GameLevels.class);
        startActivity(intent);
        finish();
    }
}
