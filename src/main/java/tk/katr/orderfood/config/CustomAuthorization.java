package tk.katr.orderfood.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 添加到方法或者类上，表示放行该接口，不需要验证
 */
public @interface CustomAuthorization {
}
