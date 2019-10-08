package com.jumpingi.arithmetic.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;

import java.util.Random;

public class BaseActivity extends AppCompatActivity {
    protected int background;
    protected TextView mTitle;
    protected ImageView mBackBtn;

    protected void setHeader() {
        setHeader(-1);
    }

    protected void setHeader(int resId) {
        Random rand = new Random();
        background = Constant.BACKGROUND_GRADATION[rand.nextInt(Constant.TOTAL_BACKGROUND_GRADATION)];

        RelativeLayout titleBackground = findViewById(R.id.toolbar_title_area_rl);
        titleBackground.setBackgroundResource(background);

        mTitle = findViewById(R.id.title_tv);
        mBackBtn = findViewById(R.id.back_button_iv);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (resId > 0) {
            mTitle.setText(resId);
        }
    }
}
