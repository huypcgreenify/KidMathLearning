package com.kml.KidsMathLearning;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MHChinhGameActivity extends AppCompatActivity {
    private static final String KEY_NAME = "name";
    private static final String KEY_NEW = "mysp";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private static boolean temp = true;
    private static int scores = 0;
    Intent context;
    ImageView quaylai, reset;
    SharedPreferences sharedPreferences;
    String highpoint;
    private ImageView box11, box12, box13;
    private ImageView box21, box22, box23;
    private ImageView box31, box32, box33;
    private TextView text11, text12, text13;
    private TextView text21, text22, text23;
    private TextView text31, text32, text33;
    private LinearLayout khungman;
    private int matrix[][];
    private TextView tvDiemso, tvDiemCao;
    private MediaPlayer mediaPlayer0, mediaPlayer1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2048);
        context = getIntent();
        anhxa();
        khoiTao();
        sharedPreferences = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);

        if (temp==true){
//            tvDiemCao.setText(sharedPreferences.getString("text", ""));
            tvDiemCao.setText("" + 0);//temp=0
            DialogHDLam();
        }else {
            tvDiemCao.setText(sharedPreferences.getString("name", ""));//temp==false
        }
//        else {
//            tvDiemCao.setText("" + scores);//temp=0
//            DialogHDLam();
//        }
        sharedPreferences = getSharedPreferences(KEY_NEW, MODE_PRIVATE);

        final GestureDetector gestureDetector = new GestureDetector(this, new CuChiManHinh());
        khungman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MHChinhGameActivity.this, ManHinhChinhActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    public void khoiTao() {
        scores = 0;
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = 0;
        }
        randdomnumber();
        randdomnumber();
        setBOX();
    }

    public void randdomnumber() {
        Random random = new Random();
        for (int m = 0; m < 9; m++) {
            int i = random.nextInt(2) + 1;
            int j = random.nextInt(2) + 1;
            if (matrix[i][j] == 0) {
                if (random.nextInt(11) < 10) {
                    matrix[i][j] = 2;
                } else {
                    matrix[i][j] = 4;
                }
                break;
            }
        }

    }

    public void gameOver() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (matrix[i][j] == 0)
                    return;
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrix[i][j] == matrix[i][j + 1]) check = true; //van cong them dc
                if (matrix[j][i] == matrix[j + 1][i]) check = true;
            }
        }

        if (check == false) {
            Dialog dialog = new Dialog(this);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.game_over);
            final Button btnChoiLai = (Button) dialog.findViewById(R.id.btnChoiLai);
            final Button btnThoat = (Button) dialog.findViewById(R.id.btnThoat);
            TextView diemso1 = (TextView) dialog.findViewById(R.id.diemsoOver);
            TextView diemcao1 = (TextView) dialog.findViewById(R.id.diemcaoOver);

            tvDiemso.setText("" + scores);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, String.valueOf(scores));
            highpoint = sharedPreferences.getString(KEY_NAME, null);
            editor.apply();

            if (scores > Integer.parseInt(highpoint)) {
                editor.putString(KEY_NAME, String.valueOf(scores));
                editor.apply();
                diemso1.setText("" + scores);
                if (highpoint != null) {
                    highpoint = sharedPreferences.getString(KEY_NAME, null);
                    tvDiemCao.setText("" + scores);
                    diemcao1.setText("" + scores);
                }
                sharedPreferences = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("text", tvDiemCao.getText().toString()).apply();
            } else {
                editor.putString(KEY_NAME, String.valueOf(highpoint));
                editor.apply();
                diemso1.setText("" + scores);
                if (highpoint != null) {
                    highpoint = sharedPreferences.getString(KEY_NAME, null);
                    tvDiemCao.setText("" + highpoint);
                    diemcao1.setText("" + highpoint);
                }
                sharedPreferences = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("text", tvDiemCao.getText().toString()).apply();
            }

            btnChoiLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    temp=false;
                    Intent intent = new Intent(MHChinhGameActivity.this,MHChinhGameActivity.class);
                    startActivity(intent);
                }
            });
            btnThoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MHChinhGameActivity.this,ManHinhChinhActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }

    }

    public void resetGame() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.amthanhchoilai);
        mediaPlayer1.start();
        builder.setMessage("Bạn có muốn chơi lại!!").setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                temp = false;
                mediaPlayer1.pause();
                khoiTao();
            }
        })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();
    }

    public void DialogHDLam() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MHChinhGameActivity.this);
        alertDialogBuilder.setView(R.layout.dialog_hdgame);
        mediaPlayer0 = MediaPlayer.create(this, R.raw.amthanhhuongdangame);
        mediaPlayer0.start();
        alertDialogBuilder.setTitle("Hướng dẫn");
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mediaPlayer0.pause();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void anhxa() {
        matrix = new int[3][3];
        tvDiemso = (TextView) findViewById(R.id.diemso);
        tvDiemCao = (TextView) findViewById(R.id.diemcao);
        khungman = (LinearLayout) findViewById(R.id.khungchoi);

        box11 = (ImageView) findViewById(R.id.box11);
        box12 = (ImageView) findViewById(R.id.box12);
        box13 = (ImageView) findViewById(R.id.box13);

        box21 = (ImageView) findViewById(R.id.box21);
        box22 = (ImageView) findViewById(R.id.box22);
        box23 = (ImageView) findViewById(R.id.box23);

        box31 = (ImageView) findViewById(R.id.box31);
        box32 = (ImageView) findViewById(R.id.box32);
        box33 = (ImageView) findViewById(R.id.box33);

        text11 = (TextView) findViewById(R.id.text11);
        text12 = (TextView) findViewById(R.id.text12);
        text13 = (TextView) findViewById(R.id.text13);

        text21 = (TextView) findViewById(R.id.text21);
        text22 = (TextView) findViewById(R.id.text22);
        text23 = (TextView) findViewById(R.id.text23);

        text31 = (TextView) findViewById(R.id.text31);
        text32 = (TextView) findViewById(R.id.text32);
        text33 = (TextView) findViewById(R.id.text33);

        quaylai = (ImageView) findViewById(R.id.exitGame);
        reset = (ImageView) findViewById(R.id.resetGame);
    }

    public void setBOX() {
        tvDiemso.setText(String.valueOf(scores));
        if (temp ==true){
            tvDiemCao.setText("" + scores);
        }
        box11.setImageResource(getBackground(matrix[0][0]));
        box12.setImageResource(getBackground(matrix[0][1]));
        box13.setImageResource(getBackground(matrix[0][2]));

        box21.setImageResource(getBackground(matrix[1][0]));
        box22.setImageResource(getBackground(matrix[1][1]));
        box23.setImageResource(getBackground(matrix[1][2]));

        box31.setImageResource(getBackground(matrix[2][0]));
        box32.setImageResource(getBackground(matrix[2][1]));
        box33.setImageResource(getBackground(matrix[2][2]));

        String matrixS[][] = new String[3][3];
        for (int i = 0; i < 9; i++) {
            if (matrix[i / 3][i % 3] == 0) {
                matrixS[i / 3][i % 3] = "";
            } else {
                matrixS[i / 3][i % 3] = String.valueOf(matrix[i / 3][i % 3]);
            }
        }

        setSizetext();

        text11.setText(matrixS[0][0]);
        text12.setText(matrixS[0][1]);
        text13.setText(matrixS[0][2]);

        text21.setText(matrixS[1][0]);
        text22.setText(matrixS[1][1]);
        text23.setText(matrixS[1][2]);

        text31.setText(matrixS[2][0]);
        text32.setText(matrixS[2][1]);
        text33.setText(matrixS[2][2]);
    }

    public void setSizetext() {
        if (matrix[0][0] <= 512) text11.setTextSize(35);
        else text11.setTextSize(25);
        if (matrix[1][0] <= 512) text21.setTextSize(35);
        else text21.setTextSize(25);
        if (matrix[2][0] <= 512) text31.setTextSize(35);
        else text31.setTextSize(25);


        if (matrix[0][1] <= 512) text12.setTextSize(35);
        else text12.setTextSize(25);
        if (matrix[1][1] <= 512) text22.setTextSize(35);
        else text22.setTextSize(25);
        if (matrix[2][1] <= 512) text32.setTextSize(35);
        else text32.setTextSize(25);


        if (matrix[0][2] <= 512) text13.setTextSize(35);
        else text13.setTextSize(25);
        if (matrix[1][2] <= 512) text23.setTextSize(35);
        else text23.setTextSize(25);
        if (matrix[2][2] <= 512) text33.setTextSize(35);
        else text33.setTextSize(25);

    }

    public int getBackground(int n) {
        if (n == 0) return R.drawable.box0;
        switch (n) {
            case 2:
                return R.drawable.box2;
            case 4:
                return R.drawable.box4;
            case 8:
                return R.drawable.box8;
            case 16:
                return R.drawable.box16;
            case 32:
                return R.drawable.box32;
            case 64:
                return R.drawable.box64;
            case 128:
                return R.drawable.box128;
            case 256:
                return R.drawable.box256;
            case 512:
                return R.drawable.box512;
            case 1024:
                return R.drawable.box1024;
            case 2048:
                return R.drawable.box2048;
        }
        return 0;
    }

    class CuChiManHinh extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
            boolean check = false;
            float diffY = moveEvent.getY() - downEvent.getY();//thao tac tha-thao tac bat dau
            float diffX = moveEvent.getX() - downEvent.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) { //thao tac vuot ngang lon hon thao  tac vuot doc
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    //thao tac vuot && toc do vuot >100
                    if (diffX > 0) {
                        onSwiperRight();
                    } else {
                        onSwiperLeft();
                    }
                    randdomnumber();
                    gameOver();
                    setBOX();
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwiperBottom();
                    } else {
                        onSwiperTop();
                    }
                    randdomnumber();
                    gameOver();
                    setBOX();
                }
            }
            if (check == true) {
                randdomnumber();
            }
            gameOver();
            setBOX();

            return super.onFling(downEvent, moveEvent, velocityX, velocityY);
        }

        private void onSwiperTop() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[j][i] != 0) {
                        for (int k = j + 1; k < 3; k++) {
                            if (matrix[k][i] == matrix[j][i]) {
                                matrix[j][i] += matrix[k][i];
                                scores += matrix[j][i];
                                matrix[k][i] = 0;
                                j = k;
                                break;
                            }
                            if (matrix[k][i] != 0) break;
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    if (matrix[j][i] == 0) {
                        for (int k = j + 1; k < 3; k++) {
                            if (matrix[k][i] != 0) {
                                matrix[j][i] = matrix[k][i];
                                matrix[k][i] = 0;

                                break;
                            }
                        }
                    }
                }
            }
        }

        private void onSwiperLeft() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] != 0) {
                        for (int k = j + 1; k < 3; k++) {
                            if (matrix[i][k] == matrix[i][j]) {
                                matrix[i][j] += matrix[i][k];
                                scores += matrix[i][j];
                                matrix[i][k] = 0;
                                j = k;

                                break;
                            } else if (matrix[i][k] != 0) break;
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] == 0) {
                        for (int k = j + 1; k < 3; k++) {
                            if (matrix[i][k] != 0) {
                                matrix[i][j] = matrix[i][k];
                                matrix[i][k] = 0;

                                break;
                            }
                        }
                    }
                }
            }
        }

        private void onSwiperBottom() {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {
                    if (matrix[j][i] != 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (matrix[k][i] == matrix[j][i]) {
                                matrix[j][i] += matrix[k][i];
                                scores += matrix[j][i];
                                matrix[k][i] = 0;
                                j = k;

                                break;
                            } else if (matrix[k][i] != 0) break;
                        }
                    }
                }
                for (int j = 2; j >= 0; j--) {
                    if (matrix[j][i] == 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (matrix[k][i] != 0) {
                                matrix[j][i] = matrix[k][i];
                                matrix[k][i] = 0;

                                break;
                            }
                        }
                    }
                }
            }
        }

        private void onSwiperRight() {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {

                    if (matrix[i][j] != 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (matrix[i][k] == matrix[i][j]) {
                                matrix[i][j] += matrix[i][k];
                                scores += matrix[i][j];
                                matrix[i][k] = 0;
                                j = k;
                                break;
                            } else if (matrix[i][k] != 0) break;
                        }
                    }
                }
                for (int j = 2; j >= 0; j--) {
                    if (matrix[i][j] == 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (matrix[i][k] != 0) {
                                matrix[i][j] = matrix[i][k];
                                matrix[i][k] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}