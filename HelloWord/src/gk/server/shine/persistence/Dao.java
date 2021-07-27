package gk.server.shine.persistence;

import java.util.Collection;
import java.util.List;

public interface Dao<T extends PersistenceData> {

	/**
	 * 根据主键查找数据
	 * 
	 * @param key
	 * @return
	 */
	T select(Object key);

	/**
	 * 查找所有数据
	 * 
	 * @return
	 */
	List<T> selectAll();

	/**
	 * 插入/更新数据<br>
	 * key需要自己生成<br>
	 * replace/on duplicate key update操作
	 * 
	 * @param data
	 * @return
	 */
	boolean save(T data);

	/**
	 * 批量插入/更新数据<br>
	 * key需要自己生成<br>
	 * replace/on duplicate key update操作
	 * 
	 * @param dataList
	 */
	void batchSave(Collection<T> dataList);

	/**
	 * 删除数据
	 * 
	 * @param key
	 * @return
	 */
	boolean delete(Object key);
}
