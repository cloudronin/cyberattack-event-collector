package events.collect;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * WebSocket Client
 *
 * @author Vishnu Vettrivel
 */
@ClientEndpoint
public class WebsocketClientEndpoint implements SocketClientEndpoint {
	
	private static final Logger logger = Logger.getLogger( WebsocketClientEndpoint.class.getName() );

	Session userSession = null;
    private MessageHandler messageHandler;
    URI clientEndPointURI;
    


    public WebsocketClientEndpoint(URI endpointURI) {
    	clientEndPointURI = endpointURI;
        initialize();
    }

	/* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#initialize()
	 */
	public void initialize() {
		try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, clientEndPointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#onOpen(javax.websocket.Session)
	 */
    @OnOpen
    public void onOpen(Session userSession) {
        logger.log(Level.INFO, "opening websocket:");
        this.userSession = userSession;
    }

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#onClose(javax.websocket.Session, javax.websocket.CloseReason)
	 */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.log(Level.INFO, "closing websocket, reason:"+reason.getReasonPhrase());
        this.userSession = null;
    }

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#onMessage(java.lang.String)
	 */
    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#addMessageHandler(events.collect.WebsocketClientEndpoint.MessageHandler)
	 */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#sendMessage(java.lang.String)
	 */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    /* (non-Javadoc)
	 * @see events.collect.SocketClientEndpoint#getUserSession()
	 */
    public Session getUserSession() {
		return userSession;
	}
}