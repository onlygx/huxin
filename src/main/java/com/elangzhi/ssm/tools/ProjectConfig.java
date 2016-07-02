package com.elangzhi.ssm.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 * Created by GaoXiang on 2016/1/14 0014.
 */
@Component
public class ProjectConfig {

    @Value("#{projectConfigurer['page.size']}")
    private Integer pageSize;

    @Value("#{projectConfigurer['file.tempImagePath']}")
    private String tempImagePath;

    @Value("#{projectConfigurer['server.host']}")
    private String serverHost;

    @Value("#{projectConfigurer['openIm.url']}")
    private String openImUrl;

    @Value("#{projectConfigurer['openIm.appkey']}")
    private String openImAppKey;

    @Value("#{projectConfigurer['openIm.secret']}")
    private String openImSecret;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTempImagePath() {
        return tempImagePath;
    }

    public void setTempImagePath(String tempImagePath) {
        this.tempImagePath = tempImagePath;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getOpenImUrl() {
        return openImUrl;
    }

    public void setOpenImUrl(String openImUrl) {
        this.openImUrl = openImUrl;
    }

    public String getOpenImAppKey() {
        return openImAppKey;
    }

    public void setOpenImAppKey(String openImAppKey) {
        this.openImAppKey = openImAppKey;
    }

    public String getOpenImSecret() {
        return openImSecret;
    }

    public void setOpenImSecret(String openImSecret) {
        this.openImSecret = openImSecret;
    }
}
