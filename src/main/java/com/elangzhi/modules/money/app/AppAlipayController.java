package com.elangzhi.modules.money.app;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
import com.elangzhi.ssm.tools.AlipayConfig;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public Tip<String> zfqm(String price){

        Map<String,String> params = new HashMap<>();
        params.put("service","mobile.securitypay.pay");//接口名称，固定值。
        params.put("partner",AlipayConfig.PARTNER);//	合作者身份ID
        params.put("_input_charset",AlipayConfig.INPUTCHARSET);//参数编码字符集
        params.put("sign_type","");//签名方式
        params.put("sign","");//签名
        params.put("notify_url","");//服务器异步通知页面路径
        params.put("out_trade_no","");//商户网站唯一订单号
        params.put("subject","");//商品名称
        params.put("payment_type","");//支付类型
        params.put("seller_id","");//	卖家支付宝账号
        params.put("total_fee","");//总金额
        params.put("body","");//商品详情
        params.put("goods_type","0");//具体区分本地交易的商品类型。 1：实物交易； 0：虚拟交易。

        // 对订单做RSA 签名
        try {
            String rsaSign = AlipaySignature.rsaSign(params,AlipayConfig.RSA_PRIVATE,"UTF-8");

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }



        return new Tip<>();
    }


    public String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + AlipayConfig.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + AlipayConfig.SELLER + "\"";

        // 商户网站唯一订单号
        //orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm";

        return orderInfo;
    }






}
