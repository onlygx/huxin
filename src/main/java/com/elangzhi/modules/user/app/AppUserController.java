package com.elangzhi.modules.user.app;

import com.elangzhi.modules.user.services.OpenIMService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.taobao.api.domain.Userinfos;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

/**
* 用户 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/user")
@Api(value = "用户", description = "登录注册、资料修改、头像修改、密码修改", position = 1)
public class AppUserController {


    /**
     * 更改用户信息
     * @param user 用户信息
     *             nick,province,city,district,address,intro
     * @param session session
     * @return 成功状态
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改资料",  notes = "更改当前登陆用户资料", position = 7)
    public Tip<User> updateUser(
            @ApiParam(name = "user",value = "可更改的内容有：nick/province/city/district/address/intro",required = true)
            @RequestBody User user,
            @ApiIgnore HttpSession session){

        try {
            User userData = (User) session.getAttribute(Const.USER);
            userData.setNick(user.getNick());
            userData.setProvince(user.getProvince());
            userData.setCity(user.getCity());
            userData.setDistrict(user.getDistrict());
            userData.setAddress(user.getAddress());
            userData.setIntro(user.getIntro());

            userService.updateById(userData);
            session.setAttribute(Const.USER,userData);
            openIMService.changeUser(createUserInfo(userData));
            return new Tip(userData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 更改阿里百川头像
     * @param request 请求
     * @param file 文件
     * @param session session
     * @return 成功状态
     */
    @RequestMapping(value = "/changeHead", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改头像",  notes = "更改当前登陆用户头像", position = 6)
    public Tip<String> changeHead(
                            @ApiIgnore
                                    HttpServletRequest request,
                            @RequestParam(value = "file")
                                    MultipartFile file,
                            @ApiIgnore
                                    HttpSession session
    ){
        if (file != null) {
            try {
                String fileName = file.getOriginalFilename();
                String saveName = UUIDFactory.getLongId() + fileName.substring(fileName.lastIndexOf("."));
                //保存地址
                String saveUrl = "/images/headImage/" + "user_" + saveName;
                String filePath =  request.getServletContext().getRealPath(saveUrl);
                file.transferTo(new File(filePath));

                User user = (User) session.getAttribute(Const.USER);
                user.setHead(saveUrl);
                //更改数据库头像
                userService.updateById(user);
                //更改session头像
                session.setAttribute(Const.USER,user);
                //更改阿里百川头像
                openIMService.changeImg(user.getUserName(),saveUrl);

                return new Tip(saveUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return new Tip(2);
            }
        } else {
            return new Tip(1);
        }
    }

    /**
     * 更改密码
     * @param userName 用户
     * @param code 验证码
     * @param session session
     * @return 成功状态
     */
    @RequestMapping(value = "/login/changePwd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改密码",  notes = "更改某用户的密码", position = 5)
    public Tip changePwd(
                            @RequestParam String userName,
                            @RequestParam String password,
                            @RequestParam String code,
                            @ApiIgnore HttpSession session){
        User userData = userService.selectByUserName(userName);
        //验证码验证
        if(!validationCode(userName,code,session)){
            return new Tip(2);
        }
        userData.setPassword(password);
        try {
            userService.updateById(userData);
            return new Tip();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(1);
        }
    }



    /**
     * 发送忘记密码验证码
     * @param session session
     * @param userName 用户名
     * @return 结果
     * 1 用户不存在
     */
    @RequestMapping(value = "/login/sendPwdCode", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发送验证码",  notes = "更改密码用", position = 4)
    public Tip<String> sendPwdCode(@ApiIgnore HttpSession session,@RequestParam String userName){
        User user = userService.selectByUserName(userName);
        if(user == null){
            return new Tip(1);
        }
        //TODO 发送短信验证码
        String code = UUIDFactory.getCode6();
        session.setAttribute(Const.SECURITY_CODE,code);
        session.setAttribute(Const.SECURITY_PHONE,userName);
        return new Tip(code);
    }

    /**
     * 登录
     * @param userName 用户信息
     * @param session session
     * @return 成功状态
     * 1，用户名不存在
     * 2，密码验证失败
     */
    @RequestMapping(value = "/login/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登陆",  notes = "登陆信息", position = 3)
    public Tip<User> login(
                    @RequestParam
                    @ApiParam(name = "userName",value = "用户名",required = true)
                    String userName,
                    @RequestParam
                    @ApiParam(name = "password",value = "密码",required = true)
                    String password,
                    @ApiIgnore HttpSession session){
        User userData = userService.selectByUserName(userName);
        if(userData == null){
            return new Tip(1);
        }
        if(password.equals(userData.getPassword())){
            session.setAttribute(Const.USER,userData);
            return new Tip(userData);
        }
        return new Tip(2);
    }

    /**
     * 注册
     * @param userName user信息
     * @param code 验证码
     * @param session sesson
     * @return 成功状态
     * 1,注册失败
     * 2,验证码验证失败
     */
    @RequestMapping(value = "/login/register", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "注册",  notes = "注册用户", position = 2)
    public Tip<User> register(
            @RequestParam
            @ApiParam(name = "userName",value = "用户名",required = true)
            String userName,
            @RequestParam
            @ApiParam(name = "password",value = "密码",required = true)
            String password,
            @RequestParam
            @ApiParam(name = "code",value = "发送的验证码",required = true)
            String code,
            @ApiIgnore HttpSession session){

        //验证码验证
        if(!validationCode(userName,code,session)){
            return new Tip(2);
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setId(UUIDFactory.getLongId());
        user.setPhone(user.getUserName());
        user.setHead("/images/account/default_head.jpg");
        user.setNick(user.getUserName());
        user.setSetTime(new Date());
        user.setType(1);
        user.setStatus(1);
        try {
            userService.insert(user);
            openIMService.addUser(createUserInfo(user));
            return new Tip(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 创建阿里百川用户信息
     * @param user 本地用户信息
     * @return 阿里百川用户信息
     */
    private Userinfos createUserInfo(User user) {
        Userinfos userinfos = new Userinfos();
        userinfos.setUserid(user.getUserName());
        userinfos.setPassword("123456");
        userinfos.setIconUrl(user.getHead());
        userinfos.setMobile(user.getPhone());
        userinfos.setName(user.getNick());
        userinfos.setNick(user.getNick());
        userinfos.setRemark(user.getId().toString());
        userinfos.setExtra(user.getId().toString());
        return userinfos;
    }

    /**
     * 验证验证码
     * @param userName 用户
     * @param code 验证码
     * @param session session
     * @return 是否通过
     * true 验证通过
     */
    private boolean validationCode(String userName,String code,HttpSession session){
        try {
            String securityCode = session.getAttribute(Const.SECURITY_CODE).toString();
            String securityPhone = session.getAttribute(Const.SECURITY_PHONE).toString();
            if(securityPhone.equals(userName) && securityCode.equals(code)){
                session.removeAttribute(Const.SECURITY_CODE);
                session.removeAttribute(Const.SECURITY_PHONE);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 发送注册验证码
     * @param session session
     * @param phone 手机
     * @return 结果
     * 1 用户已存在
     */
    @RequestMapping(value = "/login/sendCode", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发送验证码",  notes = "注册用。", position = 1)
    public Tip<String> sendCode(
            @ApiIgnore HttpSession session,
            @RequestParam
            @ApiParam(name = "phone",value = "手机号",required = true)
            String phone
    ){
        System.out.println("准备发送验证码："+ phone);
        User user = userService.selectByPhone(phone);
        if(user != null){
            return new Tip(1);
        }
        //TODO 发送短信验证码
        String code = UUIDFactory.getCode6();
        session.setAttribute(Const.SECURITY_CODE,code);
        session.setAttribute(Const.SECURITY_PHONE,phone);
        System.out.println("发送验证码："+ code);
        return new Tip(code);
    }

    //---------------------------- property -------------------------------

    @Resource
    private UserService userService;

    @Resource
    private OpenIMService openIMService;


}
