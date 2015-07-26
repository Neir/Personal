package ca.umontreal.ift2905.activities;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import ca.umontreal.ift2905.adapter.FavoriteBeerAdapter;
import ca.umontreal.ift2905.api.Beer;
import ca.umontreal.ift2905.db.FavoriteBeerDB;
import ca.umontreal.ift2905.db.SearchHistoryDB;
import ca.umontreal.ift2905.util.RowTablelayout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BeeralyzerActivity extends Activity {
	private final String defaut = "Vous devez cliquer sur le bouton « Calculer » pour obtenir un résultat.";
	
	private Button calculer = null;
	private Button raz = null;

	private EditText poids = null;
	private RadioGroup group = null;

	private TextView result = null;
	
	private Button plus;
	private Button moins;
	private TextView quantité;
	
	private ArrayList<RowTablelayout> rows = new ArrayList<RowTablelayout>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_beeralyzer);
		
//		if(favorites.size() == 0){
//			TextView text = (TextView)findViewById(R.id.favorites);
//			text.setText(text.getText()+" Empty");
//		}
		
		
		plus = new Button(getBaseContext());
		plus.setText("+");
		moins = new Button(getBaseContext());
		moins.setText("-");
		quantité = new TextView(getBaseContext());
		quantité.setText("0x");
		
//		favoritesList.setOnItemClickListener(new MainListOnItemClick());
//		FavoriteBeerAdapter adapter = new FavoriteBeerAdapter(this, names, iconUrl, new ca.umontreal.ift2905.util.ImageUtil.ImageLoaderQueue());
		

		setContentView(R.layout.activity_beeralyzer);

		calculer = (Button) findViewById(R.id.calculer);
	    raz = (Button)findViewById(R.id.raz);
	    poids = (EditText)findViewById(R.id.poids);
	    group = (RadioGroup)findViewById(R.id.sexe);
	    result = (TextView)findViewById(R.id.result2);
	    
	    
	    ArrayList<String> names = new ArrayList<String>();
	    
//	    just for test
	    names.add("Leffe");
	    names.add("Guiness");
	    names.add("Belle Gueule");
	    
	    for(String name : names){
	    	rows.add(new RowTablelayout(name, 
	    			(Button)findViewById(R.id.plus),
	    			(Button)findViewById(R.id.moins),
	    			(TextView)findViewById(R.id.qty)));
	    }
	    
		calculer.setOnClickListener(calculerListener);
	    raz.setOnClickListener(razListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beeralyzer, menu);
		
        //Intent myIntent = new Intent(getBaseContext(), AdvancedsearchActivity.class);
        //startActivityForResult(myIntent, 0);
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
	     
	  // Uniquement pour le bouton "envoyer"
	private OnClickListener calculerListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String p = poids.getText().toString();
			float pValue = Float.valueOf(p);
			float coefDisp = (float) ((group.getCheckedRadioButtonId() == R.id.femme)?0.6:0.7);
			
			//degré d'alccol
			float a = 0;
			
			int abv = 0;
			for(RowTablelayout r : rows){
				abv = 5;//Integer.valueOf(r.getBeer().getAbv());
				a += abv * r.getQty();
			}
			
			//Alcoolémie
			float t = (float) (abv*0.8 / pValue*coefDisp);

			String res = "Votre avez " + String.valueOf(t) + " d'alcool dans le sang.";
			if(t>0.5){
				res+="Il est déconseillé de prendre le volant.";
			} else
				res+="Désolé mais vous étes sobre.";
			result.setText(res);
		}
	};

	  // Listener du bouton de remise à zéro
	  private OnClickListener razListener = new OnClickListener() {
	    @Override
	    public void onClick(View v) {
	      poids.getText().clear();
	      for(RowTablelayout r : rows)
	    	  r.clear();
	      result.setText(defaut);
	    }
	  };

}
