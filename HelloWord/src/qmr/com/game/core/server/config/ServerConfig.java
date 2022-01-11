package qmr.com.game.core.server.config;

public abstract class ServerConfig {

	private String name;
    private int id;
    private String web;

    public ServerConfig() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeb() {
        return this.web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
