package events.collect;

import java.util.logging.Level;
import java.util.logging.Logger;

import events.util.Constants;

public class EventCollectorThread implements Runnable{
	
	private static final Logger logger = Logger.getLogger( EventCollectorThread.class.getName() );
	
	private SocketClientEndpoint clientEndPoint;
	private boolean reconnect;	

	public EventCollectorThread(SocketClientEndpoint clientEndpoint) {
		this(clientEndpoint, Boolean.TRUE);
	}
	
	public EventCollectorThread(SocketClientEndpoint clientEndpoint, boolean reconnect) {
		this.clientEndPoint = clientEndpoint;
		this.reconnect = reconnect;
	}


	public void run() {
		    
		// Ensure that the client web-socket end-point is re-opened if needs to be             
		do {
			try{
				Thread.sleep(Constants.SLEEP_TIME_MILLISECONDS);
			} catch (InterruptedException ex) {
				logger.log(Level.FINEST, "Waking up to check for closed connection.");
			} 
			if(clientEndPoint.getUserSession() == null)
				clientEndPoint.initialize();
		} while(reconnect);   
		
	}
	
	

}
