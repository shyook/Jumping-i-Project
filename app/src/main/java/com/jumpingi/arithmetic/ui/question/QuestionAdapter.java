package com.jumpingi.arithmetic.ui.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.ui.data.QuestionData;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private QuestionData mQuestionData;
    private Context mContext;

    QuestionAdapter(QuestionData data) {
        mQuestionData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_question, parent, false);
        QuestionAdapter.ViewHolder vh = new QuestionAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstOperand.setText(mQuestionData.getOperandFirst().get(position));
        holder.secondOperand.setText(mQuestionData.getOperandSecond().get(position));
        holder.operator.setText(mContext.getString(mQuestionData.getOperator()));

    }

    @Override
    public int getItemCount() {
        return mQuestionData.getOperandFirst().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstOperand, secondOperand, operator;
        AppCompatEditText resultEt;

        ViewHolder(View itemView) {
            super(itemView);

            firstOperand = itemView.findViewById(R.id.first_operand_tv);
            secondOperand = itemView.findViewById(R.id.second_operand_tv);
            operator = itemView.findViewById(R.id.operator_tv);
            resultEt = itemView.findViewById(R.id.result_et);
        }


    }
}
