package com.elangzhi.modules.bankCard.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.BankCard;
import com.elangzhi.modules.bankCard.services.BankCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 提现银行卡 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/bankCard")
public class BankCardController extends AdminBaseController<BankCard> {







    //---------------------------- property -------------------------------

    @Resource
    private BankCardService bankCardService;

}
