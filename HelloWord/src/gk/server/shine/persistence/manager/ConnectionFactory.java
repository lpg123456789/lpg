package gk.server.shine.persistence.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;


import gk.server.shine.Globals;
import gk.server.shine.persistence.scan.XMLConfigBuilderHelper;

public class ConnectionFactory {

	private static ConnectionFactory instance = new ConnectionFactory();

	public static ConnectionFactory getInstance() {
		return instance;
	}

	private final static Logger logger = Logger.getLogger(ConnectionFactory.class);

	// sql工厂
	private SqlSessionFactory sqlMapper;

	public void init() throws Exception {
		try (InputStream in = new FileInputStream(Globals.GAME_DB_CONFIG)) {
			String environment= "development";
			sqlMapper = new SqlSessionFactoryBuilder().build(in, environment, Globals.properties);
			//TODO 改成读配置
			XMLConfigBuilderHelper.getInstance().scanPackage("gk.server.shine.persistence.sqlmap", sqlMapper.getConfiguration());
		} catch (IOException e) {
			logger.error("init ConnectionFactory error.", e);
			throw e;
		}
	}

	public SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}

	public void setSqlMapper(SqlSessionFactory sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public SqlSession openSession() {
		return sqlMapper.openSession();
	}

	public SqlSession openBatchSession() {
		return sqlMapper.openSession(ExecutorType.BATCH);
	}
}
