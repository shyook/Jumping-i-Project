package com.jumpingi.arithmetic.ui.question;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;
import com.jumpingi.arithmetic.ui.question.factory.QuestionFactory;
import com.jumpingi.arithmetic.utils.DialogUtils;

import java.util.Arrays;
import java.util.Map;

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
        final QuestionAdapter adapter = new QuestionAdapter(generationData);
        mQuestionRecyclerView.setAdapter(adapter);

        for (int i = 0; i < generationData.getOperandFirst().size(); i++) {
            String first = generationData.getOperandFirst().get(i);
            String second = generationData.getOperandSecond().get(i);
            Log.i("TEST", first + getString(generationData.getOperator()) + second + " = " + (Integer.valueOf(first) + Integer.valueOf(second)));
        }

        Button confirm = findViewById(R.id.answer_bt);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getResultValue().size() != adapter.getQuestionData().getOperatorResult().size()) {
                    // 빈칸이 있는데 그래도 채점할건지 묻는 팝업 디스플레이
                    DialogUtils.alert(QuestionActivity.this, getString(R.string.popup_default_title)
                            , getString(R.string.empty_answer), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 정답을 맞추고 확인 버턴을 누른 경우 히스토리 저장 후 이전 화면으로 이동.
                                    DialogUtils.dismissDialog();
                                    if (which == DialogInterface.BUTTON_POSITIVE) {
                                        checkAndNotify(adapter);
                                        return;
                                    }
                                }
                            });
                } else {
                    checkAndNotify(adapter);
                }
            }
        });
    }

    private void checkAndNotify(QuestionAdapter adapter) {
        int totalScore = 0;
        Map<Integer, String> confirmValue = adapter.getResultValue();
        QuestionData data = adapter.getQuestionData();
        Boolean[] arrResult = new Boolean[data.getOperatorResult().size()];
        Arrays.fill(arrResult, false);

        for (int key : confirmValue.keySet()) {
            Log.i("TEST", "Key : " + key + "  value : " + confirmValue.get(key));
            if (data.getOperatorResult().get(key).equals(confirmValue.get(key))) {
                arrResult[key] = true;
                totalScore += 10;
            }
        }

        data.setRight(Arrays.asList(arrResult));
        data.setScore(totalScore);
        adapter.setQuestionData(data);
    }
}
