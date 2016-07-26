package com.elangzhi.modules.friend.app;

import com.elangzhi.modules.friend.services.FriendService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Friend;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
* 用户好友 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/friend")
public class AppFriendController {

    @RequestMapping(value = "/friendApply", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "好友申请", notes = "根据手机号请求添加好友")
    public Tip<Friend> friendApply(
            @ApiParam(name = "phone", value = "目标用户手机号")
            @RequestParam String phone,
            @ApiIgnore HttpSession session
    ) {
        User user = (User) session.getAttribute(Const.USER);
        User friendUser = userService.selectByUserName(phone);
        if(friendUser == null){
            return new Tip<>(2);
        }

        //如果他的申请列表中已经存在我
        List<Friend> friends = friendService.listByApplyUserId(friendUser.getId());
        for(Friend f : friends){
            if(f.getUserId().equals(user.getId())){
                return new Tip<>(3);
            }
        }


        Friend friend = new Friend();
        friend.setId(UUIDFactory.getLongId());
        friend.setSetTime(new Date());
        friend.setStatus(2);
        friend.setUserId(user.getId());
        friend.setFriendId(friendUser.getId());
        friend.setIntro(user.getNick());
        try {
            friendService.insert(friend);
            return new Tip<>(friend);
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip<>(1);
        }
    }



    @RequestMapping(value = "/findByPhone", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "好友查询", notes = "根据手机号查询用户，如果是好友，user会携带friend字段")
    public Tip<User> findByPhone(
            @ApiParam(name = "phone", value = "目标用户手机号")
            @RequestParam String phone,
            @ApiIgnore HttpSession session
    ) {

        User friendUser = userService.selectByUserName(phone);
        if(friendUser == null){
            return new Tip<>(2);
        }

        User user = (User) session.getAttribute(Const.USER);
        List<Friend> friends = friendService.listByUserId(user.getId());

        try {
            for(Friend f : friends){
                if(f.getFriendId() .equals(friendUser.getId())){
                    friendUser.setFriend(f);
                    return new Tip<>(friendUser);
                }
            }
            return new Tip<>(friendUser);
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip<>(1);
        }
    }



    @RequestMapping(value = "/friendList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "好友列表", notes = "获取我的好友列表")
    public Tip<List<Friend>> friendList( @ApiIgnore HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        List<Friend> friends = friendService.listByUserId(user.getId());
        for(Friend f : friends){
            f.setUser(userService.selectById(f.getFriendId()));
        }
        return new Tip<>(friends);
    }

    @RequestMapping(value = "/dealFriend", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "处理申请", notes = "处理好友申请，传status")
    public Tip<Friend> dealFriend(
            @ApiParam(name = "id", value = "当前申请的id")
            @RequestParam Long id,
            @ApiParam(name = "status", value = "同意传1，不同意传0")
            @RequestParam Integer status
    ){
        Friend f = friendService.selectById(id);
        f.setStatus(status);
        try {
            if(status == 1){
                friendService.updateById(f);
                Friend friend1 = new Friend();
                friend1.setId(UUIDFactory.getLongId());
                friend1.setSetTime(new Date());
                friend1.setUserId(f.getFriendId());
                friend1.setFriendId(f.getUserId());
                User user = userService.selectById(f.getUserId());
                friend1.setIntro(user.getNick());
                friend1.setStatus(1);
                friendService.insert(friend1);
            }else if(status == 0){
                friendService.deleteById(id);
            }
            return new Tip<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }

    @RequestMapping(value = "/applyList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "好友申请列表", notes = "获取我的好友申请列表")
    public Tip<List<Friend>> applyList( @ApiIgnore HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        List<Friend> friends = friendService.listByApplyUserId(user.getId());
        for(Friend f : friends){
            f.setUser(userService.selectById(f.getUserId()));
        }
        return new Tip<>(friends);
    }


    //---------------------------- property -------------------------------

    @Resource
    private FriendService friendService;

    @Resource
    private UserService userService;
}
