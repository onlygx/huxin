package com.elangzhi.modules.bankCard.app;

import com.elangzhi.modules.bankCard.services.BankCardService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.BankCard;
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
import java.util.List;

/**
* 提现银行卡 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/bankCard")
public class AppBankCardController {

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交提现银行卡信息",  notes = "提交提现银行卡信息")
    public Tip<BankCard> submit(

            @ApiParam(name = "name",value = "开户人")
            @RequestParam String name,

            @ApiParam(name = "number",value = "银行卡号")
            @RequestParam String number,

            @ApiParam(name = "bank",value = "银行名称")
            @RequestParam String bank,

            @ApiParam(name = "home",value = "开户行（支行）")
            @RequestParam String home,

            @ApiIgnore HttpSession session
    ){
        User user = (User)session.getAttribute(Const.USER);

        BankCard bankCard = new BankCard();
        bankCard.setId(UUIDFactory.getLongId());
        bankCard.setSetTime(new Date());
        bankCard.setUserId(user.getId());
        bankCard.setName(name);
        bankCard.setNumber(number);
        bankCard.setBank(bank);
        bankCard.setHome(home);

        try {
            bankCardService.insert(bankCard);
            return new Tip<>(bankCard);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }

    @RequestMapping(value = "/listByMy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取提现银行卡列表",  notes = "获取提现银行卡列表")
    public Tip<List<BankCard>> listByMy(@ApiIgnore HttpSession session){
        User user = (User)session.getAttribute(Const.USER);
        List<BankCard> bankCards = bankCardService.listByUserId(user.getId());
        return new Tip<>(bankCards);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除提现银行卡",  notes = "删除提现银行卡")
    public Tip deleteById(
            @ApiParam(name = "id",value = "数据对应的ID")
            @RequestParam Long id
    ){
        try {
            bankCardService.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(1);
        }
    }



    //---------------------------- property -------------------------------

    @Resource
    private BankCardService bankCardService;

}
