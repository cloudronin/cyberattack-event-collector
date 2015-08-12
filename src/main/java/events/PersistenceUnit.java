package events;

import java.net.UnknownHostException;

import events.persist.EventPersister;
import events.persist.EventPersisterImpl;
import events.util.Constants;

public class PersistenceUnit {
	
	private volatile static EventPersister _instance = null;
	
	public static EventPersister getInstance() throws UnknownHostException{
		if (_instance == null) { 
			synchronized (EventPersisterImpl.class) { 
				if (_instance == null) {
					_instance = new EventPersisterImpl(Constants.DB_HOST, 
							Constants.DB_PORT); 
				} 
			} 
		} 
		return _instance;
	}

	static void setInstance(EventPersister mockPersister){
		_instance = mockPersister;
	}
	
}
