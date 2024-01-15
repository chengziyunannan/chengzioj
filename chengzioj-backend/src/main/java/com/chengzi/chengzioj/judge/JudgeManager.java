package com.chengzi.chengzioj.judge;

import com.chengzi.chengzioj.judge.strategy.DefaultJudgeStrategy;
import com.chengzi.chengzioj.judge.strategy.JavaJudgeStrategy;
import com.chengzi.chengzioj.judge.strategy.JudgeContext;
import com.chengzi.chengzioj.judge.strategy.JudgeStrategy;
import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 管理判题策略的调用
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String languages = questionSubmit.getLanguages();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (("java").equals(languages)){
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
