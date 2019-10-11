package com.jumpingi.arithmetic.ui.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class QuestionData {
    @PrimaryKey
    private int id;
    private List<String> operandFirst;     // 첫번째 피 연산자
    private List<String> operandSecond;    // 두번째 피 연산자
    private int operator;                  // 연산자 string 리소스 id
    private int unit;                      // 피 연산자 단위
    private List<String> operatorResult;   // 연산 결과
    private List<Boolean> right;           // 채점 결과
    private int score;                     // 점수
    private String date;                   // 날짜 (YYYY-MM-dd)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getOperandFirst() {
        return operandFirst;
    }

    public void setOperandFirst(List<String> operandFirst) {
        this.operandFirst = operandFirst;
    }

    public List<String> getOperandSecond() {
        return operandSecond;
    }

    public void setOperandSecond(List<String> operandSecond) {
        this.operandSecond = operandSecond;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public List<String> getOperatorResult() {
        return operatorResult;
    }

    public void setOperatorResult(List<String> operatorResult) {
        this.operatorResult = operatorResult;
    }

    public List<Boolean> getRight() {
        return right;
    }

    public void setRight(List<Boolean> right) {
        this.right = right;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
