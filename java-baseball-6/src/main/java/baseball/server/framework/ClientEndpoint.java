package baseball.server.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 클라이언트의 엔드포인트를 나타내는 어노테이션
 * <p>
 * 해당 어노테이션이 붙은 메서드는 클라이언트의 엔드포인트를 나타낸다
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClientEndpoint {

  String endPoint() default "";
}

