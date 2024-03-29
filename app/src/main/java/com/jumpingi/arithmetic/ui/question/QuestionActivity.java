package com.jumpingi.arithmetic.ui.question;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.db.DatabaseHelper;
import com.jumpingi.arithmetic.ui.BaseActivity;
import com.jumpingi.arithmetic.ui.data.QuestionData;
import com.jumpingi.arithmetic.ui.question.factory.QuestionFactory;
import com.jumpingi.arithmetic.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionActivity extends BaseActivity {
    private RecyclerView mQuestionRecyclerView;
    private Button mConfirmBt, mRetryBt;
    private TextView mTotalScoreTv, mElapseTime;
    private LinearLayout mConfirmLl, mQuestionTimeLl;
    private Timer mTimer;
    private long mStartTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();
    }

    private void init() {
        setHeader(R.string.quiz);
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

        mConfirmBt = findViewById(R.id.answer_bt);
        mConfirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getResultValue().size() != adapter.getQuestionData().getOperatorResult().size()) {
                    // 빈칸이 있는데 그래도 채점할건지 묻는 팝업 디스플레이
                    DialogUtils.confirm(QuestionActivity.this, getString(R.string.popup_default_title)
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

        mConfirmLl = findViewById(R.id.answer_confirm_ll);
        mQuestionTimeLl = findViewById(R.id.question_time_ll);
        mRetryBt = findViewById(R.id.retry_bt);
        mTotalScoreTv = findViewById(R.id.score_tv);
        mElapseTime = findViewById(R.id.time_display_tv);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        };

        mTimer = new Timer();
        mTimer.schedule(timerTask, 0, 1000);
        mStartTime = System.currentTimeMillis();
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
        data.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        adapter.setQuestionData(data);
        confirmAnswerMode(totalScore, adapter);
        // 데이터 베이스 저장
        // TODO 2019.10.11 최초 채점시 한번만 데이터 베이스에 저장 하도록 수정 필요.
        DatabaseHelper.getInstance(this).getQuestionDao().insert(data);
    }

    private void confirmAnswerMode(int totalScore, final QuestionAdapter adapter) {
        // 타이머 스탑
        mTimer.cancel();
        // UI 변경
        mQuestionTimeLl.setVisibility(View.GONE);
        mConfirmLl.setVisibility(View.VISIBLE);
        mTotalScoreTv.setText(getString(R.string.total_score, totalScore));
        mRetryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionTimeLl.setVisibility(View.VISIBLE);
                mConfirmLl.setVisibility(View.GONE);
                // 틀린 문제 다시 풀기
                adapter.setRetry();
            }
        });

    }

    private void updateTime() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                long endTime = System.currentTimeMillis();
                mElapseTime.setText(getString(R.string.elapsed_time, sdf.format(endTime - mStartTime)));
            }
        });

    }
}
