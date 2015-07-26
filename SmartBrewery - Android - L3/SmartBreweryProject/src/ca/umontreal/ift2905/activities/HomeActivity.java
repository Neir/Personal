package ca.umontreal.ift2905.activities;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import ca.umontreal.ift2905.api.Search;
import ca.umontreal.ift2905.db.SearchHistoryDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity {

	ImageButton imageButton;
	ListView historyList;
	SearchHistoryDB shDB;
	private ArrayAdapter<String> mainAdapter;
	String[] names;
	String[] ids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		addListenerOnButton();
		
		shDB = new SearchHistoryDB(this);
		Map<String, String> history = new TreeMap<String, String>();
		history = shDB.getHistory();
		
		if(history.size() == 0){
			TextView text = (TextView)findViewById(R.id.history);
			text.setText(text.getText()+" Empty");
		}
		
		names = new String[history.size()];
		ids = new String[history.size()];
		int i = 0;
		
		for(Entry<String, String> entry : history.entrySet()) {
		    String id = entry.getKey();
		    String name = entry.getValue();
		    ids[i] = id;
		    names[i] = name;
		    i++;
		}
		
		List<String> list = Arrays.asList(names);
        Collections.reverse(list);
        names = (String[]) list.toArray();
        List<String> list1 = Arrays.asList(ids);
        Collections.reverse(list1);
        ids = (String[]) list1.toArray();
        
		mainAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
		historyList = (ListView)findViewById(R.id.historyList);
		historyList.setAdapter(mainAdapter);
		historyList.setOnItemClickListener(new MainListOnItemClick());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_home:
			startActivity(new Intent(this, HomeActivity.class));
			return true;
		
		case R.id.menu_search:
			startActivity(new Intent(this, SearchActivity.class));
			return true;

		case R.id.menu_favorite:
			startActivity(new Intent(this, FavoriteBeerActivity.class));
			return true;
			
		case R.id.menu_beeralyzer:
			startActivity(new Intent(this, BeeralyzerActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void addListenerOnButton() {
		imageButton = (ImageButton) findViewById(R.id.addFavorite);
		imageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				EditText searchField = (EditText)findViewById(R.id.editText1);
				String query = searchField.getText().toString();
				if(!query.equals("")){
					new DownloadBeer().execute(query);				
				}
			}
		});
	}
	
	private class MainListOnItemClick implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			Intent intent = new Intent(HomeActivity.this, BeerActivity.class);
			intent.putExtra("beerId", ids[position]);
			startActivity(intent);
		}
	}
	
	private class DownloadBeer extends AsyncTask<String, String, Search> {
		protected void onPreExecute() {
			// éxécute en premier, dans le thread interface.
			setProgressBarIndeterminateVisibility(true);
		}
		protected Search doInBackground(String... params) {
			// éxécute en arrière-plan, dans un thread non-interface
			// va chercher les conditions sur le web...
			String query = params[0];
			Search search = new Search("http://api.brewerydb.com/v2/search?q="+query+"&type=beer&key=441ca8d74a9aacb2f286cf6a2e10c143");
			return search;
		}
		protected void onProgressUpdate(String... s) {
			// éxécute dans le thread interface, si le thread non-interface
			// appelle publishProgress à l'intérieur de doInBackground
		}
		protected void onPostExecute(Search search) {
			// on a fini de charger les informations
			setProgressBarIndeterminateVisibility(false);
			if( search==null ) return;
			if( search.getErreur()!=null ) {
				Toast.makeText(HomeActivity.this,search.getErreur(),Toast.LENGTH_LONG).show();
				return;
			}
			HashMap<String, String> results= search.getResults();
			Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
			intent.putExtra("results", results);
			startActivity(intent);
		}
	}
}
