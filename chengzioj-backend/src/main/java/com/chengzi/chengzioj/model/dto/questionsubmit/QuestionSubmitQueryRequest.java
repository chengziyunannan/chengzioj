package com.chengzi.chengzioj.model.dto.questionsubmit;

import com.chengzi.chengzioj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题目提交查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 编程语言
     */
    private String languages;


    /**
     * 判题状态（0-待判题,1-判题中,2-完成,3-失败）
     */
    private Integer questionStatus;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 用户 id
     */
    private Long userId;


    private static final long serialVersionUID = 1L;
}