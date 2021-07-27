package gk.server.shine.data.container;

import gk.server.shine.data.bean.A_Bean;
import gk.server.shine.data.structs.GameConfigContainer;

public class A_Container extends GameConfigContainer<A_Bean>{

	public A_Container() {
		super(A_Bean.class);
	}

	public void load() {
		load("json/A.json");
	}

}
