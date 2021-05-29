package org.leon.finch.service.script;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.leon.finch.common.util.JavaCodeUtil;
import org.leon.finch.common.util.PathUtil;
import org.leon.finch.common.util.StringUtil;

import javax.tools.*;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 动态脚本相关功能
 *
 * @author Leon Song
 * @date 2021-05-29
 */
@Slf4j
public class DynamicScriptService {


    private <T> T loadScript(String scriptCode, String javaContent, Class<T> handlerClass) {

        String simpleClassname = JavaCodeUtil.getImplClassSimpleName(javaContent, DynamicScriptHandler.class);

        String packageName = this.getPackageNameOf(handlerClass, scriptCode);

        javaContent = JavaCodeUtil.setPackage(javaContent, packageName);


        String className = StringUtil.format("{}.{}", packageName, simpleClassname);

        log.info("实现类全类名为:{}", className);

        ScriptClassloader classloader = new ScriptClassloader(handlerClass);

        Thread.currentThread().setContextClassLoader(handlerClass.getClassLoader());

        T handler = null;

        try {

            log.info("开始动态加载java代码");
            Class clazz = this.loadFromJava(classloader, className, javaContent);

            Object instance = clazz.newInstance();

            handler = (T) instance;

        } catch (Throwable throwable) {
            log.error("脚本编译出错:{}", throwable);
        }

        return handler;

    }

    /**
     * 获得动态脚本的包名
     *
     * @param interfaceName 实现接口
     * @param scriptCode    脚本标识
     * @return 包名
     */
    public String getPackageNameOf(Class interfaceName, String scriptCode) {
        return StringUtil.format("{}.{}", interfaceName.getPackage().getName().toLowerCase(), scriptCode);
    }


    @SneakyThrows
    private Class loadFromJava(ClassLoader classLoader, String className, String javaContent) {

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        ScriptFileManager scriptFileManager = new ScriptFileManager(fileManager);


        Map<String, byte[]> dataMap = this.compileFromJava(className, javaContent, scriptFileManager);
        byte[] data = dataMap.get(className);

        ((ScriptClassloader) classLoader).setData(data);

        return classLoader.loadClass(className);
    }

    private Map<String, byte[]> compileFromJava(String className, String javaContent, ScriptFileManager fileManager) {

        Collection<JavaFileObject> compilationUnits = new ArrayList<>();
        compilationUnits.add(new JavaSourceFromString(className, javaContent));

        List<String> options = new ArrayList<>();

        String appHomePath = PathUtil.getAppHomePath();
        String classpath = StringUtil.format(".:{}/finch-service/target/finch-service-0.0.1-SNAPSHOT.jar", appHomePath);

        // 必须要设置路径，要不然会报错
        options.add("-classpath");
        options.add(classpath);

        StringWriter errorStringWriter = new StringWriter();

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        Boolean success = javaCompiler.getTask(errorStringWriter, fileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();

        if (!success) {
            String errorMessage = errorStringWriter.toString();
            throw new RuntimeException("Compile Error:" + errorMessage);
        }

        return fileManager.getAllBuffers();
    }


    /**
     * 获取一个类加载器所在的类路径
     *
     * @param classLoader
     * @return
     */
    private String getClasspath(ClassLoader classLoader) {

        StringBuilder sb = new StringBuilder();

        URLClassLoader urlClassLoader = null;

        // 向上搜索URLClassloader的类路径
        ClassLoader temp = classLoader;
        while (temp != null) {

            if (temp instanceof URLClassLoader) {
                urlClassLoader = (URLClassLoader) temp;
                break;
            }
            temp = temp.getParent();
        }

        if (urlClassLoader == null) {
            return null;
        }

        for (URL url : urlClassLoader.getURLs()) {

            String fileName = url.getFile();

            sb.append(fileName).append(File.pathSeparator);

        }

        return sb.toString();

    }


    public static void main(String[] args) {

        String javaContent = "import org.leon.finch.service.script.DynamicScriptHandler;\n" +
                "public class DynamicScriptHandlerImpl implements DynamicScriptHandler {\n" +
                "    @Override\n" +
                "    public Object handle() {\n" +
                "        return \"OK\";\n" +
                "    }\n" +
                "}";

        DynamicScriptService dynamicScriptService = new DynamicScriptService();

        DynamicScriptHandler test = dynamicScriptService.loadScript("test", javaContent, DynamicScriptHandler.class);

        System.out.println(test.handle());

    }


}
