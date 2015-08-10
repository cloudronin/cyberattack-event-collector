package events;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/*
 * EventCollector Class that listens to websocket for attack events 
 * and perists them using the EventPersister Object
 */
public class EventCollector {
	
	public static EventPersister persister;
	
    public static void main(String[] args) {
        try {
            // open websocket
            WebsocketClientEndpoint clientEndPoint = initializeClientEndPoint(Constants.WEBSOCKET_URL);
            try {
				persister = EventPersister.getInstance();
			} catch (UnknownHostException e) {
                System.err.println("UnknownHostException: " + e.getMessage());
			}

         
            // Ensure that the websocket is re-opened if needs to be             
            while(true){
            	try{
            		Thread.sleep(Constants.SLEEP_TIME_MILLISECONDS);
            	} catch (InterruptedException ex) {
                    System.err.println("InterruptedException: " + ex.getMessage());
            	} 
            	if(clientEndPoint.getUserSession() == null)
            		clientEndPoint = initializeClientEndPoint(Constants.WEBSOCKET_URL);
            }
            	
        }    
        catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }     
    }

    /*
     * Initialize Websocket and add Handler to Persist events recieved
     */
	private static WebsocketClientEndpoint initializeClientEndPoint(String webSocketURL)
			throws URISyntaxException {
		WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(webSocketURL));

		// add listener
		clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
		    public void handleMessage(String message) {
		        System.out.println("Recieved:" + message);
		        persister.persist(message);	        
		    }
		});
		return clientEndPoint;
	}
}
