package com.elangzhi.modules.user.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.User;
import com.elangzhi.modules.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 用户 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/user")
public class UserController extends AdminBaseController<User> {







    //---------------------------- property -------------------------------

    @Resource
    private UserService userService;

}
