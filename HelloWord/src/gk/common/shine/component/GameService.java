package gk.common.shine.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameService implements Component{
	
	protected final Logger logger = buildLogger();
	  
	protected Logger buildLogger() {
        return LoggerFactory.getLogger(getClass());
    }

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ready() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
