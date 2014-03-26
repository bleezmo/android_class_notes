package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages sports database. uses the db helper class as specified in android docs
 * http://developer.android.com/guide/topics/data/data-storage.html#db
 * @author josh
 *
 */
public class SportsManager extends SQLiteOpenHelper{
	//table name
	public static final String TABLE_NAME = "sports";
	//columns
	public static final String ID = "_id";
	public static final String DATE = "date";
	public static final String ARENA = "arena";
	public static final String CITY = "city";
	public static final String STATE = "state";
	
	//sql statement to create the sports table
	private static final String CREATE_TABLE = "CREATE TABLE "+
			TABLE_NAME+" ("+
			ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
			DATE+" TEXT, "+
			ARENA+" TEXT, "+
			CITY+" TEXT, "+
			STATE+" TEXT)";
	
	/**
	 * pass in the name of the database and version 1
	 * @param context
	 */
	public SportsManager(Context context) {
		super(context, "sports_db", null, 1);
	}
	/**
	 * This is called if getWritable/ReadableDatabase is called and there
	 * is no database already
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//DO NOTHING
	}
	
	/**
	 * insert the sports json stuff into the database
	 * 1. First get the 'games' json array
	 * 2. get the writable database from this object
	 * 3. loop through each game in the json array and insert each game
	 *    as a record
	 * @param jsSports
	 * @throws JSONException
	 */
	public void insertSports(JSONObject jsSports) throws JSONException{
		JSONArray games = jsSports.getJSONObject("sports_content").
		getJSONObject("games").getJSONArray("game");
		
		SQLiteDatabase db = this.getWritableDatabase();
		for(int i = 0; i < games.length(); i++){
			ContentValues cv = new ContentValues();
			JSONObject game = games.getJSONObject(i);
			cv.put(DATE, game.getString(DATE));
			cv.put(ARENA, game.getString(ARENA));
			cv.put(CITY, game.getString(CITY));
			cv.put(STATE, game.getString(STATE));
			db.insert(TABLE_NAME, null, cv);
		}
		db.close();
	}

}
