package ca.umontreal.ift2905.activities;

import ca.umontreal.ift2905.api.Beer;
import ca.umontreal.ift2905.db.FavoriteBeerDB;
import ca.umontreal.ift2905.db.SearchHistoryDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BeerActivity extends Activity implements OnClickListener {

	String id;
	TextView name;
	TextView abv;
	TextView description;
	TextView categoryName;
	TextView styleName;
	TextView styleDescription;
	ImageView image;
	Button addFavorite;
	FavoriteBeerDB fbDb;
	SearchHistoryDB shDb;
	Beer beer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_beer);
		Bundle b = getIntent().getExtras();
		id=b.getString("beerId");
		name=(TextView)findViewById(R.id.favoriteBeerTitle);
		abv=(TextView)findViewById(R.id.beerAbv);
		description=(TextView)findViewById(R.id.beerDescription);
		image=(ImageView)findViewById(R.id.beerImage);
		categoryName=(TextView)findViewById(R.id.categoryName);
		styleName=(TextView)findViewById(R.id.styleName);
		styleDescription=(TextView)findViewById(R.id.styleDescription);
		addFavorite=(Button)findViewById(R.id.addFavorite);
		addFavorite.setVisibility(View.INVISIBLE);
		addFavorite.setOnClickListener(this);
		fbDb = new FavoriteBeerDB(this);
		shDb = new SearchHistoryDB(this);
		new DownloadBeer().execute();
	}
	
	private class DownloadBeer extends AsyncTask<String, String, Beer> {
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}
		protected Beer doInBackground(String... params) {
			beer = new Beer("http://api.brewerydb.com/v2/beers?ids="+id+"&key=441ca8d74a9aacb2f286cf6a2e10c143&format=json");
			return beer;
		}
		protected void onProgressUpdate(String... s) {	}
		protected void onPostExecute(Beer beer) {
			setProgressBarIndeterminateVisibility(false);
			if( beer==null ) return;
			if( beer.getErreur()!=null ) {
				Toast.makeText(BeerActivity.this,beer.getErreur(),Toast.LENGTH_LONG).show();
				return;
			}
			name.setText(beer.getName());
			if(beer.getAbv()==null) {
				abv.setText(getString(R.string.beerAbv));
			} else {
				abv.setText(beer.getAbv());				
			}
			if(beer.getDescription()==null) {
				description.setText(getString(R.string.beerDescription));
			} else {
				description.setText(beer.getDescription());				
			}
			if(beer.getCategoryName()==null) {
				categoryName.setText(getString(R.string.beerCategoryName));
			} else {
				categoryName.setText(beer.getCategoryName());				
			}
			if(beer.getAbv()==null) {
				styleName.setText(getString(R.string.beerStyleName));
			} else {
				styleName.setText(beer.getStyleName());				
			}
			if(beer.getAbv()==null) {
				styleDescription.setText(getString(R.string.beerStyleDescription));
			} else {
				styleDescription.setText(beer.getStyleDescritpion());				
			}
			image.setImageDrawable(beer.getImage());
			addFavorite.setVisibility(View.VISIBLE);
			BeerActivity.this.shDb.AddNewEntry(id,beer.getName());
		}
	}

	@Override
	public void onClick(View v) {
		BeerActivity.this.fbDb.AddNewEntry(this.id,beer.getName(),beer.getImageUrl());
		Toast.makeText(BeerActivity.this, beer.getName()+" added to favorites",Toast.LENGTH_SHORT).show();
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
}
