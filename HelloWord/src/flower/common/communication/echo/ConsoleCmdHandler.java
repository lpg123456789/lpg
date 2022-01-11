package flower.common.communication.echo;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ConsoleCmdHandler {

	
	/**
     * 获取平台消息处理类型
     * 
     * @return cmdType
     */
    String getCmdType();

    /**
     * 处理平台命令
     * 
     * @param cmd 平台指令内容
     * @return 执行返回结果
     */
    String handler(String cmd) throws JsonProcessingException;
}
