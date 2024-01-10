package com.chengzi.chengzioj.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题用例
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class JudgeCase implements Serializable {
    /**
     * 输入用例
     */
    private String inputCase;

    /**
     * 输出用例
     */
    private String outputCase;


    private static final long serialVersionUID = 1L;
}