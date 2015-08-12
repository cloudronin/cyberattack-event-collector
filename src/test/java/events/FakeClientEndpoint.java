package events;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import events.collect.MessageHandler;
import events.collect.SocketClientEndpoint;

/**
 * WebSocket Client
 *
 * @author Vishnu Vettrivel
 */
@ClientEndpoint
public class FakeClientEndpoint implements SocketClientEndpoint{

	private Session userSession = null;
    private MessageHandler messageHandler;
    private Timer timer;
    
	
    
    public FakeClientEndpoint(URI endpointURI) {
        initialize();
    }
    
	public void initialize() {
		try {	
			onOpen(new FakeSession());
			timer = new Timer("SocketTimer", true);
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					onMessage(TestData.TEST_MESSAGE);
				}				
			}, 1, 10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    /**
     * register message handler
     *
     * @param message
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param user
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public Session getUserSession() {
		return userSession;
	}
    

}