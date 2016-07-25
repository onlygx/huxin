package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.util.Date;

public class Friend extends BaseModel {
    private Long id;

    private Long userId;

    private Long friendId;

    private Date setTime;

    /**
     * 2，正在申请
     * 1，申请成功
     */
    private Integer status;

    private String intro;

    private User user;

    public Friend(Long id, Long userId, Long friendId, Date setTime, Integer status, String intro) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.setTime = setTime;
        this.status = status;
        this.intro = intro;
    }

    public Friend() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}