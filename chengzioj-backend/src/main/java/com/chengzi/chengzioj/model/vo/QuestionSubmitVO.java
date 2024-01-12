package com.chengzi.chengzioj.model.vo;

import cn.hutool.json.JSONUtil;

import com.chengzi.chengzioj.model.dto.questionsubmit.JudgeInfo;
import com.chengzi.chengzioj.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目视图(脱敏)
 * @TableName question
 */
@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String languages;

    /**
     * 用户代码
     */
    private String userCode;

    /**
     * 判题信息（json数组,程序执行时间,消耗内存等）
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0-待判题,1-判题中,2-完成,3-失败）
     */
    private Integer questionStatus;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交者的信息
     */
    private UserVO userVO;

    /**
     * 提交的题目信息
     */
    private QuestionVO questionVO;



    /**
     * 包装类转对象
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo judgeInfoObj = questionSubmitVO.getJudgeInfo();
        if (judgeInfoObj != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoObj));
        }
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        String judgeInfoStr = questionSubmit.getJudgeInfo();
            questionSubmitVO.setJudgeInfo(JSONUtil.toBean(judgeInfoStr, JudgeInfo.class));
        return questionSubmitVO;
    }
    private static final long serialVersionUID = 1L;
}