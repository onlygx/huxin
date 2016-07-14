package com.elangzhi.modules.money.app;

import com.elangzhi.modules.money.services.MoneyService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Money;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.github.pagehelper.PageInfo;
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
import java.util.HashMap;
import java.util.Map;

/**
* 钱包 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/money")
public class AppMoneyController {

    @RequestMapping(value = "/addByZFB", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "充值",  notes = "充值到钱包")
    public Tip<Money> addByZFB(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "money",value = "金额")
            @RequestParam Long money){
        User user = (User) session.getAttribute(Const.USER);
        long value = Math.abs(money);
        try {
            moneyService.insertByType(user.getId(),1,value,user.getId());
            return new Tip<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }

    @RequestMapping(value = "/outCash", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提现",  notes = "提交提现申请，后台处理")
    public Tip<String> outCash(
                              @ApiIgnore HttpSession session,
                              @ApiParam(name = "money",value = "金额")
                              @RequestParam Long money
    ){
        User user = (User) session.getAttribute(Const.USER);
        long value = Math.abs(money);
        try {
            int status = moneyService.insertByType(user.getId(),2,-value,user.getId());
            if(status == 1){
                return new Tip<>();
            }else{
                return new Tip<>(2,"余额不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }


    @RequestMapping(value = "/listByMy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "我的资金记录",  notes = "获取我的收支列表（分页）")
    public Tip<PageInfo<Money>> listByMy(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "type",value = "收入支出。0支出，1收入")
            @RequestParam Integer type,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "size",value = "每页大小")
            @RequestParam Integer size
    ){

        Map<String,String> map  = new HashMap<>();
        map.put("type",type.toString());

        User user = (User) session.getAttribute(Const.USER);
        map.put("userId",user.getId().toString());
        PageInfo<Money> targetPageInfo = moneyService.listParam(map,page,size);

        return new Tip<>(targetPageInfo);
    }


    //---------------------------- property -------------------------------

    @Resource
    private MoneyService moneyService;

    @Resource
    private UserService userService;

}
