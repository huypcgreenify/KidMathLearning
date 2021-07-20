package com.kml.KidsMathLearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PhepCongActivity extends AppCompatActivity {
    ImageView imgLoa;
    ImageView imageViewQuayLai, imgDA1, imgs1, imgs2, imgDA2, imgDA3, imgDamMay1, imgDamMay2, imgDamMay3;
    int i = 1;
    int Tong = 2;
    int dung;
    int sai;
    TextView tvphepcong;
    static boolean flag = false;
    public static MediaPlayer mediaPlayer, mediaPlayer2,mediaPlayer3,mediaPlayer4,mediaPlayer5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phep_cong);
        //Ánh xạ
        imageViewQuayLai = findViewById(R.id.imgQuayLaiCuaMHPhepCong);
        imgDA1 = findViewById(R.id.imgDapAn1);
        imgDA2 = findViewById(R.id.imgDapAn2);
        imgDA3 = findViewById(R.id.imgDapAn3);
        imgs1 = findViewById(R.id.imgSoThuNhat);
        imgs2 = findViewById(R.id.imgSoThuHai);
        imgDamMay1 = findViewById(R.id.imgDamMay1);
        imgDamMay2 = findViewById(R.id.imgDamMay2);
        imgDamMay3 = findViewById(R.id.imgDamMay3);
        tvphepcong = findViewById(R.id.tvPhepCong);
        imgLoa = findViewById(R.id.imgLoaPhepCong);
        mediaPlayer = MediaPlayer.create(this, R.raw.amthanhhuongdan);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.amthanhdung);
        mediaPlayer4 = MediaPlayer.create(this, R.raw.amthanhsai);
        mediaPlayer5 = MediaPlayer.create(this,R.raw.chucmung);
        mediaPlayer.start();
        //Chuyển màn hình
        DialogHDLam();
        //Quay lại:
        imgLoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    ManHinhChaoActivity.mediaPlayer.start();
                    imgLoa.setImageResource(R.drawable.hinhloa);
                    flag = false;
                } else {
                    ManHinhChaoActivity.mediaPlayer.pause();
                    imgLoa.setImageResource(R.drawable.unloa);
                    flag = true;
                }
            }
        });
        imgDA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tong == 0 || Tong == 3 || Tong == 6 || Tong == 9) {
                    mediaPlayer3.start();
                    imgDamMay1.setImageResource(R.drawable.anhxanh);
                    imgDamMay2.setImageResource(R.drawable.maydo);
                    imgDamMay3.setImageResource(R.drawable.maydo);
                    dung++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                } else {
                    mediaPlayer4.start();
                    imgDamMay1.setImageResource(R.drawable.maydo);
                    sai++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                }
                Service();
            }
        });

        imgDA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tong == 1 || Tong == 4 || Tong == 7) {
                    mediaPlayer3.start();
                    imgDamMay2.setImageResource(R.drawable.anhxanh);
                    imgDamMay1.setImageResource(R.drawable.maydo);
                    imgDamMay3.setImageResource(R.drawable.maydo);
                    dung++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                } else {
                    mediaPlayer4.start();
                    imgDamMay2.setImageResource(R.drawable.maydo);
                    sai++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                }
                Service();
            }
        });
        imgDA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tong == 2 || Tong == 5 || Tong == 8) {
                    mediaPlayer3.start();
                    imgDamMay3.setImageResource(R.drawable.anhxanh);
                    imgDamMay2.setImageResource(R.drawable.maydo);
                    imgDamMay1.setImageResource(R.drawable.maydo);
                    dung++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                } else {
                    mediaPlayer4.start();
                    imgDamMay3.setImageResource(R.drawable.maydo);
                    sai++;
                    MyRunnable myRunnable = new MyRunnable();
                    myRunnable.start();
                }
                Service();
            }
        });
        imageViewQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManHinhChaoActivity.mediaPlayer.pause();
                mediaPlayer2 = MediaPlayer.create(PhepCongActivity.this, R.raw.quaylai);
                mediaPlayer2.start();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PhepCongActivity.this);
                alertDialogBuilder.setView(R.layout.dialog_quay_lai);
                alertDialogBuilder.setTitle("Bé chắc chứ ?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intentQuayLai = new Intent(PhepCongActivity.this, CongTruActivity.class);
                                ManHinhChaoActivity.mediaPlayer.start();
                                mediaPlayer2.pause();
                                startActivity(intentQuayLai);
                            }
                        })
                        .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ManHinhChaoActivity.mediaPlayer.start();
                                mediaPlayer2.pause();
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


    }

    public void DialogHDLam() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PhepCongActivity.this);
        alertDialogBuilder.setView(R.layout.dialog_hdphepcong);
        alertDialogBuilder.setTitle("Hướng dẫn");
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ManHinhChaoActivity.mediaPlayer.start();
                        mediaPlayer.pause();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public int myRandomso1() {
        int so1;
        Random random = new Random();
        so1 = random.nextInt(10);
        switch (so1) {
            case 0:
                imgs1.setImageResource(R.drawable.so0);
                break;
            case 1:
                imgs1.setImageResource(R.drawable.so1);
                break;
            case 2:
                imgs1.setImageResource(R.drawable.so2);
                break;
            case 3:
                imgs1.setImageResource(R.drawable.so3);
                break;
            case 4:
                imgs1.setImageResource(R.drawable.so4);
                break;
            case 5:
                imgs1.setImageResource(R.drawable.so5);
                break;
            case 6:
                imgs1.setImageResource(R.drawable.so6);
                break;
            case 7:
                imgs1.setImageResource(R.drawable.so7);
                break;
            case 8:
                imgs1.setImageResource(R.drawable.so8);
                break;
            case 9:
                imgs1.setImageResource(R.drawable.so9);
                break;
        }
        return so1;
    }

    public int myRandomso2() {
        int so2;
        Random random = new Random();
        so2 = random.nextInt(10);
        switch (so2) {
            case 0:
                imgs2.setImageResource(R.drawable.so0);
                break;
            case 1:
                imgs2.setImageResource(R.drawable.so1);
                break;
            case 2:
                imgs2.setImageResource(R.drawable.so2);
                break;
            case 3:
                imgs2.setImageResource(R.drawable.so3);
                break;
            case 4:
                imgs2.setImageResource(R.drawable.so4);
                break;
            case 5:
                imgs2.setImageResource(R.drawable.so5);
                break;
            case 6:
                imgs2.setImageResource(R.drawable.so6);
                break;
            case 7:
                imgs2.setImageResource(R.drawable.so7);
                break;
            case 8:
                imgs2.setImageResource(R.drawable.so8);
                break;
            case 9:
                imgs2.setImageResource(R.drawable.so9);
                break;
        }
        return so2;
    }

    public void myRandomDapAn(int Tong) {
        switch (Tong) {
            case 0:
                imgDA1.setImageResource(R.drawable.so0);
                break;
            case 1:
                imgDA2.setImageResource(R.drawable.so1);
                break;
            case 2:
                imgDA3.setImageResource(R.drawable.so2);
                break;
            case 3:
                imgDA1.setImageResource(R.drawable.so3);
                break;
            case 4:
                imgDA2.setImageResource(R.drawable.so4);
                break;
            case 5:
                imgDA3.setImageResource(R.drawable.so5);
                break;
            case 6:
                imgDA1.setImageResource(R.drawable.so6);
                break;
            case 7:
                imgDA2.setImageResource(R.drawable.so7);
                break;
            case 8:
                imgDA3.setImageResource(R.drawable.so8);
                break;
            case 9:
                imgDA1.setImageResource(R.drawable.so9);
                break;
        }
    }

    private class MyRunnable extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                imgDamMay3.setImageResource(R.drawable.dammay);
                imgDamMay2.setImageResource(R.drawable.dammay);
                imgDamMay1.setImageResource(R.drawable.dammay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Service() {
        do {
            Log.e("AAA", "SO1: " + myRandomso1());
            Log.e("BBB", "SO2: " + myRandomso2());
            Tong = myRandomso1() + myRandomso2();
        } while (Tong >= 10);
        myRandomDapAn(Tong);
        Log.e("BBB", "tong 3:  " + Tong);
        i++;
        tvphepcong.setText("Câu " + i + "/15");
        if (i > 15) {
            ManHinhChaoActivity.mediaPlayer.pause();
            mediaPlayer5.start();
            tvphepcong.setText("Kết thúc");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PhepCongActivity.this);
            final View view1 = LayoutInflater.from(PhepCongActivity.this).inflate(R.layout.dialog_ket_qua, null);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setView(view1);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            Button choiLai = view1.findViewById(R.id.Again);
            Button quayLai = view1.findViewById(R.id.menu);
            TextView cauDung = view1.findViewById(R.id.tvCauDung);
            TextView cauSai = view1.findViewById(R.id.tvCauSai);
            cauDung.setText("Số câu đúng: " + dung);
            cauSai.setText("Số câu sai: " + sai);
            choiLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i = 1;
                    dung = 0;
                    sai = 0;
                    tvphepcong.setText("Câu " + i + "/15");
                    alertDialog.cancel();
                    ManHinhChaoActivity.mediaPlayer.start();
                    mediaPlayer5.pause();
                }
            });
            quayLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PhepCongActivity.this, CongTruActivity.class);
                    ManHinhChaoActivity.mediaPlayer.start();
                    mediaPlayer5.pause();
                    startActivity(intent);

                }
            });

        }
        Log.e("AAA", "Đúng: " + dung);
        Log.e("AAA", "Sai: " + sai);
    }
}