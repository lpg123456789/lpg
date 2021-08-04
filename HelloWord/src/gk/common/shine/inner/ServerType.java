package gk.common.shine.inner;

/**
 * 服务器类型
 * 
 * @author hdh
 *
 */
public enum ServerType {
    /**
     * 未知服务器
     */
    UNKNOWN(0),
    /**
     * 游戏 逻辑服
     */
    GAME(1),
    /**
     * 跨服
     */
    CROSS(2),
    /**
     * 网关
     */
    GATE(3),
    /**
     * 网关缓存
     */
    GATECACHE(4),
    /**
     * 微信公众号网站
     */
    WEB(5),
    /**
     * 照相机
     */
    CAMERA(6),

    /**
     * 聊天监控
     */
    CHATMONITOR(10),

    ;

    private final int type;

    private ServerType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static ServerType getServerType(int type) {
        ServerType[] values = ServerType.values();
        for (ServerType serverType : values) {
            if (serverType.type == type) {
                return serverType;
            }
        }
        return null;
    }

    public static String getServerTypeName(int type) {
        ServerType[] values = ServerType.values();
        for (ServerType serverType : values) {
            if (serverType.type == type) {
                return serverType.name();
            }
        }
        return UNKNOWN.name();
    }

}
