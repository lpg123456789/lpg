package flower.com.game.module.activity;

import java.util.Optional;

import flower.com.game.module.activity.defines.ActivityStatus;
import flower.com.game.module.activity.defines.ActivityType;
import flower.common.component.LifeCycle;


/**
 * 活动<br>
 * 在指定的时间段开启的玩法/功能<br>
 * 活动在{@link ActivityRegister}注册<br>
 * 
 * 
 * 
 * @author hdh
 *
 */
public interface Activity extends LifeCycle {

    /**
     * 活动类型
     * 
     * @return
     */
    ActivityType getType();

    /**
     * 活动方案id
     * 
     * @return
     */
    int getPlanId();

    /**
     * 活动状态<br>
     * 若暂停 则返回暂停状态
     * 
     * @return
     */
    ActivityStatus getStatus();

    /**
     * 获取原始的活动状态
     * 
     * @return
     */
    ActivityStatus getOriginStatus();

    /**
     * 活动阶段<br>
     * 部分活动 {@link ActivityStatus#BEGIN}期间可能会有多个阶段<br>
     * 
     * @return
     */
    int getStage();

    /**
     * 活动是否进行中<br>
     * {@link ActivityStatus#BEGIN}阶段<br>
     * 
     * @return
     */
    boolean isBegin();

    /**
     * 活动是否在结算阶段<br>
     * {@link ActivityStatus#SETTLE}阶段
     * 
     * @return
     */
    boolean isSettle();

    /**
     * 活动是否开启中<br>
     * 含{@link ActivityStatus#BEGIN}或{@link ActivityStatus#SETTLE}阶段
     * 
     * @return
     */
    boolean isOpen();

    /**
     * 是否在一次活动的时间周期中<br>
     * 进入准备阶段至活动彻底结束 视为一次活动时间周期<br>
     * 含{@link ActivityStatus#PREPARE}或
     * {@link ActivityStatus#BEGIN}或{@link ActivityStatus#SETTLE}阶段
     * 
     * 
     * @return
     */
    boolean isInCycle();

    /**
     * 判断并使用活动配置<br>
     * 若使用了新配置 触发状态刷新
     * 
     * @param activityConfig
     * @param now
     */
    void checkAndUseConfig(ActivityConfig activityConfig, long now);

    /**
     * 判断并刷新活动状态<br>
     * 
     * @param now
     */
    void checkAndRefreshStatus(long now);

    /**
     * 刷新配置<br>
     * 检查配置是否发生了变化 修正活动时间<br>
     * 只在启动服务器 和活动相关配置发生了变化时检查<br>
     */
    void refreshConfig();

    /**
     * 准备开始活动<br>
     * 活动开始前 进入准备阶段
     * 
     * @param now
     */
    void prepare(long now);

    /**
     * 开始活动
     * 
     * @param now
     */
    void begin(long now);

    /**
     * 结算活动
     * 
     * @param now
     */
    void settle(long now);

    /**
     * 彻底结束活动
     * 
     * @param now
     */
    void end(long now);

    /**
     * 强行关闭当前活动 并且在原活动结束时间前 不再尝试开启<br>
     * 只限于后台控制强行关闭活动<br>
     * 会触发活动结算和结束
     * 
     * @param now
     */
    void forceEnd(long now);

    // -------------------配置相关------------------------
    // -------------活动开始时 记录相关时间 不再因为配置变化或配置被删除而关闭活动------------------------
    /**
     * 获取当前活动配置<br>
     * 若活动未开启 返回null
     * 
     * @return
     */
   // Optional<ActivityConfig> getConfig();

    /**
     * 获取当前活动配置id
     * 
     * @return
     */
    int getConfigId();

    /**
     * 获取当前活动配置类型
     * 
     * @return
     */
    int getConfigType();

    /**
     * 当前活动开始时间
     * 
     * @return
     */
    long getBeginTime();

    /**
     * 当前活动结算时间
     * 
     * @return
     */
    long getSettleTime();

    /**
     * 当前活动结束时间
     * 
     * @return
     */
    long getEndTime();

    // ------------------持久化相关-----------------------
    /**
     * 活动数据
     * 
     * @return
     */
    ActivityData getActivityData();

    /**
     * 异步保存数据<br>
     * 若数据未发生变化 不保存
     */
    default void saveData() {
        saveData(false, false);
    }

    /**
     * 保存数据
     * 
     * @param block 是否阻塞当前线程
     * @param force 是否强行保存 若为false 且数据无变化 则不触发保存
     */
    void saveData(boolean block, boolean force);

    // ---------暂停---------------------
    /**
     * 是否暂停中
     * 
     * @return
     */
    boolean isPause();

    /**
     * 暂停活动<br>
     * 活动状态不再发生变化 不再触发tick<br>
     * 视该活动为未开启/已彻底关闭的状态
     */
    void pause();

    /**
     * 取消暂停
     */
    void unpause();

    /**
     * 活动背包缓存
     * 
     * @return
     */
    ActivityBagCache getBagCache();

    /**
     * 获取玩家的该活动的背包
     * 
     * @param playerId
     * @param createIfAbsent
     * @return
     */
    PlayerActivityBag getActivityBag(long playerId, boolean createIfAbsent);

    void broadcastActivityStatus();
}
