package gk.common.shine.server.structs;

/**
 * session属性类型
 * 
 * @author zhangzhen
 * @date 2017年8月29日
 * @version 1.0
 */
public enum SessionAttType {

    /**
     * 是否tcpsocket，用于内网服务器之间的通信
     */
    SOCKET_TCP,
    /**
     * websocket是否已经握手
     */
    SOCKET_WEB_HANDSHAKE,
    /**
     * 要发送的buffer
     */
    SESSION_MSG_BUFFER,
    /**
     * 消息验证code(发送+1，主要用于服务器发送给网关消息 次数记录+1)
     */
    SESSION_MSG_GATE_CODE,
    /**
     * 消息验证code(接收+1，主要用于网关接收客户端和服务器消息 次数记录+1)
     */
    SESSION_MSG_CLIENT_CODE,
    /**
     * 接受消息时间
     */
    SESSION_MSG_TIME,
    /**
     * 每秒接收消息次数
     */
    SESSION_MSG_NUM,
    /**
     * 消息内容
     */
    SESSION_MSG_CONTEXT,
    /**
     * 连接网关发送注册消息
     */
    SESSION_REGISTER,
    /**
     * 是否gmSession
     */
    SESSION_LOGIN_GM,
    /**
     * 是否公共服session
     */
    SESSION_LOGIN_PUBLIC,
    /**
     * 上一次的心跳时间
     */
    SESSION_LAST_HEART,
    /**
     * 角色id
     */
    SESSION_PLAYER_ID,
    /**
     * 登陆后是否发过心跳消息
     */
    SESSION_INIT_HEART,
    /**
     * 用户名
     */
    SESSION_USER_NAME,
    /**
     * 服务器Id
     */
    SESSION_SERVER_ID,
    /**
     * 登陆ip
     * 
     * @see SessionUtil.getClientIp
     */
    @Deprecated
    SESSION_SESSION_IP,
    /**
     * 渠道
     */
    SESSION_CHANNEL,
    /**
     * 首次心跳时间
     */
    SESSION_FIRST_HEART,
    /**
     * 累计心跳次数
     */
    SESSION_HEART_TIMES,
    /**
     * session关闭信息
     */
    SESSION_CLOSE_INFO,
    /**
     * session创建时间
     */
    SESSION_CREATE_TIME,
    /**
     * 是否是网关session
     */
    SESSION_GATE,
    /**
     * 网关和服务器之间的心跳
     */
    SESSION_G2S_HEART_TIME,
    /**
     * 是否是console session
     */
    SESSION_CONSOLE,
    /**
     * 客户端真实ip
     */
    SESSION_REL_IP_ADDRESS,
    /**
     * 玩家登陆使用的网关id
     */
    PLAYER_LOGIN_GATE_ID,

    /**
     * 秘钥<br>
     * 
     */
    PLAYER_KEY,
    /**
     * 秘钥绑定的玩家id<br>
     * 若秘钥不是通过网关获得 而是从账号缓存中获取<br>
     * 只能指定某个玩家id进行登录
     */
    PLAYER_SERCET_KEY_PLAYER_ID,
    /**
     * 连接验证
     */
    STEP,
    /**
     * 请求协议id
     */
    REQ_MSG_ID,

    // ---------------inner----------
    /**
     * 服务器之间连接 服务端记录的客户端session的心跳时间
     */
    INNER_HEARTBEAT_TIME,
    /**
     * 服务器之间连接 客户端的服务器类型<br>
     * {@link ServerType}
     */
    INNER_SERVER_TYPE,

    /**
     * 玩家是否离线中<br>
     * 用于标记该连接是否先执行了logout再执行closeSession
     */
    PLAYER_LOGOUTING,;
}
