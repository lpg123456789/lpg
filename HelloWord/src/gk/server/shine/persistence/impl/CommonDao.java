package gk.server.shine.persistence.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import gk.server.shine.persistence.PersistenceData;

public class CommonDao<T extends PersistenceData> extends AbstractDao<T> {
	/**
	 * 单次批量操作的数量
	 */
	protected final static int DEFAULT_BATCH_SIZE = 300;

	public int getDefaultBatchSize() {
		return DEFAULT_BATCH_SIZE;
	}
	
	/**
	 * mapper.xml文件的namespace
	 */
	protected final String name;

	public CommonDao(String name) {
		this.name = name;
	}
	
	@Override
	public T select(Object key) {
		if (key == null) {
			throw new NullPointerException(name + "load data error.key is null.");
		}
		SqlSession session = openSession();
		String statement = getSelectStatement();
		try {
			long startTime = System.currentTimeMillis();
			T data = session.selectOne(statement, key);
			logCostTime(statement, startTime);
			return data;
		} catch (Exception e) {
			logger.error(statement + " error.key=" + key, e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<T> selectAll() {
		SqlSession session = openSession();
		String statement = getSelectAllStatement();
		try {
			long startTime = System.currentTimeMillis();
			List<T> allDatas = session.selectList(statement);
			logCostTime(statement, startTime);
			return allDatas;
		} catch (Exception e) {
			logger.error(statement + " error.", e);
			throw e;
		} finally {
			session.close();
		}
	}

	public boolean insert(T data) {
		SqlSession session = openSession();
		String statement = getInsertStatement();
		try {
			long startTime = System.currentTimeMillis();
			int row = session.insert(statement, data);
			session.commit();
			logCostTime(statement, startTime);
			return row > 0;
		} catch (Exception e) {
			logger.error(statement + " error.key=" + data.getPrimaryKey(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public boolean update(T data) {
		SqlSession session = openSession();
		String statement = getUpdateStatement();
		try {
			long startTime = System.currentTimeMillis();
			int row = session.update(statement, data);
			session.commit();
			logCostTime(statement, startTime);
			return row > 0;
		} catch (Exception e) {
			logger.error(statement + " error.key=" + data.getPrimaryKey(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean save(T data) {
		SqlSession session = openSession();
		String statement = getSaveStatement();
		try {
			long startTime = System.currentTimeMillis();
			int row = session.insert(statement, data);
			session.commit();
			logCostTime(statement, startTime);
			return row > 0;
		} catch (Exception e) {
			logger.error(statement + " error.key=" + data.getPrimaryKey(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void batchSave(Collection<T> dataList) {
		String statement = getBatchSaveStatement();
		batchSave(dataList, statement);
	}

	public void batchSave(Collection<T> dataList, String statement) {
		if (dataList == null || dataList.isEmpty()) {
			return;
		}
		SqlSession session = openBatchSession();
		try {
			long startTime = System.currentTimeMillis();
			List<T> tmpList = new ArrayList<>(getDefaultBatchSize());
			for (T data : dataList) {
				tmpList.add(data);
				if (tmpList.size() >= getDefaultBatchSize()) {
					session.insert(statement, tmpList);
					session.commit();
					tmpList.clear();
				}
			}
			if (!tmpList.isEmpty()) {
				session.insert(statement, tmpList);
				session.commit();
			}
			session.commit();
			logCostTime(statement, startTime);
		} catch (Exception e) {
			logger.error(statement + " error.", e);
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean delete(Object key) {
		if (key == null) {
			throw new NullPointerException(name + "delete data error.key is null.");
		}
		SqlSession session = openSession();
		String statement = getDeleteStatement();
		try {
			long startTime = System.currentTimeMillis();
			int row = session.delete(statement, key);
			session.commit();
			logCostTime(statement, startTime);
			return row > 0;
		} catch (Exception e) {
			logger.error(statement + " error.key=" + key, e);
			throw e;
		} finally {
			session.close();
		}
	}
	
	public String getName() {
		return name;
	}

	protected String getSelectStatement() {
		return name + ".select";
	}

	protected String getSelectAllStatement() {
		return name + ".selectAll";
	}

	protected String getInsertStatement() {
		return name + ".insert";
	}

	protected String getUpdateStatement() {
		return name + ".update";
	}

	protected String getSaveStatement() {
		return name + ".save";
	}

	public String getBatchSaveStatement() {
		return name + ".batchSave";
	}

	protected String getDeleteStatement() {
		return name + ".delete";
	}
}
