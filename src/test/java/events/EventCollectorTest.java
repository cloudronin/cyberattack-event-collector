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
	

	static String TEST_FIELD_VALUE = "78.182.82.165";
	static String TEST_FIELD_NAME = "md5";
	static String TEST_MESSAGE = 
	"{\"latitude\":\"41.01\",\"longitude\":\"28.96\",\"countrycode\":\"TR\",\"country\":\"TR\"" +
	",\"city\":\"Istanbul\",\"org\":\"Tt Adsl-Ttnet_Dynamic_Gay\",\"latitude2\":\"47.14\",\"longitude2\":\"9.52\"" +
	",\"countrycode2\":\"LI\",\"country2\":\"LI\",\"city2\":\"Vaduz\",\"type\":\"ipviking.honey\"," +
	"\"" + TEST_FIELD_NAME + "\":\"" + TEST_FIELD_VALUE + "\",\"dport\":\"23\",\"svc\":\"telnet\",\"zerg\":\"\"}"; 
	
	EventPersister persister;
	SocketClientEndpoint endpoint;
	Fongo fongo;

	@Before
	public void setUp() throws Exception {
		fongo = new Fongo("mongo server 1");
		PersistenceUnit.setInstance(new FakePersister(fongo));
		persister = PersistenceUnit.getInstance();
		endpoint = new FakeClientEndpoint(new URI(Constants.NORSE_WEBSOCKET_URL));
		MessageHandler messageHandler = new MessageHandler(){
			public void handleMessage(String message) {
				persister.persist(message, Constants.DB_NAME, Constants.NORSE_DB_COLLECTION_NAME);
			}	
		};
		endpoint.addMessageHandler(messageHandler);
	}

	@Test
	public void testEndToEnd() {
    	EventCollectorThread norseCollector = new 
    			EventCollectorThread(endpoint, Boolean.FALSE);
    	norseCollector.run();
    	DBObject object = fongo.getDB(Constants.DB_NAME).
    		getCollection(Constants.NORSE_DB_COLLECTION_NAME).findOne();
    	assertTrue(object.containsField(TEST_FIELD_NAME));
    	assertEquals(TEST_FIELD_VALUE, object.get(TEST_FIELD_NAME));
	}
	
}
