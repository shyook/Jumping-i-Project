package com.jumpingi.arithmetic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mAddBt, mSubtractionBt, mMultiplyBt, mDivideBt, mFractionBt, mQuizBt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
    }

    private void init() {
        mAddBt = findViewById(R.id.menu_addition_bt);
        mSubtractionBt = findViewById(R.id.menu_subtraction_bt);
        mMultiplyBt = findViewById(R.id.menu_multiply_bt);
        mDivideBt = findViewById(R.id.menu_divide_bt);
        mFractionBt = findViewById(R.id.menu_fraction_bt);
        mQuizBt = findViewById(R.id.menu_game_bt);

        mAddBt.setOnClickListener(this);
        mSubtractionBt.setOnClickListener(this);
        mMultiplyBt.setOnClickListener(this);
        mDivideBt.setOnClickListener(this);
        mFractionBt.setOnClickListener(this);
        mQuizBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent  = new Intent(MenuActivity.this, QuestionActivity.class);
        switch(view.getId()) {
                // 더하기
            case R.id.menu_addition_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_addition));
                break;

                // 빼기
            case R.id.menu_subtraction_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_subtraction));
                break;

                // 곱하기
            case R.id.menu_multiply_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_multiply));
                break;

                // 나누기
            case R.id.menu_divide_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_divide));
                break;

                // 분수
            case R.id.menu_fraction_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_fraction));
                break;

                // 게임
            case R.id.menu_game_bt:
                intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, getString(R.string.menu_mensa));
                break;
        }

        this.startActivity(intent);
    }
}
