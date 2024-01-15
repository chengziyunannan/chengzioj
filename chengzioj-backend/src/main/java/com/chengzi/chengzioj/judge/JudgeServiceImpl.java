package com.chengzi.chengzioj.judge;

import cn.hutool.json.JSONUtil;
import com.chengzi.chengzioj.common.ErrorCode;
import com.chengzi.chengzioj.exception.BusinessException;
import com.chengzi.chengzioj.judge.codesandbox.CodeSandbox;
import com.chengzi.chengzioj.judge.codesandbox.CodeSandboxFactory;
import com.chengzi.chengzioj.judge.codesandbox.CodeSandboxProxy;
import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.chengzi.chengzioj.judge.strategy.JudgeContext;
import com.chengzi.chengzioj.model.dto.question.JudgeCase;
import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;
import com.chengzi.chengzioj.model.entity.Question;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import com.chengzi.chengzioj.model.enums.QuestionJudgeStatusEnum;
import com.chengzi.chengzioj.service.QuestionService;
import com.chengzi.chengzioj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题服务实现类
 */

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type :example}")
    private String type;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //根据传入的题目id,获取对应的题目、提交信息(代码、编程语言等)
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        //先判断题目的判题状态,如果不是未判题,则不进行判题操作
        if (questionSubmit.getQuestionStatus() != QuestionJudgeStatusEnum.WAITING.getValue()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目判题中");
        }
        //更新数据库中的判题状态,将题目的判题状态改为判题中
        questionSubmit.setQuestionStatus(QuestionJudgeStatusEnum.RUNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题状态更新失败");
        }
        //调用代码沙箱,获取执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String userCode = questionSubmit.getUserCode();
        String languages = questionSubmit.getLanguages();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputCaseList = judgeCaseList.stream().map(JudgeCase::getInputCase).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .userCode(userCode)
                .languages(languages)
                .inputCaseList(inputCaseList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        //根据代码沙箱的执行结果,设置题目的判题信息和状态
        List<String> outputCaseList = executeCodeResponse.getOutputCase();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(judgeInfo);
        judgeContext.setInputCaseList(inputCaseList);
        judgeContext.setOutputCaseList(outputCaseList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfoResponse = judgeManager.doJudge(judgeContext);
        //更新数据库中的判题状态,将题目的判题状态改为已完成
        questionSubmit.setQuestionStatus(QuestionJudgeStatusEnum.SUCCEED.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResponse));
        update = questionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题状态更新失败");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
