package com.elangzhi.modules.money.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Money;
import com.elangzhi.modules.money.services.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 钱包 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/money")
public class MoneyController extends AdminBaseController<Money> {







    //---------------------------- property -------------------------------

    @Resource
    private MoneyService moneyService;

}
