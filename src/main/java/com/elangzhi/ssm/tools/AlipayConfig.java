package com.elangzhi.ssm.tools;


public class AlipayConfig {


    //PID 在这里填上我们上面找到的PID;
    //public static final String PARTNER = "2088812135870881";
    public static final String PARTNER = "2088102168883782";

    // 商户收款账号 然后在SELLER上写上我们支付宝的登录帐户，即那个你申请移动支付的支付宝账号
   // public static final String SELLER = "763348774@qq.com";
    public static final String SELLER = "lcxxor7216@sandbox.com";

    // 支付宝公钥 然后在RSA_PUBLIC这里填上支付宝公钥
    //public static final String RSA_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    public static final String RSA_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMZ1vOKyFpa0bwDLH7rComDAjATw1O3aw1l7Ok8d/jKuD+Jh1bU7/ijigqXQ4Kp+7krd8pCf+XEjemlTPiPc8Z/CfUkrAlI8TqYeXGXBR2gl60c+Msnma7/41GluVbqpATBgEqfABVYcpCNh40BOdZ17K1la5mAwSJsDFm3H/1NjAgMBAAECgYBFo8j9L4rXpUcSWzSuH2BFXSuFDFFbiZllC+XBH4A0nnm6caRBLWUZSaqYrvw3N8U8hjd44nnwZoes7+XtRbmPO5v5SF/zBSj5yBz0tIlH/j/AGWYu7cM9y4ItpaYEg7zgSId4VN3nVC4AmitHcUDXaymllIi1hTTG+hPLqUOSoQJBAP6x4+hlq70qIaibwU04zicFDynepa2tT0HFpkER/sE7YKWCWQlYxq1GwaSbE4CU8WTC0PvErP4ifzyrQn+k2pECQQDHehQMmPBmaFsU7ibNYSN6z6l3lqxSGbcuxTCmU9y/RUKa9oh7+/CJnC3fv2EsftlOIkKM5xnjdEkjngiYUoCzAkBNy6RjrfqBM0dIqxqmLb26aW8ySNGudQuKeYbIxWhdOXfR1jjABB/beYtYbg3M7rG1J1SSMobssTjQHTeYqZAhAkAnxC2FCWQ7dihaNtPjc68IB6gIIDCAOYIsP8FgFy3Vr7AEhotU9DfSpyD6DwQHQ858ZhYQUu31SzRddl7ORvxDAkBmPLkqBxJRldnTz+uQSh+P4glHjXd4lSi//BSp3I0lOAUwwbdzbcfPVkaJdpVyG4FoA9/pz/ujIRdszDTB5ZGw";

    //商户网站使用的编码格式，固定为UTF-8。
    public static final String INPUT_CHARSET = "UTF-8";

    //签名类型，目前仅支持RSA。
    public static final String SIGN_TYPE = "RSA";

    //异步通知url
    public static final String NOTIFY_URL = "http://onlygx.eicp.net/app/alipay/zfResult";

    //接口名称。
    public static final String SERVICE = "mobile.securitypay.pay";

}