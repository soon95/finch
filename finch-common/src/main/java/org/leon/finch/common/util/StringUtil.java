package org.leon.finch.common.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.Random;

/**
 * 字符串处理的通用方法
 *
 * @author Leon Song
 * @date 2020-11-01
 */
public class StringUtil extends StringUtils {

    private static String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    /**
     * 获取长度为n的字符串
     *
     * @param n 字符串长度
     * @return 一个长度为n的随机字符串
     */
    public static String randomString(int n) {
        String rawString = "abcdefghijklmnopqrstuvwxyz0123456789";
        int rawLength = rawString.length();
        String res = "";
        for (int i = 0; i < n; i++) {
            int pos = new Random().nextInt(rawLength);
            res += rawString.charAt(pos);
        }
        return res;
    }

    /**
     * 将阿拉伯数字转成中文。
     */
    public static String numberToChinese(int num) {

        String[] chnNumChar = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] chnUnitSection = new String[]{"", "万", "亿", "万亿", "亿亿"};
        String[] chnUnitChar = new String[]{"", "十", "百", "千"};

        String strIns = "";
        String chnStr = "";
        int unitPos = 0;
        boolean zero = true;
        while (num > 0) {
            int v = num % 10;
            if (v == 0) {
                if (!zero) {
                    zero = true;
                    chnStr = chnNumChar[v] + chnStr;
                }
            } else {
                zero = false;
                strIns = chnNumChar[v];
                strIns += chnUnitChar[unitPos];
                chnStr = strIns + chnStr;
            }
            unitPos++;
            num = num / 10;
        }
        return chnStr;

    }

    /**
     * 名词变复数归纳总结
     * 1.一般情况下，在名词后加“s”或“es”.
     * 2.以s,sh,ch,x结尾的名字，在名词后直接加“es”.
     * 3.以o结尾的名字，有两种情况：
     * 1）有生命的名词，在名词后加“es”.
     * 如：tomato-tomatoes potato-potatoes
     * 2)无生命的名字，在名字后加“s”.
     * 如：photo-photos radio-radios
     * 注意：使用java一律采用加“s”的策略
     * 4.以辅音字母+y结尾的名词,将y改变为i,再加-es.
     * 元音字母+y结尾的名词则直接加s
     */
    public static String toPlural(String singular) {

        if (singular == null || "".equals(singular)) {
            return singular;
        }
        int length = singular.length();
        //一个字母的直接加个s.
        if (length == 1) {
            return singular + "s";
        }

        char lastChar = singular.charAt(length - 1);
        char lastSecondChar = singular.charAt(length - 2);
        if (lastChar == 's' || lastChar == 'x' || (lastChar == 'h' && (lastSecondChar == 's' || lastSecondChar == 'c'))) {
            return singular + "es";
        } else if (lastChar == 'y' && (lastSecondChar != 'a' && lastSecondChar != 'e' && lastSecondChar != 'i' && lastSecondChar != 'o' && lastSecondChar != 'u')) {
            return singular.substring(0, length - 1) + "ies";
        } else {
            return singular + "s";
        }

    }

    /**
     * 把一个字符串转成下划线的大写格式
     */
    public static String upperCamelToUpperUnderscore(String name) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);
    }

    /**
     * 把一个大写驼峰转成小写驼峰
     */
    public static String upperCamelToLowerCamel(String name) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);
    }

    /**
     * 把一个大写驼峰转成小写驼峰
     */
    public static String lowerCamelToUpperCamel(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
    }

    /**
     * 把一个下划线风格的字符串转成首字母大写驼峰法字符串
     */
    public static String underscoreToUpperCamel(String name) {
        if (isBlank(name)) {
            return name;
        }

        name = name.toUpperCase();

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    /**
     * 把一个下划线风格的字符串转成首字母小写驼峰法字符串
     */
    public static String underscoreToLowerCamel(String name) {
        if (isBlank(name)) {
            return name;
        }

        name = name.toUpperCase();

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }


    /**
     * 类似于sl4j的字符串格式化.使用 {} 做占位符。
     */
    public static String format(String messagePattern, Object... arguments) {
        return MessageFormatter.arrayFormat(messagePattern, arguments).getMessage();
    }

    /**
     * 将10进制转换成N进制
     *
     * @param tenRadixNumber 十进制数
     * @param radix          进制编码支持10+26=36进制
     */
    public static String radixTenToN(long tenRadixNumber, int radix) {

        if (tenRadixNumber == 0) {
            return CHARS.charAt(0) + "";
        }

        StringBuilder buf = new StringBuilder();
        int remainder;
        while (tenRadixNumber != 0) {
            // 求余数
            remainder = (int) (tenRadixNumber % radix);
            // 除以基数
            tenRadixNumber = tenRadixNumber / radix;
            // 保存余数，记得要倒叙排列
            buf.append(CHARS.charAt(remainder));
        }
        buf.reverse();// 倒叙排列
        return buf.toString();
    }


    /**
     * @param keyword 需要进行模糊搜索的词
     */
    public static String safeLike(String keyword) {

        if (keyword == null) {
            return null;
        }

        keyword = keyword.replaceAll("_", "\\\\_");

        keyword = keyword.replaceAll("%", "\\\\%");
        return keyword;

    }


}
