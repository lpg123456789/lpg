package flower.com.game.persistence;

//自定义
public class LogSqlFactory {

	private final static LogSqlFactory instance = new LogSqlFactory();

    public static final LogSqlFactory getInstance() {
        return instance;
    }

    public void init() {
    	
    }
}
