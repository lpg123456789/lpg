package flower.common.communication.echo.manager;

import java.util.Map;

import flower.common.component.service.AbstractService;

public class BackstageManager extends AbstractService{

	private static final BackstageManager instance = new BackstageManager();

	private BackstageManager() {
	}

	public static BackstageManager getInstance() {
		return instance;
	}
	
	  /**
     * 协议 Id -> 后台消息处理器
     */
    private Map<Integer, BackstageMsgHandler<?>> id2Handlers = new HashMap<>();

    @Override
    protected void onInitialize() throws Exception {
        scanBackstageMsgHandlers();
    }

}
