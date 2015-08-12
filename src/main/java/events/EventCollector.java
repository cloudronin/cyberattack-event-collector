package events;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import events.collect.EventCollectorThread;
import events.collect.MessageHandler;
import events.collect.SocketClientEndpoint;
import events.collect.WebsocketClientEndpoint;
import events.persist.EventPersister;
import events.util.Constants;

/*
 * EventCollector Class that listens to websocket for attack events 
 * and perists them using the EventPersister Object
 */
public class EventCollector {
	
	private static final Logger logger = Logger.getLogger( EventCollector.class.getName() );

    public static void main(String[] args) {
        
		try {
			
			final EventPersister persister = PersistenceUnit.getInstance();	
			MessageHandler defaultHandler = new MessageHandler() {
			    public void handleMessage(String message) {
			        logger.log(Level.INFO, "Received:" + message);
			        persister.persist(message, Constants.DB_NAME, Constants.NORSE_DB_COLLECTION_NAME);	        
			    }
			};
	    	SocketClientEndpoint endpoint = new WebsocketClientEndpoint(
	    			new URI(Constants.NORSE_WEBSOCKET_URL));
	    	endpoint.addMessageHandler(defaultHandler);
	    	EventCollectorThread norseCollector = new EventCollectorThread(endpoint);
	    	norseCollector.run();
    	
		} catch (UnknownHostException ex) {
			logger.log(Level.SEVERE, "EventCollector failed with UnknownHostException: ", ex);
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, "EventCollector failed with URISyntaxException: ", e);
		}
    }
}
