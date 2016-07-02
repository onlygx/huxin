package com.elangzhi.generator.generator;

import com.elangzhi.ssm.model.*;

/**
 * 生成代码
 * Created by GaoXiang
 */
public class Start {

    public static void main(String[] args) {

        new ModuleConfig("用户",User.class).startGenerator();

    }
}
