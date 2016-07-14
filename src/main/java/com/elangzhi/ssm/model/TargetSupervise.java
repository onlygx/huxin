package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

/**
 * 监督员
 */
public class TargetSupervise extends BaseModel {
    private Long id;

    /**
     * 监督员id
     */
    private Long userId;

    /**
     * 观点
     * 1，通过
     * 0，不通过
     * 2，未选择
     */
    private Integer opinion;

    /**
     * 备注
     */
    private String intro;

    /**
     * 挑战id
     */
    private Long targetId;

    private User user;



    public TargetSupervise(Long id, Long userId, Integer opinion, String intro, Long targetId) {
        this.id = id;
        this.userId = userId;
        this.opinion = opinion;
        this.intro = intro;
        this.targetId = targetId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TargetSupervise() {
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

    public Integer getOpinion() {
        return opinion;
    }

    public void setOpinion(Integer opinion) {
        this.opinion = opinion;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}