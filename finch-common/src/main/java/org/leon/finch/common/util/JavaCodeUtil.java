package org.leon.finch.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leon Song
 * @date 2021-05-29
 */
@Slf4j
public class JavaCodeUtil {

    /**
     * 根据一段java代码，解析出其中的实现类类名
     *
     * @param javaContent    java代码
     * @param interfaceClass 实现接口名
     * @return 实现类名
     */
    public static String getImplClassSimpleName(String javaContent, Class interfaceClass) {

        String regex = StringUtil.format("public\\s+class\\s(\\w+)\\s+implements\\s+{}", interfaceClass.getSimpleName());

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(javaContent);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            log.info("代码无法解析类名");
            log.info(interfaceClass.getName());
            log.info(javaContent);
            return null;
        }

    }

    /**
     * 给一段java代码设置上包名
     *
     * @param javaContent java代码
     * @param packageName 包名
     * @return 渲染之后的代码
     */
    public static String setPackage(String javaContent, String packageName) {

        javaContent = removePackage(javaContent);

        return StringUtil.format("package {};\n{}", packageName, javaContent);
    }

    /**
     * 去除一段java代码中的 包声明
     *
     * @param javaContent java代码
     * @return 去除之后的java代码
     */
    public static String removePackage(String javaContent) {

        String regex = "\\s*package[\\s\\w.]+;";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(javaContent);

        return matcher.replaceAll("");
    }


}
