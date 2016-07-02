package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.util.Date;

public class User extends BaseModel {

    private Long id;

    /**
     *  登录名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型
     * 1，普通用户
     */
    private Integer type;

    /**
     * 状态
     * 1，正常
     */
    private Integer status;

    /**
     * 个性签名
     */
    private String intro;

    /**
     * 手机
     */
    private String phone;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String head;

    /**
     * 昵称
     */
    private String nick;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 微信号
     */
    private String wx;

    private Date setTime;

    public User(Long id, String userName, String password, Integer type, Integer status, String intro, String phone, String province, String city, String district, String address, String sex, String head, String nick, String qq, String wx, Date setTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.status = status;
        this.intro = intro;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.sex = sex;
        this.head = head;
        this.nick = nick;
        this.qq = qq;
        this.wx = wx;
        this.setTime = setTime;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }
}