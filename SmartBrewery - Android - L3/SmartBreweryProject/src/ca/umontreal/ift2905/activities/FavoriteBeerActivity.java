package ca.umontreal.ift2905.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import ca.umontreal.ift2905.adapter.FavoriteBeerAdapter;
import ca.umontreal.ift2905.db.FavoriteBeerDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteBeerActivity extends Activity {

	private ListView favoritesList;
	private FavoriteBeerDB fbDb;
	private List<String> ids;
	private List<String> names;
	private List<String> iconUrl;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_beer);
		fbDb = new FavoriteBeerDB(this);
		Map<String, ArrayList<String>> favorites = new TreeMap<String, ArrayList<String>>();
		favorites = fbDb.getFavorites();
		ids = new ArrayList<String>();
		names = new ArrayList<String>();
		iconUrl = new ArrayList<String>();
		for (Entry<String, ArrayList<String>> entry : favorites.entrySet()) {
		    ids.add(entry.getKey());
		    names.add(entry.getValue().get(0));
		    iconUrl.add(entry.getValue().get(1));
		}
		favoritesList = (ListView)findViewById(R.id.listViewFavoriteBeer);
		FavoriteBeerAdapter adapter = new FavoriteBeerAdapter(this, names, iconUrl, new ca.umontreal.ift2905.util.ImageUtil.ImageLoaderQueue());
		favoritesList.setAdapter(adapter);
		favoritesList.setOnItemClickListener(new MainListOnItemClick());
 
//        mListAppInfo.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int pos, long id) {
//                // get the list adapter
//                AppInfoAdapter appInfoAdapter = (AppInfoAdapter)parent.getAdapter();
//                // get selected item on the list
//                ApplicationInfo appInfo = (ApplicationInfo)appInfoAdapter.getItem(pos);
//                // launch the selected application
//                Utilities.launchApp(parent.getContext(), getPackageManager(), appInfo.packageName);
//            }
//        });
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.favorite_beer, menu);
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
			Intent intent = new Intent(FavoriteBeerActivity.this, BeerActivity.class);
			intent.putExtra("beerId", ids.get(position));
			startActivity(intent);
		}
	}

}

