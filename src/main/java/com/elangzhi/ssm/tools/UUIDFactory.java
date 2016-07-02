package com.elangzhi.ssm.tools;

import java.util.Random;
import java.util.UUID;

/**
 * Created by GaoXiang on 2015/12/29 0029.
 */
public class UUIDFactory {

    /**
     * 生成一个Long类型的id
     * @return Long
     */
    public static Long getLongId(){

        Long id = UUID.randomUUID().getMostSignificantBits();

        return Math.abs(id);
    }


    /**
     * 生成一个六位验证码
     * @return 验证码
     */
    public static String getCode6(){

        String charValue = "";
        for (int i = 0; i < 6; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }


    /**
     * 获取随机数
     * @param from
     * @param to
     * @return
     */
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }


    public static void main(String[] args) {

        for(int i = 0;i < 1000;i++ ){
            Long id = getLongId();

            System.out.println(id);
        }
    }

}
