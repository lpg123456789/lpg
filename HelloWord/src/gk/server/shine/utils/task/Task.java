package gk.server.shine.utils.task;

/**
 * 任务
 * 
 * @author hdh
 *
 */
@FunctionalInterface
public interface Task {
	/**
	 * 执行
	 * 
	 * @throws Exception
	 */
	void execute() throws Exception;

}
