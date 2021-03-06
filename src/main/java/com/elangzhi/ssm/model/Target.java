package com.elangzhi.ssm.model;

import com.elangzhi.ssm.model.base.BaseModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 挑战
 */
public class Target extends BaseModel {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户
     */
    private Long userId;

    /**
     * 开始时间
     */
    private Date setTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 结束时间
     */
    private Date finishTime;

    /**
     * 裁判
     */
    private String refereeId;
    private String refereeName;

    /**
     * 保持天数（整数）
     */
    private Integer keep;

    /**
     * 押金
     */
    private Double price;

    /**
     * 状态
     * 1，挑战中（默认）
     * 2，已结束（等待裁判做出决定）
     * 3，已完成
     */
    private Integer status;

    /**
     * 类型
     * 1，挑战（默认）
     */
    private Integer type;

    /**
     * 裁判观点
     * 2，未选择
     * 1，成功
     * 0，失败
     */
    private Integer opinion;

    /**
     * 挑战详情
     */
    private String content;

    //当前坚持天数
    private Integer nowKeep;

    /**
     * 标签
     * 多个用英文逗号拼接
     */
    private String tag;


    /**
     * 用来给Objective-C语言做适配
     */
    private String stringId;

    public String getStringId() {
        if(id != null){
            return id.toString();
        }
        return "";
    }

    public void setStringId(String stringId) {
        this.id = Long.parseLong(stringId);
    }


    //监督员
    private List<TargetSupervise> superviseList;
    private User user;

    public Target(Long id, String title, Long userId, Date setTime, String refereeId, Integer keep, Double price, Integer status, Integer type, Integer opinion, String content) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.setTime = setTime;
        this.refereeId = refereeId;
        this.keep = keep;
        this.price = price;
        this.status = status;
        this.type = type;
        this.opinion = opinion;
        this.content = content;
    }

    public Target() {
        super();
    }


    public String getSetTime(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.setTime);
    }

    public String getEndTime(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.endTime);
    }

    public String getFinishTime(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.finishTime);
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getNowKeep() {
        return nowKeep;
    }

    public void setNowKeep(Integer nowKeep) {
        this.nowKeep = nowKeep;
    }

    public List<TargetSupervise> getSuperviseList() {
        return superviseList;
    }

    public void setSuperviseList(List<TargetSupervise> superviseList) {
        this.superviseList = superviseList;
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

    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public Integer getKeep() {
        return keep;
    }

    public void setKeep(Integer keep) {
        this.keep = keep;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOpinion() {
        return opinion;
    }

    public void setOpinion(Integer opinion) {
        this.opinion = opinion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}