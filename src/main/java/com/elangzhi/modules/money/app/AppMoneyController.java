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
    @ApiOperation(value = "充值",  notes = "使用支付宝充值")
    public Tip<Money> addByZFB(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "money",value = "金额")
            @RequestParam Long money){
        User user = (User) session.getAttribute(Const.USER);
        money = Math.abs(money);

        Money moneyRecord = new Money();
        moneyRecord.setId(UUIDFactory.getLongId());
        moneyRecord.setSetTime(new Date());
        moneyRecord.setType(1);
        moneyRecord.setInfoId(user.getId());
        moneyRecord.setUserId(user.getId());
        moneyRecord.setMoney(money);
        moneyRecord.setIntro("支付宝充值");
        try {
            moneyService.insert(moneyRecord);

            //更新资金
            user.setMoney(user.getMoney() + money);
            userService.updateById(user);

            return new Tip<>(moneyRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }

    @RequestMapping(value = "/outCash", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提现",  notes = "提交提现申请，后台处理")
    public Tip<Money> outCash(
                              @ApiIgnore HttpSession session,
                              @ApiParam(name = "money",value = "金额")
                              @RequestParam Long money
    ){
        User user = (User) session.getAttribute(Const.USER);
        money = Math.abs(money);

        Money moneyRecord = new Money();
        moneyRecord.setId(UUIDFactory.getLongId());
        moneyRecord.setSetTime(new Date());
        moneyRecord.setType(2);
        moneyRecord.setInfoId(user.getId());
        moneyRecord.setUserId(user.getId());
        moneyRecord.setMoney(Long.parseLong("-" + money));
        moneyRecord.setIntro("支付宝充值");
        try {
            moneyService.insert(moneyRecord);

            //更新资金
            user.setMoney(user.getMoney() - money);
            userService.updateById(user);

            return new Tip<>(moneyRecord);
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
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "id",value = "每页大小")
            @RequestParam Integer size
    ){
        User user = (User) session.getAttribute(Const.USER);
        PageInfo<Money> targetPageInfo = moneyService.listByUserId(user.getId(),page,size);
        return new Tip<>(targetPageInfo);
    }


    //---------------------------- property -------------------------------

    @Resource
    private MoneyService moneyService;

    @Resource
    private UserService userService;

}
