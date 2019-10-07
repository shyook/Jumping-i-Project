package com.jumpingi.arithmetic.constants;

import com.jumpingi.arithmetic.R;

public interface Constant {
    int[] BACKGROUND_GRADATION = {
            R.drawable.background_gradient_1
            , R.drawable.background_gradient_2
            , R.drawable.background_gradient_3
            , R.drawable.background_gradient_4
            , R.drawable.background_gradient_5
    };

    int TOTAL_BACKGROUND_GRADATION = 5;

    int[] GENDER = {
            R.string.intro_male
            , R.string.intro_female };

    interface IntentParam {
        String INTENT_KEY_MENU_TYPE = "TYPE";
    }

    int TOTAL_QUESTION_GENERATE = 10;

    interface UNIT_TYPE {
        int UNIT_TYPE_1 = 10;
        int UNIT_TYPE_10 = 100;
        int UNIT_TYPE_100 = 1000;
        int UNIT_TYPE_2 = 20;
    }

    enum QUESTION_MODE {
        QUESTION_MODE_IN_PROGRESS
        , QUESTION_MODE_CONFIRM_ANSWER
        , QUESTION_MODE_RETRY
    }

}
