package events.collect;

import javax.websocket.CloseReason;
import javax.websocket.Session;

public interface SocketClientEndpoint {

	public abstract void initialize();

	/**
	 * Callback hook for Connection open events.
	 *
	 * @param userSession the userSession which is opened.
	 */
	public abstract void onOpen(Session userSession);

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession the userSession which is getting closed.
	 * @param reason the reason for connection close
	 */
	public abstract void onClose(Session userSession, CloseReason reason);

	/**
	 * Callback hook for Message Events. This method will be invoked when a client send a message.
	 *
	 * @param message The text message
	 */
	
	public abstract void onMessage(String message);

	/**
	 * register message handler
	 *
	 * @param message
	 */
	public abstract void addMessageHandler(MessageHandler messageHandler);

	/**
	 * Send a message.
	 *
	 * @param user
	 * @param message
	 */
	public abstract void sendMessage(String message);

	public abstract Session getUserSession();

}