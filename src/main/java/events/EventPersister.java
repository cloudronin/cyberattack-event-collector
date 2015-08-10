package events;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * EventPersister : Singleton object
 * that creates a MongoClient object
 * and allows the client to call the persist method
 * that will insert JSON based documents into the DB
 * 
 */
public class EventPersister {
  
	public String dbName;
	public String dbHost;
	public Integer dbPort;
	public String dbCollectionName;
	private MongoClient mongo;
	private volatile static EventPersister _instance = null;
	
	public static EventPersister getInstance() throws UnknownHostException {
		if (_instance == null) { 
			synchronized (EventPersister.class) { 
				if (_instance == null) {
					_instance = new EventPersister(Constants.DB_HOST, 
							Constants.DB_NAME, Constants.DB_PORT, Constants.DB_COLLECTION_NAME); 
				} 
			} 
		} 
		return _instance;
	}
	
	
  private EventPersister(String serverAddress, String databaseName, 
			Integer serverPort, String collectionName) throws UnknownHostException {
		super();
		this.dbName = databaseName;
		this.dbHost = serverAddress;
		this.dbPort = serverPort;
		this.dbCollectionName = collectionName;
		mongo = new MongoClient(this.dbHost, this.dbPort);
	}

  public void persist(String jsonEventString) {
    try {
		DB db = mongo.getDB(this.dbName);	
		DBCollection collection = db.getCollection(this.dbCollectionName);
		DBObject dbObject = (DBObject)JSON.parse(jsonEventString);			
		collection.insert(dbObject);
		
   } catch (Exception e) {
   		System.err.println("Error persisting event: " + e.getMessage());
    }
  }
  
}