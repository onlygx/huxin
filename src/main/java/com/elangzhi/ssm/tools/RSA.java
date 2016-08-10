
package com.elangzhi.ssm.tools;

import com.alipay.api.internal.util.StreamUtil;
import com.alipay.api.internal.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA{
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	/*PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) );
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
			PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);*/
			PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            java.security.Signature signature = java.security.Signature  .getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }


	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if(ins != null && !StringUtils.isEmpty(algorithm)) {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			byte[] encodedKey = StreamUtil.readText(ins).getBytes();
			encodedKey = com.alipay.api.internal.util.codec.Base64.decodeBase64(encodedKey);
			return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
		} else {
			return null;
		}
	}

	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param alipay_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String alipay_public_key, String input_charset)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(alipay_public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
