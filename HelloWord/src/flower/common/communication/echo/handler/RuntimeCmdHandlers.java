package common.communication.echo.handler;

import java.util.function.Function;

import common.communication.echo.ConsoleCmdHandler;
import common.utils.RuntimeUtil;

/**
 *
 * Runtime 命令处理器
 *
 * @author redback
 * @version 1.00
 * @time 2020-4-27 14:57
 */
public enum RuntimeCmdHandlers implements ConsoleCmdHandler {

    /**
     * 触发 FullGc
     */
    GC("gc", (cmd) -> RuntimeUtil.gc()),

    /**
     * 退出服务器
     */
    EXIT("exit", (cmd) -> RuntimeUtil.exit());

    RuntimeCmdHandlers(String type, Function<String, String> handler) {
        this.type = type;
        this.handler = handler;
    }

    public Function<String, String> getHandler() {
        return handler;
    }

    public String getType() {
        return type;
    }

    private String type;

    private Function<String, String> handler;

    @Override
    public String getCmdType() {
        return this.getType();
    }

    @Override
    public String handler(String cmd) {
        return this.handler.apply(cmd);
    }
}
