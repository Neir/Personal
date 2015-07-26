package ca.umontreal.ift2905.activities;

import java.util.HashMap;
import java.util.Map.Entry;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private ListView mainList;
	private ArrayAdapter<String> mainAdapter;
	private String[] names;
	private String[] ids;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_result);

		Bundle b = getIntent().getExtras();
		HashMap<String, String> results = (HashMap<String, String>) b.get("results");
		
		TextView nbResults = (TextView)findViewById(R.id.nbResults);
		nbResults.setText(results.size()+" results:");	
		
		names = new String[results.size()];
		ids = new String[results.size()];
		int i = 0;
		
		for(Entry<String, String> entry : results.entrySet()) {
		    String id = entry.getKey();
		    String name = entry.getValue();
		    ids[i] = id;
		    names[i] = name;
		    i++;
		}
		
		mainAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

		mainList = (ListView) findViewById(R.id.listViewResult);
		mainList.setAdapter(mainAdapter);
		mainList.setOnItemClickListener(new MainListOnItemClick());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
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
	
	private class MainListOnItemClick implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			Intent intent = new Intent(ResultActivity.this, BeerActivity.class);
			intent.putExtra("beerId", ids[position]);
			startActivity(intent);
		}
	}

}
