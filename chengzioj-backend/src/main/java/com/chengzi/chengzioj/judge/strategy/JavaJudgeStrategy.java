package com.chengzi.chengzioj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.chengzi.chengzioj.model.dto.question.JudgeCase;
import com.chengzi.chengzioj.model.dto.question.JudgeConfig;
import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;
import com.chengzi.chengzioj.model.entity.Question;
import com.chengzi.chengzioj.model.enums.QuestionJudgeInfoEnum;

import java.util.List;

/**
 * 默认的判题策略
 */
public class JavaJudgeStrategy implements JudgeStrategy {

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputCaseList = judgeContext.getInputCaseList();
        List<String> outputCaseList = judgeContext.getOutputCaseList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        QuestionJudgeInfoEnum questionJudgeInfoEnum = QuestionJudgeInfoEnum.ACCEPTED;
        long time = judgeInfo.getTime();
        long memory = judgeInfo.getMemory();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        //判断输出用例的个数
        if (outputCaseList.size() != inputCaseList.size()){
            questionJudgeInfoEnum = QuestionJudgeInfoEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(questionJudgeInfoEnum.getValue());
            return judgeInfoResponse;
        }
        //判断每一个测试用例是否正确
        for (int i = 0; i < inputCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (judgeCase.getOutputCase() != judgeCase.getInputCase() ){
                questionJudgeInfoEnum = QuestionJudgeInfoEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(questionJudgeInfoEnum.getValue());
                return judgeInfoResponse;
            }
        }
        //判断题目限制(时间限制、内存限制)
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        long timeLimit = judgeConfig.getTimeLimit();
        long memoryLimit = judgeConfig.getMemoryLimit();
        if (time > timeLimit){
            questionJudgeInfoEnum = QuestionJudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(questionJudgeInfoEnum.getValue());
            return judgeInfoResponse;
        }
        if (memory > memoryLimit){
            questionJudgeInfoEnum = QuestionJudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(questionJudgeInfoEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(questionJudgeInfoEnum.getValue());
        return judgeInfoResponse;
    }
}
