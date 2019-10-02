package com.jumpingi.arithmetic.ui.question.factory;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.data.QuestionData;

import java.util.Random;

public class DivUnit1Question extends Question {

    @Override
    QuestionData doGenerationOperand(int num) {
        Random random = new Random();
        int first, second;
        int generatingCount = 0;
        while (num != generatingCount) {
            first = random.nextInt(Constant.UNIT_TYPE.UNIT_TYPE_2);
            second = random.nextInt(Constant.UNIT_TYPE.UNIT_TYPE_1);

            if (checkOperand(first, second)) {
                arrFirst.add(first);
                arrSecond.add(second);
                arrResult.add(first - second);
                generatingCount++;
            }
        }

        result.setOperandFirst(convertIntegerToString(arrFirst));
        result.setOperandSecond(convertIntegerToString(arrSecond));
        result.setOperatorResult(convertIntegerToString(arrResult));
        result.setOperator(R.string.menu_divide);
        result.setUnit(Constant.UNIT_TYPE.UNIT_TYPE_1);

        return result;
    }

    @Override
    boolean checkOperand(int first, int second) {
        // 0인지 체크
        if (first == 0 || second <= 1) {
            return false;
        }

        if (first == second) {
            return false;
        }

        // 소수점 결과 제외
        if (first % second != 0) {
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
