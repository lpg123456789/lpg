package flower.com.game.context.register;

import flower.com.game.module.activity.Activity;
import flower.common.component.service.ContextRegister;

/**
 * 活动注册类
 * 
 * @author hdh
 *
 */
public class ActivityRegister implements ContextRegister {

	@Override
	public void registerAll() throws Exception {
		// TODO Auto-generated method stub

	}

	private void registerActivity(Activity activity) {
		ActivityManager.getInstance().registerActivity(activity);
	}

}
