package org.leon.finch.common.util;

import lombok.SneakyThrows;
import org.leon.finch.common.exception.BadRequestException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 路径通用类
 *
 * @author Leon Song
 * @date 2020-11-07
 */
public class PathUtil {

    /**
     * 获得当前应用的根目录
     * 例如: /Users/soon/MyFiles/Code/finch
     */
    public static String getAppHomePath() {
        return Paths.get("").toAbsolutePath().toString();
    }

    /**
     * 获得当前应用名
     * 例如: finch
     */
    public static String getAppName() {
        String path = getAppHomePath();

        int index = path.lastIndexOf("/");

        return path.substring(index + 1);
    }

    /**
     * 获得类的所在包名
     */
    public static String getPackageName(Class clazz) {
        return clazz.getPackage().getName();
    }


    /**
     * 以resources目录下的某个文件为指南针，获取当前模块路径
     *
     * @param compassFile 指南针文件 需要位于resources下
     */
    public static String getModuleHomePath(String compassFile) {

        URL url = PathUtil.class.getClassLoader().getResource(compassFile);

        if (null == url) {
            throw new BadRequestException("文件:{}不存在，请修复", compassFile);
        }

        String path = url.getPath();

        // windows系统中环境路径比较特殊 需要去掉 file:/
        String osName = System.getProperties().getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            path = path.substring(path.indexOf("/") + 1);
        }

        // 如果 指南针文件是一个jar包，那么返回的就是其对应的解压包名，也就是去掉.jar的文件，需要保证jar包旁边有个直接解压的文件
        int index = path.indexOf(".jar");
        if (index == -1) {
            index = path.indexOf("/target/classes");
            if (index == -1) {
                index = path.indexOf("/target/test-classes");
            }
        }

        return path.substring(0, index);
    }

    /**
     * 返回上一级路径
     */
    public static String prePath(String path) {
        if (StringUtil.isBlank(path)) {
            return null;
        }

        if (path.endsWith("/")) {
            return path.substring(0, path.length() - 1);
        } else {
            int index = path.lastIndexOf("/");
            return path.substring(0, index);
        }
    }

    public static String prePackage(String packagePath) {
        if (StringUtil.isBlank(packagePath)) {
            return null;
        }

        int index = packagePath.lastIndexOf(".");
        return packagePath.substring(0, index);

    }

    /**
     * 判断文件是否存在
     */
    public static boolean isExist(String filePath) {

        Path path = Paths.get(filePath);

        return Files.exists(path);
    }


    /**
     * 获取一个安全的文件夹路径
     * 没有则创建
     */
    @SneakyThrows
    public static String getSafeDirectoryPath(String directoryPath) {

        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        return directoryPath;
    }

}
