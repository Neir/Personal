package ca.umontreal.ift2905.activities;

import java.util.HashMap;
import java.util.Iterator;

import ca.umontreal.ift2905.api.RandomSearch;
import ca.umontreal.ift2905.api.Search;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private Button buttonRandomSearch;
	private Button buttonSearch;
	private String array_degree[];
	private String array_category[];
	
	private CheckBox checkBox1;
	private CheckBox checkBox2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		addListenerOnButton();

		array_degree=new String[5];
		array_degree[0]="0-5";
		array_degree[1]="6-10";
		array_degree[2]="11-15";
		array_degree[3]="16-20";
		array_degree[4]=">20";
		Spinner s1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_degree);
		s1.setAdapter(adapter);
		s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				checkBox1.setChecked(true);
			}
			public void onNothingSelected(AdapterView<?> arg0){
				checkBox1.setChecked(false);
			}
		});
		
		array_category=new String[12];
		array_category[0]="British Origin Ales";
		array_category[1]="Irish Origin Ales";
		array_category[2]="North American Origin Ales";
		array_category[3]="German Origin Ales";
		array_category[4]="Belgian And French Origin Ales";
		array_category[5]="International Ale Styles";
		array_category[6]="European-germanic Lager";
		array_category[7]="North American Lager";
		array_category[8]="Other Lager";
		array_category[9]="International Styles";
		array_category[10]="Hybrid/mixed Beer";
		array_category[11]="Mead, Cider, & Perry";
		Spinner s2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_category);
		s2.setAdapter(adapter2);
		s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{				
				checkBox2.setChecked(true);
			}
			public void onNothingSelected(AdapterView<?> arg0){
				checkBox2.setChecked(false);
			}
		});
		
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
		if(checkBox1.isChecked()) checkBox1.setChecked(false);
		if(checkBox2.isChecked()) checkBox2.setChecked(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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

	public void addListenerOnButton() {
		buttonRandomSearch = (Button) findViewById(R.id.button1);
		buttonRandomSearch.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				new DoRandomSearch().execute();				
			}
		});
		
		buttonSearch = (Button) findViewById(R.id.button2);
		buttonSearch.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Spinner s = (Spinner) findViewById(R.id.spinner1);
				int positionItem = s.getSelectedItemPosition();
				String degree = "";
				switch(positionItem){
					case 0:
						degree = "0,5";
						break;
					case 1:
						degree = "6,10";
						break;
					case 2:
						degree = "11,15";
						break;
					case 3:
						degree = "16,20";
						break;
					case 4:
						degree = "21,100";
						break;
					default: degree = "0";
				}
				Spinner catSpinner = (Spinner) findViewById(R.id.spinner1);
				String idCategory = String.valueOf(catSpinner.getSelectedItemPosition()+1);
				String[] params = new String[2];
				params[0]=degree;
				params[1]=idCategory;
				if(checkBox1.isChecked()||checkBox2.isChecked())
					new DoSearch().execute(params);
			}
		});
	}

	private class DoRandomSearch extends AsyncTask<String, String, RandomSearch> {
		protected void onPreExecute() {
			// éxécute en premier, dans le thread interface.
			setProgressBarIndeterminateVisibility(true);
		}
		protected RandomSearch doInBackground(String... params) {
			// éxécute en arrière-plan, dans un thread non-interface
			// va chercher les conditions sur le web...
			RandomSearch search = new RandomSearch("http://api.brewerydb.com/v2/beer/random?key=441ca8d74a9aacb2f286cf6a2e10c143");
			return search;
		}
		protected void onProgressUpdate(String... s) {
			// éxécute dans le thread interface, si le thread non-interface
			// appelle publishProgress à l'intérieur de doInBackground
		}
		protected void onPostExecute(RandomSearch search) {
			// on a fini de charger les informations
			setProgressBarIndeterminateVisibility(false);
			if( search==null ) return;
			if( search.getErreur()!=null ) {
				Toast.makeText(SearchActivity.this,search.getErreur(),Toast.LENGTH_LONG).show();
				return;
			}
			HashMap<String, String> results= search.getResults();
			Iterator<String> it = results.keySet().iterator();
			String idBeer = it.next();
			Intent intent = new Intent(SearchActivity.this, BeerActivity.class);
			intent.putExtra("beerId", idBeer);
			startActivity(intent);
		}
	}
	
	private class DoSearch extends AsyncTask<String, String, Search> {
		protected void onPreExecute() {
			// éxécute en premier, dans le thread interface.
			setProgressBarIndeterminateVisibility(true);
		}
		protected Search doInBackground(String... params) {
			// éxécute en arrière-plan, dans un thread non-interface
			// va chercher les conditions sur le web...
			String degree = params[0];
			String idCategory = params[1];
			Search search;
			if(checkBox1.isChecked()&&!checkBox2.isChecked()){
				search = new Search("http://api.brewerydb.com/v2/beers?abv="+degree+"&key=441ca8d74a9aacb2f286cf6a2e10c143");
				return search;
			}
			if(checkBox2.isChecked()&&!checkBox1.isChecked()){
				search = new Search("http://api.brewerydb.com/v2/beers?categoryId="+idCategory+"&key=441ca8d74a9aacb2f286cf6a2e10c143");
				return search;
			}
			if(checkBox2.isChecked()&&checkBox1.isChecked()){
				search = new Search("http://api.brewerydb.com/v2/beers?categoryId="+idCategory+"&beers?abv="+degree+"&key=441ca8d74a9aacb2f286cf6a2e10c143");
				return search;
			}
			return null;
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
				Toast.makeText(SearchActivity.this,search.getErreur(),Toast.LENGTH_LONG).show();
				return;
			}
			HashMap<String, String> results= search.getResults();
			Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
			intent.putExtra("results", results);
			startActivity(intent);
		}
	}

}
