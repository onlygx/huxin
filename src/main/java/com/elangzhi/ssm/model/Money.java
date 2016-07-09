package com.elangzhi.ssm.model;

import java.util.Date;

public class Money {
    private Long id;

    private Long money;

    private String intro;

    private Long userId;

    /**
     * 1，充值
     * 2，提现申请
     */
    private Integer type;

    private Long infoId;

    private Date setTime;

    public Money(Long id, Long money, String intro, Long userId, Integer type, Long infoId, Date setTime) {
        this.id = id;
        this.money = money;
        this.intro = intro;
        this.userId = userId;
        this.type = type;
        this.infoId = infoId;
        this.setTime = setTime;
    }

    public Money() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }
}