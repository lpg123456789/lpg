package gk.server.shine.persistence.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gk.server.shine.persistence.Dao;
import gk.server.shine.persistence.PersistenceData;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.impl.CommonDao;

public class DaoFactory {

	private final static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    public DaoFactory() {
        registerAll();
    }
    
    private final Map<Class<?>, Dao<?>> daoMap = new HashMap<>();
    
    private void registerAll() {
    	registerDao(new CommonDao<>("game_groupdata"),GroupData.class);
    }
    
    private void registerDao(Dao<?> dao,Class<?> dataClazz) {
        daoMap.put(dataClazz, dao);
    }
    
    @SuppressWarnings("unchecked")
    public <D extends Dao<T>, T extends PersistenceData> D getDao(Class<T> dataClazz) {
        Dao<?> dao = daoMap.get(dataClazz);
        if (dao == null) {
            return null;
        }
        return (D) dao;
    }

    /**
     * 根据主键查找数据
     * 
     * @param <T>
     * @param dataClazz
     * @param key
     * @return
     */
    public <T extends PersistenceData> T select(Class<T> dataClazz, Object key) {
        if (key == null) {
            throw new NullPointerException("load data[" + dataClazz.getSimpleName() + "] fail.key is null.");
        }
        Dao<T> dao = getDao(dataClazz);
        if (dao == null) {
            return null;
        }
        T data = dao.select(key);
        return data;
    }

    /**
     * 查找该类型的所有数据
     * 
     * @param <T>
     * @param dataClazz
     * @return
     */
    public <T extends PersistenceData> List<T> selectAll(Class<T> dataClazz) {
        Dao<T> dao = getDao(dataClazz);
        if (dao == null) {
            return null;
        }
        List<T> dataList = dao.selectAll();
        return dataList;
    }
}
