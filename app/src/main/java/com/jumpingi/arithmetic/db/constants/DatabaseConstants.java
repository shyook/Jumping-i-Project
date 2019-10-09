package com.jumpingi.arithmetic.db.constants;

public class DatabaseConstants {
    public static final String ARITHMETIC_HISTORY_DATABASE_NAME = "history_info.db";
    public static final int ARITHMETIC_HISTORY_DATABASE_VERSION = 1;

    // 데이터베이스 컬럼 명
    public static final String HISTORY_IDX_FIELD = "idx";                               // 인덱스
    public static final String HISTORY_DATE_FIELD = "date";                             // 문제를 푼 날짜
    public static final String HISTORY_ELAPSED_TIME_FIELD = "elapsed_time";             // 문제를 푸는데 걸린 시간
    public static final String HISTORY_TOTAL_SCORE_FIELD = "total_score";               // 점수
    public static final String HISTORY_OPERATION_TYPE_FIELD = "operation_type";         // 문제 유형 (덧셈, 뺄셈, 곱셈, 나눗셈...)
    public static final String HISTORY_OPERATION_RESULT_FIELD = "operation_result";     // 문제와 적어서 제출한 답 그리고 정답 오답 (10개의 항목)
}
