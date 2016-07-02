package com.elangzhi.modules.user.services;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersGetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * openIm 服务
 * @author GaoXiang
 * @version 20160613
 */
@Service
public class OpenIMService {

    //服务器地址  用于图片前缀
    @Value("#{projectConfigurer['server.host']}")
    private String serverHost;

    //阿里百川正式服务器地址
    @Value("#{projectConfigurer['openIm.url']}")
    private String openImUrl;

    //阿里百川项目 appKey
    @Value("#{projectConfigurer['openIm.appkey']}")
    private String openImAppKey;

    //阿里百川项目 AppSecret
    @Value("#{projectConfigurer['openIm.secret']}")
    private String openImSecret;


    /**
     * 更改头像地址
     * @param userName 用户名
     * @param iconUrl 头像地址
     * @throws ApiException 更改头像异常
     */
    public void changeImg(String userName,String iconUrl) throws ApiException {
        Userinfos userinfos = new Userinfos();
        userinfos.setUserid(userName);
        userinfos.setIconUrl(serverHost + iconUrl);
        List<Userinfos> infoList = new ArrayList<>();
        infoList.add(userinfos);
        TaobaoClient client = new DefaultTaobaoClient(openImUrl, openImAppKey, openImSecret);
        OpenimUsersUpdateRequest request = new OpenimUsersUpdateRequest();
        request.setUserinfos(infoList);
        client.execute(request);
    }

    /**
     * 添加用户
     * @param userinfos 用户信息
     * @throws ApiException 添加异常
     */
    public void addUser(Userinfos userinfos) throws ApiException {
        List<Userinfos> infoList = new ArrayList<>();
        userinfos.setIconUrl(serverHost + userinfos.getIconUrl());
        infoList.add(userinfos);
        TaobaoClient client = new DefaultTaobaoClient(openImUrl, openImAppKey, openImSecret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        req.setUserinfos(infoList);
        client.execute(req);
    }

    /**
     * 获取用户信息
     * @param userName 用户名
     * @throws ApiException 获取失败
     */
    public void getUser(String userName) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(openImUrl, openImAppKey, openImSecret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids(userName);
        OpenimUsersGetResponse response = client.execute(req);
        System.out.println(response.getBody());
    }


    /**
     * 更改信息
     * @param userinfos 用户信息
     * @throws ApiException 更改失败
     */
    public void changeUser(Userinfos userinfos) throws ApiException {
        List<Userinfos> infoList = new ArrayList<>();
        infoList.add(userinfos);
        TaobaoClient client = new DefaultTaobaoClient(openImUrl, openImAppKey, openImSecret);
        OpenimUsersUpdateRequest request = new OpenimUsersUpdateRequest();
        request.setUserinfos(infoList);
        client.execute(request);
    }
}
