package com.jumpingi.arithmetic.ui.question.factory;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddUnit1Question extends Question {
    QuestionData result = new QuestionData();
    List<Integer> arrFirst = new ArrayList<>();
    List<Integer> arrSecond = new ArrayList<>();

    @Override
    QuestionData doGenerationOperand(int num) {
        Random random = new Random();
        int first, second;
        int generatingCount = 0;
        while (num != generatingCount) {
            first = random.nextInt(Constant.UNIT_TYPE.UNIT_TYPE_1);
            second = random.nextInt(Constant.UNIT_TYPE.UNIT_TYPE_1);

            if (checkOperand(first, second)) {
                arrFirst.add(first);
                arrSecond.add(second);
                generatingCount++;
            }
        }

        result.setOperandFirst(convertIntegerToString(arrFirst));
        result.setOperandSecond(convertIntegerToString(arrSecond));
        result.setOperator(R.string.menu_addition);

        return result;
    }

    @Override
    boolean checkOperand(int first, int second) {
        // 0인지 체크
        if (first == 0 || second== 0) {
            return false;
        }

        // 만들어진 문제중 같은 문제가 있는지 체크.
        for (int i = 0; i < arrFirst.size(); i++) {
            if (first == arrFirst.get(i)) {
                if (second == arrSecond.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}