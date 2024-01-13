package com.chengzi.chengzioj.judge.codesandbox;

import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.chengzi.chengzioj.judge.codesandbox.model.ExecuteCodeResponse;


/**
 * 代码沙箱接口
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest 请求信息
     * @return executeCodeResponse 响应信息
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
