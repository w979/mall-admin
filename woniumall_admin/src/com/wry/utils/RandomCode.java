package com.wry.utils;

import java.util.Random;

/**
 * 生成随机码
 */
public class RandomCode {

    /**
     * 生成16位商品编号
     * @return
     */
    public static String getGoodsNo(){
        // 随机产生6位激活码
        String[] codes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            String str = codes[random.nextInt(codes.length)];
            code += str;
        }
        return code;
    }
}
