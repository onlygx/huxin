package com.elangzhi.modules.money.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Money;
import com.elangzhi.modules.money.services.MoneyService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

/**
* 钱包 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/money")
public class MoneyController extends AdminBaseController<Money> {



    @RequestMapping(value="/listByMoney")
    public ModelAndView list(Money money, Integer page, Integer size, ModelMap model) throws Exception {
        PageInfo<Money> pageInfo =  moneyService.listByMoney(money,page,size);
        model.put("pageInfo",pageInfo);
        return new ModelAndView("admin/money/listHistory",model);
    }


    //---------------------------- property -------------------------------

    @Resource
    private MoneyService moneyService;

}
