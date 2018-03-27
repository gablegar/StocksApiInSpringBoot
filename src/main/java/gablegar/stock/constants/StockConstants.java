package gablegar.stock.constants;

/**
 * Created by glegarda on 13/03/18.
 * Contains all the common constants for the project
 */
public class StockConstants {

	public static final String SUCCESSFUL = "successful";
	public static final String ERROR = "error";
	public static final String NOT_FOUND = "not found";
	public static final String INITIAL_MSG = "Stock with id : ";
	public static final String UPDATED = "updated";
	public static final String CREATED = "created";
	public static final String NOT_CREATED = "not created";
	public static final String NOT_AVAILABLE_STOCKS = "No stocks available";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_STORE = "ROLE_STORE";
	public static final String ROLE_GENERAL_USER = "ROLE_USER";
	public static final String STORE_AUTHORITY = "STORE";
	public static final String API_PATH ="/api/**";

	private StockConstants(){
		throw new AssertionError();
	}
}
