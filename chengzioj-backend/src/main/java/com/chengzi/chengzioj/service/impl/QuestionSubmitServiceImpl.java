package com.chengzi.chengzioj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzi.chengzioj.common.ErrorCode;
import com.chengzi.chengzioj.exception.BusinessException;
import com.chengzi.chengzioj.mapper.QuestionSubmitMapper;
import com.chengzi.chengzioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.chengzi.chengzioj.model.entity.Question;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import com.chengzi.chengzioj.model.entity.User;
import com.chengzi.chengzioj.service.QuestionService;
import com.chengzi.chengzioj.service.QuestionSubmitService;



import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
* @author chengzi
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-01-05 15:10:23
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService {


    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // todo 校验用户选择的编程语言
        // 是否已提交
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserCode(questionSubmitAddRequest.getUserCode());
        questionSubmit.setLanguages(questionSubmitAddRequest.getLanguages());
        // todo 设置提交状态
//        questionSubmit.setQuestionStatus();
        questionSubmit.setJudgeInfo("{}");

        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据库插入异常");
        }
        return questionSubmit.getId();
    }



}




