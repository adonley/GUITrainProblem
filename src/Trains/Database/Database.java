package Trains.Database;

/**
 * A singleton database to hold the graphical information about trains.
 * @author Andrew Donley
 */
public class Database {
	
	/**
	 * The singleton database object that is eagerly initialized.
	 */
	public static final Database db = new Database();
	
	/** 
	 * <code>private</code> constructor does nothing since there is only one database instance.
	 */
	private Database() { }
	
	/**
	 * Returns an instance of the Database class that was initiated previously.
	 * @return <code>db</code> the database instance that is associated with this object.
	 */
	public static Database getInstance() {
		return db;
	}

}
