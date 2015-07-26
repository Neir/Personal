package ca.umontreal.ift2905.api;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Search {

	HashMap<String, String> results;
	String erreur;
	
	public Search(String url) {
		Log.d("search","loading "+url);		
		erreur = null;
		try {
			// lire la page web
			HttpEntity page = getHttp(url);
			// JSON format
			JSONObject js = new JSONObject(EntityUtils.toString(page,HTTP.UTF_8));
			System.out.println(js);
			results = new HashMap<String, String>();
			// extraire les informations
			JSONArray data = js.getJSONArray("data");
			Log.d("nb", String.valueOf(data.length()));
			for(int i=0;i<data.length();i++){
				JSONObject object = data.getJSONObject(i);
				String id = (String)object.get("id");
				Log.d("id", id);
				String name = (String) object.get("name");
				Log.d("name", name);
				results.put(id, name);
			}
			
		} catch (ClientProtocolException e) {
			erreur="erreur http(protocol):"+e.getMessage();
		} catch (IOException e) {
			erreur="erreur http(IO):"+e.getMessage();
		} catch (ParseException e) {
			erreur="erreur JSON(parse):"+e.getMessage();
		} catch (JSONException e) {
			erreur = "No results";
        }
	}

	private HttpEntity getHttp(String url) throws ClientProtocolException, IOException
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet http = new HttpGet(url);
		HttpResponse response = httpClient.execute(http);
		return response.getEntity();
	}

	public HashMap<String, String> getResults() {
		return this.results;
	}
	
	public String getErreur() {
		return this.erreur;
	}
}
