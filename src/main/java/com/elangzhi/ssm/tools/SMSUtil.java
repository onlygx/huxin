package com.elangzhi.ssm.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 短信平台工具
 */
public class SMSUtil {

    private static String apikey = "612d1a51fb80fefa9caece30e46a0eb7";
    private static String username = "qhgh";
    private static String password_md5 = "46c048cadc663f645b07c5dfa2328995";
    private static String encode = "UTF-8";


    /**
     * 发送短信
     * @param phone 手机号
     */
    public static void sendCode(String phone,String code){
        String content = "【互信】您的验证码为：" + code;
        send(phone,content);
    }


    private static void send(String phone,String content){

        //连接超时及读取超时设置
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); //连接超时：30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");	//读取超时：30秒

        //新建一个StringBuffer链接
        StringBuffer buffer = new StringBuffer();

        String mobile = phone;  //手机号,只发一个号码：13800000001。发多个号码：13800000001,13800000002,...N 。使用半角逗号分隔。

        try {


            String contentUrlEncode = URLEncoder.encode(content,encode);  //对短信内容做Urlencode编码操作。注意：如

            //把发送链接存入buffer中，如连接超时，可能是您服务器不支持域名解析，请将下面连接中的：【m.5c.com.cn】修改为IP：【115.28.23.78】
            buffer.append("http://m.5c.com.cn/api/send/index.php?"
                    +"username=" +username
                    +"&password_md5=" +password_md5
                    +"&mobile=" +mobile
                    +"&apikey=" +apikey
                    +"&content=" +contentUrlEncode
                    +"&encode="+encode
            );

            //System.out.println(buffer); //调试功能，输入完整的请求URL地址

            //把buffer链接存入新建的URL中
            URL url = new URL(buffer.toString());

            //打开URL链接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            //使用POST方式发送
            connection.setRequestMethod("POST");

            //使用长链接方式
            connection.setRequestProperty("Connection", "Keep-Alive");

            //发送短信内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            //获取返回值
            String result = reader.readLine();

            //输出result内容，查看返回值，成功为success，错误为error，详见该文档起始注释
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendInvitation(String phone) {
        String content = "您的好友邀请您使用【互助平台】，您可以在各大应用商店搜索到。欢迎体验。";
        send(phone,content);
    }
}
