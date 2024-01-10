package com.chengzi.chengzioj.controller;

import com.chengzi.chengzioj.common.BaseResponse;
import com.chengzi.chengzioj.common.ErrorCode;
import com.chengzi.chengzioj.common.ResultUtils;
import com.chengzi.chengzioj.exception.BusinessException;
import com.chengzi.chengzioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.chengzi.chengzioj.model.entity.User;
import com.chengzi.chengzioj.service.QuestionSubmitService;
import com.chengzi.chengzioj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_thumb")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 题目提交记录
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return questionSubmitId 用户提交id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                         HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

}
