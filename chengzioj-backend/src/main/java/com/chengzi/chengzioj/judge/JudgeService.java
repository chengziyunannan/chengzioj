package com.chengzi.chengzioj.judge;

import com.chengzi.chengzioj.model.entity.QuestionSubmit;

/**
 * 判题服务
 */

public interface JudgeService {

    /**
     * 判题
     * @param questionSubmieId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmieId);
}
