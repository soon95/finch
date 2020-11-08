package org.leon.finch.common.util;

import lombok.NonNull;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Map;

/**
 * 渲染模板的工具类
 *
 * @author Leon Song
 * @date 2020-11-08
 */
public class TemplateUtil {

    public static String render(@NonNull String template, @NonNull Map<String, Object> params) {

        VelocityContext context = new VelocityContext();

        // 参数装到模板引擎上下文中
        for (Map.Entry<String, Object> param : params.entrySet()) {

            context.put(param.getKey(), param.getValue());

        }

        StringWriter writer = new StringWriter();

        Velocity.evaluate(context, writer, "", template);

        return writer.toString();
    }

}
