package com.chengzi.chengzioj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题信息
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class JudgeInfo implements Serializable {
    /**
     * 执行信息(ms)
     */
    private String message;

    /**
     * 时间(kb)
     */
    private long time;


    /**
     * 内存(kb)
     */

    private long memory;


    private static final long serialVersionUID = 1L;
}