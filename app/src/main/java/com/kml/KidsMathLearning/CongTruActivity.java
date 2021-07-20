package com.kml.KidsMathLearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CongTruActivity extends AppCompatActivity {
    ImageView imgLoa;
    ImageView imgC,imgT;
    ImageView imageViewQuayLai;
    FrameLayout frameLayoutPhepCong, frameLayoutPhepTru;
    static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_tru);
        //Ánh xạ
        imgLoa  = findViewById(R.id.imgLoaMHCongTru);
        imageViewQuayLai = findViewById(R.id.imgQuayLaiCuaMHCongTru);
        imgC = findViewById(R.id.imgCong);
        imgT = findViewById(R.id.imgTru);
        frameLayoutPhepCong = findViewById(R.id.frameLOPhepCong);
        frameLayoutPhepTru = findViewById(R.id.frameLOPhepTru);
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
        //Chuyển màn hình
        //Quay lại:
        imageViewQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentQuayLai = new Intent(CongTruActivity.this, ManHinhChinhActivity.class);
                ManHinhChaoActivity.mediaPlayer.start();
                startActivity(intentQuayLai);
            }
        });
        //Phép cộng:
        frameLayoutPhepCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhepCong = new Intent(CongTruActivity.this, PhepCongActivity.class);
                imgC.setImageResource(R.drawable.anhxanh);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intentPhepCong);
            }
        });
        //Phép trừ:
        frameLayoutPhepTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhepTru = new Intent(CongTruActivity.this, PhepTruActivity.class);
                imgT.setImageResource(R.drawable.anhxanh);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intentPhepTru);
            }
        });

    }
}