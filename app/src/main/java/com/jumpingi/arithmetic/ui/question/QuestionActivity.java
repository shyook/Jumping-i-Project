package com.jumpingi.arithmetic.ui.question;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jumpingi.arithmetic.constants.Constant;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString(Constant.IntentParam.INTENT_KEY_MENU_TYPE);

        Log.i("TEST", "Type : " + type);
    }
}
