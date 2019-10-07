package com.jumpingi.arithmetic.ui.question;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;
import com.jumpingi.arithmetic.utils.UiUtils;
import com.jumpingi.arithmetic.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private Context mContext;
    private QuestionData mQuestionData;             // 문제 데이터 클래스
    private Map<Integer, String> mResultValue;
    private Constant.QUESTION_MODE mMode = Constant.QUESTION_MODE.QUESTION_MODE_IN_PROGRESS;    // 문제를 풀고 있는지, 채점 중인지

    QuestionAdapter(QuestionData data) {
        mQuestionData = data;
        mResultValue = new HashMap<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_question, parent, false);
        QuestionAdapter.ViewHolder vh = new QuestionAdapter.ViewHolder(view, new CustomEditTextListener());
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.firstOperand.setText(mQuestionData.getOperandFirst().get(position));
        holder.secondOperand.setText(mQuestionData.getOperandSecond().get(position));
        holder.operator.setText(UiUtils.convertNameToSign(mQuestionData.getOperator(), mContext));
        holder.customEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.resultEt.setText(mResultValue.get(holder.getAdapterPosition()));

        // 자리수가 많아 지는 항목에 대해 UI Width 변경.
        if (mQuestionData.getOperator() == R.string.menu_multiply && mQuestionData.getUnit() == Constant.UNIT_TYPE.UNIT_TYPE_10) {
            holder.resultEt.setMinWidth(Utils.getDpToPixel(mContext, 60));
        } else if (mQuestionData.getOperator() == R.string.menu_multiply && mQuestionData.getUnit() == Constant.UNIT_TYPE.UNIT_TYPE_100) {
            holder.resultEt.setMinWidth(Utils.getDpToPixel(mContext, 80));
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(6);
            holder.resultEt.setFilters(FilterArray);
        } else if (mQuestionData.getOperator() == R.string.menu_addition && mQuestionData.getUnit() == Constant.UNIT_TYPE.UNIT_TYPE_100) {
            holder.resultEt.setMinWidth(Utils.getDpToPixel(mContext, 60));
        }

        // 채점 중인 경우 정답 오답 표시.
        if (mMode == Constant.QUESTION_MODE.QUESTION_MODE_CONFIRM_ANSWER || mMode == Constant.QUESTION_MODE.QUESTION_MODE_RETRY) {
            holder.confirmAnswer.setVisibility(View.VISIBLE);
            String strAnswer = mResultValue.get(position);
            if (TextUtils.isEmpty(strAnswer)) {
                strAnswer = "";
            }
            holder.resultEt.setText(strAnswer);
            // 정답 확인 시에는 에디트 안되도록.
            if (mMode == Constant.QUESTION_MODE.QUESTION_MODE_CONFIRM_ANSWER) {
                holder.resultEt.setEnabled(false);
            }

            if (mQuestionData.getRight().get(position)) {
                holder.confirmAnswer.setImageResource(R.drawable.checked);
            } else {
                if (mMode == Constant.QUESTION_MODE.QUESTION_MODE_RETRY) {
                    holder.resultEt.setEnabled(true);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mQuestionData.getOperandFirst().size();
    }

    /**
     * 연산 결과를 반환.
     *
     * @return
     */
    public Map<Integer, String> getResultValue() {
        return mResultValue;
    }

    /**
     * 문제 데이터를 반환.
     *
     * @return
     */
    public QuestionData getQuestionData() {
        return mQuestionData;
    }

    /**
     * 정답 확인.
     * @param data
     */
    public void setQuestionData(QuestionData data) {
        mQuestionData = data;
        mMode = Constant.QUESTION_MODE.QUESTION_MODE_CONFIRM_ANSWER;
        notifyDataSetChanged();
    }

    public void setRetry() {
        mMode = Constant.QUESTION_MODE.QUESTION_MODE_RETRY;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstOperand, secondOperand, operator;
        ImageView confirmAnswer;
        AppCompatEditText resultEt;
        CustomEditTextListener customEditTextListener;

        ViewHolder(View itemView, CustomEditTextListener customEditTextListener) {
            super(itemView);

            firstOperand = itemView.findViewById(R.id.first_operand_tv);
            secondOperand = itemView.findViewById(R.id.second_operand_tv);
            operator = itemView.findViewById(R.id.operator_tv);
            resultEt = itemView.findViewById(R.id.result_et);
            resultEt.addTextChangedListener(customEditTextListener);
            confirmAnswer = itemView.findViewById(R.id.answer_confirm_iv);
            this.customEditTextListener = customEditTextListener;
        }

    }

    private class CustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 결과를 저장 하기 위해 텍스트 변경에 대해 저장 한다.
            if (mMode == Constant.QUESTION_MODE.QUESTION_MODE_IN_PROGRESS || mMode == Constant.QUESTION_MODE.QUESTION_MODE_RETRY) {
                String strValue = editable.toString();
                mResultValue.put(position, strValue);
            }
        }
    }
}
