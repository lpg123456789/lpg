package flower.com.game.module.activity.defines;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;

import flower.com.game.module.activity.annotation.ActivityTags;
import flower.com.game.module.activity.types.ActivityTag;
import flower.common.utils.ClazzUtil;

import java.util.*;
import java.util.function.Predicate;

public enum ActivityType {

    /**
     * 势力冲榜
     */
    @ActivityTags(ActivityTag.DEIFICATION_RANK)
    INFLUENCE_RANK(101),

    /**
     * 登录送服装活动
     */
    FAIRLOGIN(2101),

    ;
    private final int value;

    ActivityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static ListMultimap<ActivityTag, ActivityType> tagToActivityTypes = ArrayListMultimap.create();
    static {
        for (ActivityType value : values()) {
            ActivityTags tags = ClazzUtil.getFiledAnnotation(value, ActivityTags.class);
            if (tags == null) {
                continue;
            }
            for (ActivityTag activityTag : tags.value()) {
                tagToActivityTypes.put(activityTag, value);
            }
        }
    }

    private static final Map<Integer, ActivityType> typesMap = Maps.uniqueIndex(Arrays.asList(ActivityType.values()), t -> {
        assert t != null;
        return t.getValue();
    });

    public static ActivityType get(int type) {
        return typesMap.get(type);
    }

    /**
     * 获取所有冲榜活动类型
     * 
     * @return List
     */
    public static List<ActivityType> getRankActivities() {
        return getActivitiesByTag(ActivityTag.DEIFICATION_RANK);
    }

    /**
     * 获取所有冲榜活动类型
     * 
     * @return List
     */
    public static List<ActivityType> getAllianceRankActivities() {
        return getActivitiesByTag(ActivityTag.ALLIANCE_RUSH);
    }

    /**
     * 活动是否包含了指定 tag
     * 
     * @param tag {@link ActivityTag}
     * @return true/false
     */
    public boolean hasActivityTag(ActivityTag tag) {
        return tagToActivityTypes.get(tag).contains(this);
    }

    /**
     * 获取所有冲榜抢购活动
     * 
     * @return List
     */
    public static List<ActivityType> getRankPanicBuyActivities() {
        return getActivitiesByTag(ActivityTag.PANIC_BUY);
    }

    public static List<ActivityType> getActivityTypes(Predicate<ActivityType> predicate) {
        List<ActivityType> activityTypes = new ArrayList<>();
        for (ActivityType value : values()) {
            if (predicate.test(value)) {
                activityTypes.add(value);
            }
        }
        return activityTypes;
    }

    /**
     * 通过活动标签获取活动类型
     * 
     * @param tag {@link ActivityTag}
     * @return List
     */
    public static List<ActivityType> getActivitiesByTag(ActivityTag tag) {
        List<ActivityType> activityTypes = tagToActivityTypes.get(tag);
        if (activityTypes == null) {
            return Collections.emptyList();
        }
        return activityTypes;
    }

    public static boolean containsActivityType(ActivityTag tag, int activityType) {
        List<ActivityType> activityTypes = getActivitiesByTag(tag);
        for (ActivityType at : activityTypes) {
            if (at.getValue() == activityType) {
                return true;
            }
        }
        return false;
    }

}
