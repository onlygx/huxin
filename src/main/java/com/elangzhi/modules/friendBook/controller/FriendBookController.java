package com.elangzhi.modules.friendBook.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.FriendBook;
import com.elangzhi.modules.friendBook.services.FriendBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 用户通讯录 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/friendBook")
public class FriendBookController extends AdminBaseController<FriendBook> {







    //---------------------------- property -------------------------------

    @Resource
    private FriendBookService friendBookService;

}
