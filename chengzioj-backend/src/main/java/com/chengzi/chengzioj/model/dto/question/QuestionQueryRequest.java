package com.chengzi.chengzioj.model.dto.question;

import com.chengzi.chengzioj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 判题用例（json数组，如:输入用例、输出用例）
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json数组，如:内存限制、运行时间限制等）
     */
    private JudgeConfig judgeConfig;


    /**
     * 创建用户 id
     */
    private Long userId;




    private static final long serialVersionUID = 1L;
}