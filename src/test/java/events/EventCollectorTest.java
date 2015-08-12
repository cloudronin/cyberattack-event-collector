package events;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.DBObject;

import events.collect.EventCollectorThread;
import events.collect.MessageHandler;
import events.collect.SocketClientEndpoint;
import events.persist.EventPersister;
import events.util.Constants;

public class EventCollectorTest {
	
	SocketClientEndpoint endpoint;
	Fongo fongo;

	@Before
	public void setUp() throws Exception {
		fongo = new Fongo("mongo server 1");
		PersistenceUnit.setInstance(new FakePersister(fongo));
		final EventPersister persister = PersistenceUnit.getInstance();
		endpoint = new FakeClientEndpoint(new URI(Constants.NORSE_WEBSOCKET_URL));
		MessageHandler basicHandler = new MessageHandler(){
			public void handleMessage(String message) {
				persister.persist(message, Constants.DB_NAME, Constants.NORSE_DB_COLLECTION_NAME);
			}	
		};
		endpoint.addMessageHandler(basicHandler);
	}

	@Test
	public void testEndToEnd() {
    	EventCollectorThread norseCollector = new 
    			EventCollectorThread(endpoint, Boolean.FALSE);
    	norseCollector.run();
    	DBObject object = fongo.getDB(Constants.DB_NAME).
    		getCollection(Constants.NORSE_DB_COLLECTION_NAME).findOne();
    	assertTrue(object.containsField(TestData.TEST_FIELD_NAME));
    	assertEquals(TestData.TEST_FIELD_VALUE, object.get(TestData.TEST_FIELD_NAME));
	}
	
}
