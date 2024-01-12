package com.chengzi.chengzioj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.chengzi.chengzioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.chengzi.chengzioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;

import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import com.chengzi.chengzioj.model.entity.User;
import com.chengzi.chengzioj.model.vo.QuestionSubmitVO;


/**
* @author chengzi
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-01-05 15:10:23
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);



    /**
     * 获取提交题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取提交题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);



}
