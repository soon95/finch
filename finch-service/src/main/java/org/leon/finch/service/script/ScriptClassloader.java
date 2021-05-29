package org.leon.finch.service.script;

import lombok.Data;

/**
 * 一个自定义的类加载器
 *
 * @author Leon Song
 * @date 2021-05-29
 */
@Data
public class ScriptClassloader extends ClassLoader {

    private byte[] data;

    public ScriptClassloader(Class<?> clazz) {
        super(clazz.getClassLoader());
    }


    @Override
    protected Class<?> findClass(String name) {
        return defineClass(name, this.data, 0, data.length);

    }
}
