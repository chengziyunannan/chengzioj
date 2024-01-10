package com.chengzi.chengzioj.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题配置
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class JudgeConfig implements Serializable {
    /**
     * 时间限制(ms)
     */
    private long timeLimit;

    /**
     * 内存限制(kb)
     */
    private long memoryLimit;


    /**
     * 堆栈限制(kb)
     */

    private long stackLimit;


    private static final long serialVersionUID = 1L;
}