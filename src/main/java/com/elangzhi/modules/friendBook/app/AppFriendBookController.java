package com.elangzhi.modules.friendBook.app;

import com.elangzhi.modules.friendBook.services.FriendBookService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Friend;
import com.elangzhi.ssm.model.FriendBook;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* 用户通讯录 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/friendBook")
public class AppFriendBookController {

    @RequestMapping(value = "/uploadBook", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传通讯录", notes = "上传通讯录，格式为：name:phone;name:phone;....")
    public Tip<String> uploadBook(
            @ApiParam(name = "books", value = "通讯录字符串")
            @RequestParam String books,
            @ApiIgnore HttpSession session
    ){
        User user = (User) session.getAttribute(Const.USER);
        if(!books.isEmpty()){
            String[] bookArray = books.split(";");
            for(String obj : bookArray){
                String[] objArray = obj.split(":");
                String name = objArray[0];
                String phone = objArray[1];

                FriendBook friendBook = new FriendBook();
                friendBook.setId(UUIDFactory.getLongId());
                friendBook.setUserId(user.getId());
                friendBook.setName(name);
                friendBook.setPhone(phone);
                try {
                    friendBookService.insert(friendBook);
                    return new Tip<>("成功上传（条）：" + bookArray.length);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Tip<>(2);
                }
            }
        }
        return new Tip<>(1);
    }

    @RequestMapping(value = "/phoneList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取通讯录", notes = "获取我的通讯录")
    public Tip<List<FriendBook>> phoneList(@ApiIgnore HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        List<FriendBook> friendBooks = friendBookService.listByUserId(user.getId());
        return new Tip<>(friendBooks);
    }

    //---------------------------- property -------------------------------

    @Resource
    private FriendBookService friendBookService;

}
