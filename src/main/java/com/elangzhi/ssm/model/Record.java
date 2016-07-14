package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.util.Date;

public class Record extends BaseModel {
    private Long id;

    private String title;

    private Long userId;

    private Date setTime;

    private Long targetId;

    private String content;

    public Record(Long id, String title, Long userId, Date setTime, Long targetId, String content) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.setTime = setTime;
        this.targetId = targetId;
        this.content = content;
    }

    public Record() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}