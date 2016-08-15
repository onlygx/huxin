package com.elangzhi.modules.money.app;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.elangzhi.modules.money.services.MoneyService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
import com.elangzhi.ssm.model.Money;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.*;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
    public String zfResult(HttpSession session, HttpServletRequest request){
        ParamMap param = new ParamMap(request);
        String signType = "RSA";                                                                //签名方式
        String sign = param.get("sign").toString();                                    //签名
        String outTradeNo = param.get("out_trade_no").toString();    //商户网站唯一订单号
        String tradeStatus = param.get("trade_status").toString();     //交易状态
        String tradeNo = param.get("trade_no").toString();                   //支付宝交易号
        String buyerEmail = param.get("buyer_email").toString();          //买家支付宝账号
        String notifyId = param.get("notify_id").toString();                  //通知校验ID



        Map<String,String> params = new HashMap<>();
        param.remove("sign_type");
        param.remove("sign");
        for(String key : param.keySet()){
            params.put(key,param.get(key).toString());
        }

        if(notifyId!="" && notifyId!=null){////判断接受的post通知中有无notify_id，如果有则是异步通知。
            if(AlipayNotify.verifyResponse(notifyId).equals("true"))//判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
            {
                if(AlipayNotify.getSignVeryfy(params, sign))//使用支付宝公钥验签
                {

                    Money money = null ;
                    try {
                        if(outTradeNo.isEmpty()){
                            return "error";
                        }
                        if(tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")){
                            //验证通过
                            money = moneyService.selectById(Long.parseLong(outTradeNo));
                            User user = userService.selectById(money.getUserId());
                            if(money != null && money.getStatus() == 2){
                                Double value = user.getMoney() + money.getMoney();
                                user.setMoney(value);
                                userService.updateById(user);
                                money.setStatus(1);
                                money.setZfb(buyerEmail);
                                money.setIntro(param.toString());
                                moneyService.updateById(money);
                            }
                        }else{
                            return "error";
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        try {
            boolean isCheck = AlipaySignature.rsaCheck(AlipaySignature.getSignContent(params),sign,AlipayConfig.RSA_PUBLIC,"UTF-8",signType);
            System.out.println("我自己的验证结果");
            System.out.println(isCheck);
        } catch (AlipayApiException e) {
            e.printStackTrace();

        }

        System.out.println("支付宝回调：");

        System.out.println(param);

        return "success";
    }


    private Map<String,String> createParams(String id,String sMoney){
        Map<String,String> params = new HashMap<>();
        params.put("service",AlipayConfig.SERVICE);//接口名称，固定值。
        params.put("partner",AlipayConfig.PARTNER);//	合作者身份ID
        params.put("_input_charset",AlipayConfig.INPUT_CHARSET);//参数编码字符集
/*        params.put("sign_type",AlipayConfig.SIGN_TYPE);//签名方式
        params.put("sign",AlipayConfig.RSA_PRIVATE);//签名*/
        params.put("notify_url",AlipayConfig.NOTIFY_URL);//服务器异步通知页面路径
        params.put("out_trade_no",id);//商户网站唯一订单号
        params.put("subject","互信充值");//商品名称
        params.put("payment_type","1");//支付类型
        params.put("seller_id",AlipayConfig.SELLER);//	卖家支付宝账号
        params.put("total_fee",sMoney);//总金额
        params.put("body","充值用。");//商品详情
        params.put("goods_type","0");//具体区分本地交易的商品类型。 1：实物交易； 0：虚拟交易。
        return params;
    }


    private void getParam(ParamMap param){
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
    }

    @RequestMapping(value = "/zfqm")
    @ResponseBody
    public Tip<String> zfqm2(Double price,HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        Double money = Math.abs(price);

        Money moneyRecord = new Money();
        try {
            moneyRecord.setId(UUIDFactory.getLongId());
            moneyRecord.setSetTime(new Date());
            moneyRecord.setInfoId(user.getId());
            moneyRecord.setUserId(user.getId());
            moneyRecord.setMoney(money);
            moneyRecord.setType(1);
            moneyRecord.setStatus(2);
            moneyRecord.setIntro("充值");
            moneyService.insert(moneyRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(2,"创建订单失败！");
        }

        Map<String,String> params = createParams(moneyRecord.getId().toString(),money.toString());

        if(params!=null&&params.size()>0)
        {
            //partner
            String partner=AlipayConfig.PARTNER;
            AlipayCore.logResult(partner,"partner");
            //接口名
            String service=AlipayConfig.SERVICE;
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            if(partner.replace("\"","").equals(AlipayConfig.PARTNER)&& service.replace("\"","").equals(AlipayConfig.SERVICE)){//确认PID和接口名称。

                //将post接收到的数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串。需要排序。
                String data=AlipayCore.createLinkString(params);

                //打印待签名字符串。工程目录下的log文件夹中。
                AlipayCore.logResult(data,"datashuju");

                //将待签名字符串使用私钥签名。
                String rsa_sign= null;
                try {
                    rsa_sign = URLEncoder.encode(RSA.sign(data, AlipayConfig.RSA_PRIVATE, AlipayConfig.INPUT_CHARSET),AlipayConfig.INPUT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //把签名得到的sign和签名类型sign_type拼接在待签名字符串后面。
                data = data+"&sign="+rsa_sign+"&sign_type="+AlipayConfig.SIGN_TYPE;

                //返回给客户端,建议在客户端使用私钥对应的公钥做一次验签，保证不是他人传输。
                return new Tip<>(data);
            }
            else
            {
                return new Tip<>(1,"客户端信息与服务端配置信息有误！");
            }
        }
        else
        {
            return new Tip<>(2,"无客户端信息!");
        }
    }

    @Resource
    private MoneyService moneyService;

    @Resource
    private UserService userService;



}
