package com.jumpingi.arithmetic.ui.question.factory;

import android.content.Context;

import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;

public class QuestionFactory {

    public QuestionData makeQuestion(Context oContext, String strType) {
        Question question = getQuestionFactory(getType(oContext, strType));
        return question.doGenerationOperand(Constant.TOTAL_QUESTION_GENERATE);
    }

    private Question.QUESTION_TYPE getType(Context oContext, String strType) {
        for (Question.QUESTION_TYPE type : Question.QUESTION_TYPE.values()) {
            String strQuestionType = oContext.getResources().getString(type.getMainMenu()) + oContext.getResources().getString(type.getSubMenu());

            if (strQuestionType.equals(strType)) {
                return type;
            }
        }

        return Question.QUESTION_TYPE.QUESTION_TYPE_ADD_UNIT_1;
    }

    private Question getQuestionFactory(Question.QUESTION_TYPE type) {
        switch (type) {
            case QUESTION_TYPE_ADD_UNIT_1:
                return new AddUnit1Question();

            case QUESTION_TYPE_ADD_UNIT_10:
                return new AddUnit10Question();

            case QUESTION_TYPE_ADD_UNIT_100:
                return new AddUnit100Question();

            case QUESTION_TYPE_SUB_UNIT_1:
                return new SubUnit1Question();

            case QUESTION_TYPE_SUB_UNIT_10:
                return new SubUnit10Question();

            case QUESTION_TYPE_SUB_UNIT_100:
                return new SubUnit100Question();

            case QUESTION_TYPE_MULTI_UNIT_1:
                return new MulUnit1Question();

            case QUESTION_TYPE_MULTI_UNIT_10:
                return new MulUnit10Question();

            case QUESTION_TYPE_MULTI_UNIT_100:
                return new MulUnit100Question();

            case QUESTION_TYPE_DIVIDE_UNIT_1:
                return new DivUnit1Question();

            case QUESTION_TYPE_DIVIDE_UNIT_10:
                return new DivUnit10Question();

            case QUESTION_TYPE_DIVIDE_UNIT_100:
                return new DivUnit100Question();

            case QUESTION_TYPE_FRACTION_ADD:
                return new AddUnit1Question();

            case QUESTION_TYPE_FRACTION_SUB:
                return new AddUnit1Question();

            case QUESTION_TYPE_QUIZ:
                return new AddUnit1Question();

            default:
                return new AddUnit1Question();

        }
    }
}
