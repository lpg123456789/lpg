package gk.server.shine.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.server.shine.persistence.Dao;
import gk.server.shine.persistence.PersistenceData;
import gk.server.shine.persistence.manager.ConnectionFactory;


public abstract class AbstractDao<T extends PersistenceData> implements Dao<T> {

    protected final Logger logger = LoggerFactory.getLogger("DBLOGCONSUMING");
    /**
     * 操作过长时间 毫秒<br>
     * 若操作时间过长 则打印日志
     */
    protected final static long WARN_TIME = 50;

    protected SqlSession openSession() {
        return ConnectionFactory.getInstance().openSession();
    }

    protected SqlSession openBatchSession() {
        return ConnectionFactory.getInstance().openBatchSession();
    }

    protected void logCostTime(String statement, long startTime) {
        long now = System.currentTimeMillis();
        long costTime = now - startTime;
        if (costTime >= WARN_TIME) {
            logger.warn("{} costTime:{}ms", statement, costTime);
        }
    }

}
