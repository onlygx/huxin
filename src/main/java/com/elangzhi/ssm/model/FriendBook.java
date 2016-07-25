package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

public class FriendBook  extends BaseModel {
    private Long id;

    private String name;

    private String phone;

    private Long userId;

    private Long bookUserId; //已注册的显示对方id
    private String bookUserName; // 已注册用户的name
    private Integer friendStatus; // 如果是好友，为1，如果为2，代表在申请

    public FriendBook(Long id, String name, String phone, Long userId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userId = userId;
    }

    public FriendBook() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}