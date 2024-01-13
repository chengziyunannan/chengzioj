package com.chengzi.chengzioj.judge.codesandbox;

import com.chengzi.chengzioj.judge.codesandbox.iml.ExampleCodeSandbox;
import com.chengzi.chengzioj.judge.codesandbox.iml.RemoteCodeSandbox;
import com.chengzi.chengzioj.judge.codesandbox.iml.ThirdPartyCodeSandbox;


/**
 * 代码沙箱工厂(根据传入的字符串创建对应的代码沙箱实例)
 */
public class CodeSandboxFactory {
    /**
     * 创建代码沙箱实例
     * @param type 传入的沙箱类型
     * @return 对应的代码沙箱实例
     */
    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
