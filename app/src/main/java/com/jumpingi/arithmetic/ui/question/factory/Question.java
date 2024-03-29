package com.jumpingi.arithmetic.ui.question.factory;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.ui.data.QuestionData;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {
    protected List<Integer> arrFirst = new ArrayList<>();
    protected List<Integer> arrSecond = new ArrayList<>();
    protected List<Integer> arrResult = new ArrayList<>();
    protected QuestionData result = new QuestionData();

    public enum QUESTION_TYPE {
        QUESTION_TYPE_ADD_UNIT_1(R.string.menu_addition, R.string.sub_menu_unit_1)
        , QUESTION_TYPE_ADD_UNIT_10(R.string.menu_addition, R.string.sub_menu_unit_10)
        , QUESTION_TYPE_ADD_UNIT_100(R.string.menu_addition, R.string.sub_menu_unit_100)
        , QUESTION_TYPE_SUB_UNIT_1(R.string.menu_subtraction, R.string.sub_menu_unit_1)
        , QUESTION_TYPE_SUB_UNIT_10(R.string.menu_subtraction, R.string.sub_menu_unit_10)
        , QUESTION_TYPE_SUB_UNIT_100(R.string.menu_subtraction, R.string.sub_menu_unit_100)
        , QUESTION_TYPE_MULTI_UNIT_1(R.string.menu_multiply, R.string.sub_menu_unit_1)
        , QUESTION_TYPE_MULTI_UNIT_10(R.string.menu_multiply, R.string.sub_menu_unit_10)
        , QUESTION_TYPE_MULTI_UNIT_100(R.string.menu_multiply, R.string.sub_menu_unit_100)
        , QUESTION_TYPE_DIVIDE_UNIT_1(R.string.menu_divide, R.string.sub_menu_unit_1)
        , QUESTION_TYPE_DIVIDE_UNIT_10(R.string.menu_divide, R.string.sub_menu_unit_10)
        , QUESTION_TYPE_DIVIDE_UNIT_100(R.string.menu_divide, R.string.sub_menu_unit_100)
        , QUESTION_TYPE_FRACTION_ADD(R.string.menu_fraction, R.string.menu_addition)
        , QUESTION_TYPE_FRACTION_SUB(R.string.menu_fraction, R.string.menu_subtraction)
        , QUESTION_TYPE_QUIZ(R.string.menu_quiz, R.string.sub_menu_start);

        QUESTION_TYPE(int mainMenu, int subMenu) {
            this.mainMenu = mainMenu;
            this.subMenu = subMenu;
        }

        int mainMenu;
        int subMenu;

        public int getMainMenu() {
            return mainMenu;
        }

        public int getSubMenu() {
            return subMenu;
        }

    }

    abstract QuestionData doGenerationOperand(int num);
    abstract boolean checkOperand(int first, int second);

    protected List<String> convertIntegerToString(List<Integer> convertList) {
        List<String> result = new ArrayList<>();
        for (int data : convertList) {
            result.add(String.valueOf(data));
        }

        return result;
    }

}