package ca.umontreal.ift2905.db;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavoriteBeerDB extends SQLiteOpenHelper {

		static final int VERSION=1;

		static final String TABLE = "favorite_beer";

		static final String C_ID = "_id";
		static final String C_API_ID = "api_id";
		static final String C_NAME = "name";
		static final String C_ICON_URL = "icon_url";

		Context context;	

		public FavoriteBeerDB(Context context) {
			super(context, "favorite_beer.db", null, VERSION);
			this.context=context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d("Favorite DB", "Create DB");
			String sql="create table "+TABLE+" ("
					+C_ID+" integer primary key, "
					+C_API_ID+ " text,"
					+C_NAME+ " text,"
					+C_ICON_URL+ " text)";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists "+TABLE);
			onCreate(db);
		}

		public void AddNewEntry(String apiId, String name, String iconUrl) {
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues val = new ContentValues();
			val.clear();
			val.put(C_ID,System.currentTimeMillis());
			val.put(C_API_ID, apiId);
			val.put(C_NAME, name);
			val.put(C_ICON_URL, iconUrl);

			try {
				db.insertOrThrow(TABLE, null,val);
			} catch ( SQLException e ) {
				Log.d("Favorite DB","DB Error: "+e.getMessage());
			}
			db.close();		
		}

		
		public Map<String, ArrayList<String>> getFavorites () {
	        Map<String, ArrayList<String>> favorites = new TreeMap<String, ArrayList<String>>();
	        SQLiteDatabase db=this.getReadableDatabase();
	        final String[] select = new String[] {C_API_ID, C_NAME, C_ICON_URL};
			Cursor c=db.query(TABLE,select,null, null, null, null, null);
	        while (c.moveToNext()) {
	        	ArrayList<String> favorite = new ArrayList<String>();
	        	favorite.add(c.getString(1));
	        	favorite.add(c.getString(2));
	            favorites.put(c.getString(0),favorite);
	        }
	        db.close();
	        return favorites;
	    }
		
		public int querySize() {
			final String[] select = new String[] {C_ID};
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor c=db.query(TABLE,select,null, null, null, null, null);
			int size=c.getCount();
			db.close();
			return size;
		}
	
}
