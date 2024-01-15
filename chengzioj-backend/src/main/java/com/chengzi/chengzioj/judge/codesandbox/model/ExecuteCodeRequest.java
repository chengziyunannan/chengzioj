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
public class ExecuteCodeRequest {
    /**
     * 输入用例
     */
    private List<String> inputCaseList;

    /**
     * 代码语言
     */
    private String languages;

    /**
     * 用户代码
     */
    private String userCode;
}
