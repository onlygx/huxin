package com.elangzhi.modules.money.app;

import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 支付宝 Controller
 * @author GaoXiang
 * @version 1.0
 */
@Controller
@RequestMapping("/app/alipay")
public class AppAlipayController {


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

    @RequestMapping(value = "/zfqm")
    @ResponseBody
    public Tip<String> zfqm(){

        return new Tip<>();
    }







}
