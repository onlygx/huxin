package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.util.Date;

public class BankCard extends BaseModel{
    private Long id;

    /**
     * 银行名称
     */
    private String bank;

    /**
     * 银行卡号
     */
    private String number;

    /**
     * 开户姓名
     */
    private String name;

    /**
     * 开户行（支行）
     */
    private String home;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 添加时间
     */
    private Date setTime;

    public BankCard(Long id, String bank, String number, String name, String home, Long userId, Date setTime) {
        this.id = id;
        this.bank = bank;
        this.number = number;
        this.name = name;
        this.home = home;
        this.userId = userId;
        this.setTime = setTime;
    }

    public BankCard() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
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
}