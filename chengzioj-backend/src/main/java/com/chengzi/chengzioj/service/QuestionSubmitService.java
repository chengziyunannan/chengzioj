package com.chengzi.chengzioj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzi.chengzioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import com.chengzi.chengzioj.model.entity.User;


/**
* @author chengzi
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-01-05 15:10:23
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
