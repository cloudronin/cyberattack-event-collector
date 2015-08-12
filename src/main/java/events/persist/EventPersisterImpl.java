package events.persist;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * EventPersisterImpl : 
 * Object that creates a MongoClient object
 * and allows the client to call the persist method
 * that will insert JSON based documents into the DB
 * 
 */
public class EventPersisterImpl implements EventPersister {
	
	private static final Logger logger = Logger.getLogger( EventPersisterImpl.class.getName() );

  
	public String dbHost;
	public Integer dbPort;
	private MongoClient mongo;
	
  public EventPersisterImpl(String serverAddress, 
			Integer serverPort) throws UnknownHostException {
		super();
		this.dbHost = serverAddress;
		this.dbPort = serverPort;
		mongo = new MongoClient(this.dbHost, this.dbPort);
	}

  /* (non-Javadoc)
 * @see events.EventPersister#persist(java.lang.String)
 */
  public void persist(String jsonEventString, String dbName, String dbCollectionName) {
    try {
		DB db = mongo.getDB(dbName);	
		DBCollection collection = db.getCollection(dbCollectionName);
		DBObject dbObject = (DBObject)JSON.parse(jsonEventString);			
		collection.insert(dbObject);	
   } catch (Exception e) {
		logger.log(Level.SEVERE, "Error persisting event: ", e);
    }
  }
  
}