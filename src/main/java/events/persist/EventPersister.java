package events.persist;

public interface EventPersister {

	public void persist(String jsonEventString, String dbName, String dbCollection);

}