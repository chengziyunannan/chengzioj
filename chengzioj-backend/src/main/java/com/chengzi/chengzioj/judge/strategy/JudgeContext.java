package com.chengzi.chengzioj.judge.strategy;

import com.chengzi.chengzioj.model.dto.question.JudgeCase;
import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;
import com.chengzi.chengzioj.model.entity.Question;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import lombok.Data;


import java.util.List;

/**
 * 上下文
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<JudgeCase> JudgeCaseList;

    private List<String> inputCaseList;

    private List<String> outputCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
