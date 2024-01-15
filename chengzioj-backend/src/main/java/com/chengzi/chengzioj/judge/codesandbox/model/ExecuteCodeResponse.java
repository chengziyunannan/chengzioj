package com.chengzi.chengzioj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {
    /**
     * 输出用例
     */
    private List<String> outputCase;

    /**
     * 其他信息
     */
    private String message;

    /**
     * 执行状态
     */
    private Integer questionStatus;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
