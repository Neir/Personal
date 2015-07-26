package ca.umontreal.ift2905.db;

import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SearchHistoryDB extends SQLiteOpenHelper {

		static final int VERSION=1;

		static final String TABLE = "history";

		static final String C_ID = "_id";
		static final String C_API_ID = "api_id";
		static final String C_NAME = "name";

		Context context;	

		public SearchHistoryDB(Context context) {
			super(context, "history.db", null, VERSION);
			this.context=context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d("History DB", "Create DB");
			String sql="create table "+TABLE+" ("
					+C_ID+" integer primary key, "
					+C_API_ID+ " text,"
					+C_NAME+ " text)";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists "+TABLE);
			onCreate(db);
		}

		public void AddNewEntry(String apiId, String name) {
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues val = new ContentValues();
			val.clear();
			val.put(C_ID,System.currentTimeMillis());
			val.put(C_API_ID, apiId);
			val.put(C_NAME, name);

			try {
				db.insertOrThrow(TABLE, null,val);
			} catch ( SQLException e ) {
				Log.d("History DB","DB Error: "+e.getMessage());
			}
			db.close();		
		}

		
		public Map<String, String> getHistory () {
	        Map<String, String> history = new TreeMap<String,String>();
	        SQLiteDatabase db=this.getReadableDatabase();
	        final String[] select = new String[] {C_API_ID, C_NAME};
			Cursor c=db.query(TABLE,select,null, null, null, null, null);
	        while (c.moveToNext()) {
	        	history.put(c.getString(0),c.getString(1));
	        }
	        db.close();
	        return history;
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
