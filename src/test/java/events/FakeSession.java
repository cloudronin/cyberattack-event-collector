package events;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class FakeSession implements Session {

	public WebSocketContainer getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addMessageHandler(MessageHandler handler)
			throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	public Set<MessageHandler> getMessageHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeMessageHandler(MessageHandler handler) {
		// TODO Auto-generated method stub

	}

	public String getProtocolVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNegotiatedSubprotocol() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Extension> getNegotiatedExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getMaxIdleTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMaxIdleTimeout(long milliseconds) {
		// TODO Auto-generated method stub

	}

	public void setMaxBinaryMessageBufferSize(int length) {
		// TODO Auto-generated method stub

	}

	public int getMaxBinaryMessageBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMaxTextMessageBufferSize(int length) {
		// TODO Auto-generated method stub

	}

	public int getMaxTextMessageBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Async getAsyncRemote() {
		// TODO Auto-generated method stub
		return null;
	}

	public Basic getBasicRemote() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void close(CloseReason closeReason) throws IOException {
		// TODO Auto-generated method stub

	}

	public URI getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, List<String>> getRequestParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getPathParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getUserProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Session> getOpenSessions() {
		// TODO Auto-generated method stub
		return null;
	}

}
