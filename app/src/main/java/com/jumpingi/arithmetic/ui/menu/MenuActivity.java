package com.jumpingi.arithmetic.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.ui.question.QuestionActivity;

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
        }

        this.startActivity(intent);
    }
}
