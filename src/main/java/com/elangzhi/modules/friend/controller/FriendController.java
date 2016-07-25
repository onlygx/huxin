package com.elangzhi.modules.friend.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Friend;
import com.elangzhi.modules.friend.services.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 用户好友 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/friend")
public class FriendController extends AdminBaseController<Friend> {







    //---------------------------- property -------------------------------

    @Resource
    private FriendService friendService;

}
