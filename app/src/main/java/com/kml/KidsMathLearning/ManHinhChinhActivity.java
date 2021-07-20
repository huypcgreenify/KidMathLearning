package com.kml.KidsMathLearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ManHinhChinhActivity extends AppCompatActivity {
    ImageView imgHome,imgLoaManHinhChinh;
    ImageView imgCT,imgDV,imgTD,imgGT;
    FrameLayout frameLayoutCongTru, frameLayoutDoVui, frameLayoutTapDem, frameLayoutGiaiTri;
    static boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        //Ánh xạ
        imgHome = findViewById(R.id.imgHome);
        imgCT = findViewById(R.id.imgCongTru);
        imgDV = findViewById(R.id.imgDoVui);
        imgTD = findViewById(R.id.imgTapDem);
        imgGT = findViewById(R.id.imgGiaiTri);
        imgLoaManHinhChinh = findViewById(R.id.imgLoaMHChinh);
        frameLayoutCongTru = findViewById(R.id.frameLOCongtru);
        frameLayoutDoVui = findViewById(R.id.frameLODoVui);
        frameLayoutTapDem = findViewById(R.id.frameLOTapDem);
        frameLayoutGiaiTri = findViewById(R.id.frameLOGiaiTri);
        //Chuyển màn hình
        imgLoaManHinhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    ManHinhChaoActivity.mediaPlayer.start();
                    imgLoaManHinhChinh.setImageResource(R.drawable.hinhloa);
                    flag = false;
                } else {
                    ManHinhChaoActivity.mediaPlayer.pause();
                    imgLoaManHinhChinh.setImageResource(R.drawable.unloa);
                    flag = true;
                }
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhChinhActivity.this, ManHinhChaoActivity.class);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intent);
            }
        });
        //Cộng trừ
        frameLayoutCongTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCongTru = new Intent(ManHinhChinhActivity.this, CongTruActivity.class);
                imgCT.setImageResource(R.drawable.anhxanh);
                startActivity(intentCongTru);
            }
        });
        //Đố vui
        frameLayoutDoVui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDoVui = new Intent(ManHinhChinhActivity.this, DoVuiActivity.class);
                imgDV.setImageResource(R.drawable.anhxanh);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intentDoVui);
            }
        });
        //Tập đếm
        frameLayoutTapDem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTapDem = new Intent(ManHinhChinhActivity.this, TapDemActivity.class);
                imgTD.setImageResource(R.drawable.anhxanh);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intentTapDem);
            }
        });
        //Giải trí
        frameLayoutGiaiTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGiaiTri = new Intent(ManHinhChinhActivity.this, MHChaoGameActivity.class);
                imgGT.setImageResource(R.drawable.anhxanh);
                ManHinhChaoActivity.mediaPlayer.pause();
                startActivity(intentGiaiTri);
            }
        });

    }
}