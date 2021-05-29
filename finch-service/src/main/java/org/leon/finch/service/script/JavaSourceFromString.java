package org.leon.finch.service.script;

import org.leon.finch.common.util.StringUtil;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * @author Leon Song
 * @date 2021-05-29
 */
public class JavaSourceFromString extends SimpleJavaFileObject {

    private String code;

    public JavaSourceFromString(String className, String javaContent) {

        super(URI.create(
                StringUtil.format("string:///{}{}", className.replace(".", "/"), Kind.SOURCE.extension)
        ), Kind.SOURCE);

        this.code = javaContent;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
