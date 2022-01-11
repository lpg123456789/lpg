package flower.com.game.module.activity.annotation;


import java.lang.annotation.*;

import flower.com.game.module.activity.types.ActivityTag;

/**
 * @author redback
 * @version 1.00
 * @time 2020-12-1 10:47
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE})
public @interface ActivityTags {

    ActivityTag[] value();

}
