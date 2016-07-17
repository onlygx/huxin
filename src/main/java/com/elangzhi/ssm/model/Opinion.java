package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.util.Date;

/**
 * 产品意见
 */
public class Opinion extends BaseModel {
    private Long id;

    private Long userId;

    /**
     * 内容
     */
    private String content;

    private Date setTime;

    public Opinion(Long id, Long userId, String content, Date setTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.setTime = setTime;
    }

    public Opinion() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }
}