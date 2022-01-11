package flower.com.game.module.activity.structs;


/**
 * 活动数据
 * 
 * @author hdh
 *
 */
@TableName("activity")
public class ActivityData implements PersistenceData, Changeable {
    /**
     * 活动类型
     */
    private int id;
    /**
     * 活动状态
     */
    private int status;
    /**
     * 活动阶段
     */
    private int stage;
    /**
     * 是否暂停
     */
    private boolean pause;
    // ----------活动开始后 记录活动时间-----------
    // 后台要修改活动时间时 额外发送通知
    // 不再支持直接改配置来修改方案,活动时间
    /**
     * 配置id
     */
    private int configId;
    /**
     * 配置类型
     */
    private int configType;
    /**
     * 活动方案
     */
    private int planId;
    /**
     * 活动开始时间
     */
    private long beginTime;
    /**
     * 活动结算时间
     */
    private long settleTime;
    /**
     * 活动结束时间
     */
    private long endTime;
    /**
     * 自定义活动数据<br>
     */
    private byte[] customData;
    /**
     * 活动背包数据
     */
    private byte[] bagData;
    /**
     * 开启的次数<br>
     * 每次活动begin时+1<br>
     * 首次开始活动前为0 开始后到下次活动开始前为1<br>
     * 感觉会用到 先存着
     */
    private int openedNum;

    private transient boolean change;

    @Override
    public Object getPrimaryKey() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(long settleTime) {
        this.settleTime = settleTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public byte[] getCustomData() {
        return customData;
    }

    public void setCustomData(byte[] customData) {
        this.customData = customData;
    }

    public int getOpenedNum() {
        return openedNum;
    }

    public void setOpenedNum(int openedNum) {
        this.openedNum = openedNum;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    @Override
    public boolean isChange() {
        return change;
    }

    @Override
    public void setChange(boolean change) {
        this.change = change;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public byte[] getBagData() {
        return bagData;
    }

    public void setBagData(byte[] bagData) {
        this.bagData = bagData;
    }

}
