package com.chengzi.chengzioj.judge.codesandbox.iml;

import com.chengzi.chengzioj.judge.codesandbox.CodeSandbox;
import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;
import com.chengzi.chengzioj.model.enums.QuestionJudgeInfoEnum;
import com.chengzi.chengzioj.model.enums.QuestionJudgeStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> inputCaseList = executeCodeRequest.getInputCaseList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputCase(inputCaseList);
        executeCodeResponse.setMessage("测试成功");
        executeCodeResponse.setQuestionStatus(QuestionJudgeStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(QuestionJudgeInfoEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
