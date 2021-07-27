package gk.server.shine.persistence.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
/**
 * 表名
 */
public @interface TableName {

    String value();

}
