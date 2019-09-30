package com.jumpingi.arithmetic.ui.question;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;
import com.jumpingi.arithmetic.ui.question.factory.QuestionFactory;

public class QuestionActivity extends AppCompatActivity {
    private RecyclerView mQuestionRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        String type = null;
        if (bundle != null) {
            type = bundle.getString(Constant.IntentParam.INTENT_KEY_MENU_TYPE);
        }

        Log.i("TEST", "Type : " + type);
        QuestionFactory question = new QuestionFactory();
        QuestionData generationData = question.makeQuestion(this, type);


        mQuestionRecyclerView = findViewById(R.id.question_rv);
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        QuestionAdapter adapter = new QuestionAdapter(generationData);
        mQuestionRecyclerView.setAdapter(adapter);

        for (int i = 0; i < generationData.getOperandFirst().size(); i++) {
            String first = generationData.getOperandFirst().get(i);
            String second = generationData.getOperandSecond().get(i);
            Log.i("TEST", first + getString(generationData.getOperator()) + second + " = " + (Integer.valueOf(first) + Integer.valueOf(second)));
        }
    }
}
