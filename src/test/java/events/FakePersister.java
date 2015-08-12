package events;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import events.persist.EventPersister;

public class FakePersister implements EventPersister {		
	private Fongo fongo;
	FakePersister(Fongo fakeMongo){
		this.fongo = fakeMongo; 
	}
	public void persist(String jsonEventString, String dbName,
			String dbCollection) {
		DB db = fongo.getDB(dbName);
		DBCollection collection = db.getCollection(dbCollection);
		DBObject dbObject = (DBObject)JSON.parse(jsonEventString);			
		collection.insert(dbObject);			
	}
}