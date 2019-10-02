package com.jumpingi.arithmetic.ui.data;

import java.util.List;

public class QuestionData {
    private List<String> mOperandFirst;     // 첫번째 피 연산자
    private List<String> mOperandSecond;    // 두번째 피 연산자
    private int mOperator;                  // 연산자 string 리소스 id
    private int mUnit;                      // 피 연산자 단위
    private List<String> mOperatorResult;   // 연산 결과
    private List<Boolean> mRight;           // 채점 결과
    private int mScore;                     // 점수

    public List<String> getOperandFirst() {
        return mOperandFirst;
    }

    public void setOperandFirst(List<String> operandFirst) {
        this.mOperandFirst = operandFirst;
    }

    public List<String> getOperandSecond() {
        return mOperandSecond;
    }

    public void setOperandSecond(List<String> operandSecond) {
        this.mOperandSecond = operandSecond;
    }

    public int getOperator() {
        return mOperator;
    }

    public void setOperator(int operator) {
        this.mOperator = operator;
    }

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        this.mUnit = unit;
    }

    public List<String> getOperatorResult() {
        return mOperatorResult;
    }

    public void setOperatorResult(List<String> operatorResult) {
        this.mOperatorResult = operatorResult;
    }

    public List<Boolean> getRight() {
        return mRight;
    }

    public void setRight(List<Boolean> right) {
        this.mRight = right;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = mScore;
    }
}
