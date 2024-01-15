package com.chengzi.chengzioj.judge.strategy;

import com.chengzi.chengzioj.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
