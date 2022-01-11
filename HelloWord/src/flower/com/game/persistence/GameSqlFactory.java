package flower.com.game.persistence;

//游戏库
public class GameSqlFactory {

	private final static GameSqlFactory instance = new GameSqlFactory();

    public static final GameSqlFactory getInstance() {
        return instance;
    }
    
    public void init() {
    	
    }
	
}
