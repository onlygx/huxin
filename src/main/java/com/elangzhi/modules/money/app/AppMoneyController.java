package com.elangzhi.modules.money.app;

import com.elangzhi.modules.money.services.MoneyService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
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
import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "/zfResult")
    @ResponseBody
    @ApiIgnore
    public void zfResult(HttpSession session, HttpServletRequest request){
        ParamMap param = new ParamMap(request);
        String notifyTime = param.get("notify_time").toString();         //通知时间
        String notifyType = param.get("notify_type").toString();         //通知类型
        String notifyId = param.get("notify_id").toString();                  //通知校验ID
        String signType = param.get("sign_type").toString();                 //签名方式
        String sign = param.get("sign").toString();                                    //签名
        String outTradeNo = param.get("out_trade_no").toString();    //商户网站唯一订单号
        String subject = param.get("subject").toString();                       //商品名称
        String paymentType = param.get("payment_type").toString();  //支付类型
        String tradeNo = param.get("trade_no").toString();                   //支付宝交易号
        String tradeStatus = param.get("trade_status").toString();     //交易状态
        String sellerId = param.get("seller_id").toString();                     //卖家支付宝用户号
        String sellerEmail = param.get("seller_email").toString();           //卖家支付宝账号
        String buyerId = param.get("buyer_id").toString();                    //买家支付宝用户号
        String buyerEmail = param.get("buyer_email").toString();          //买家支付宝账号
        String totalFee = param.get("total_fee").toString();                  //交易金额
        String quantity = param.get("quantity").toString();                     //购买数量
        String price = param.get("price").toString();                                //商品单价
        String body = param.get("body").toString();                                //商品描述
        String gmtCreate = param.get("gmt_create").toString();           //交易创建时间
        String gmtPayment = param.get("gmt_payment").toString();     //交易付款时间
        String isTotalFeeSdjust = param.get("is_total_fee_adjust").toString();          //是否调整总价
        String useCoupon = param.get("use_coupon").toString();                                     //是否使用红包买家
        String discount = param.get("discount").toString();                                             //折扣
        String refundStatus = param.get("refund_status").toString();                          //退款状态
        String gmtRefund = param.get("gmt_refund").toString();                                   //退款时间



        System.out.println("支付宝回调：");

        System.out.println(param);
    }


    @RequestMapping(value = "/addCash", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "充值",  notes = "充值到钱包")
    public Tip<Money> addCash(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "money",value = "金额")
            @RequestParam Double money){
        User user = (User) session.getAttribute(Const.USER);
        Double value = Math.abs(money);
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
    @ApiOperation(value = "提现到支付宝",  notes = "提交提现申请，后台处理")
    public Tip<String> outCash(
                              @ApiIgnore HttpSession session,
                              @ApiParam(name = "money",value = "金额")
                              @RequestParam Double money
    ){
        User user = (User) session.getAttribute(Const.USER);
        if(user.getZfb() == null || user.getZfb().equals("")){
            return new Tip<>(3,"请先绑定支付宝！");
        }
        Double value = Math.abs(money);
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
