package ca.umontreal.ift2905.api;

import java.io.IOException;
import java.io.InputStream;

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

import android.graphics.drawable.Drawable;

public class Beer {

	private String abv;
	private String name;
	private String description;
	private String categoryName;
	private String styleName;
	private String styleDescription;
	private Drawable image;
	private String iconUrl;
	
	private String erreur;
	
	public Beer(String url) {
		erreur=null;
		try {
			HttpEntity page = getHttp(url);
			JSONObject js = new JSONObject(EntityUtils.toString(page,HTTP.UTF_8));
			JSONArray data = js.getJSONArray("data");
			for (int i = 0; i < data.length(); ++i) {
			    JSONObject obj = data.getJSONObject(i);
			    name = (String) obj.get("name");
			    try {
			    	abv = "ABV: "+(String) obj.get("abv")+"%";
			    } catch (JSONException e) {
			    	abv = null;
			    }
			    try {
			    	description = "Description: "+(String) obj.get("description");
			    } catch (JSONException e) {
			    	description = null;
			    }
			    try {
			    	JSONObject label = obj.getJSONObject("labels");
			    	iconUrl = label.getString("icon");
			    	String beerImage = label.getString("medium");
			    	image=loadHttpImage(beerImage);
			    } catch (JSONException e) {
			    }
			    try {
			    	JSONObject style = obj.getJSONObject("style");
			    	JSONObject category = style.getJSONObject("category");
			    	categoryName = "Category: "+category.getString("name");
			    	styleName = "Style: "+style.getString("name");
			    	styleDescription = "Style description: "+style.getString("description");	    	
			    } catch (JSONException e) {
			    	categoryName = null;
			    	styleName = null;
			    	styleDescription = null;
			    }
			}
		} catch (ClientProtocolException e) {
			erreur="erreur http(protocol):"+e.getMessage();
		} catch (IOException e) {
			erreur="erreur http(IO):"+e.getMessage();
		} catch (ParseException e) {
			erreur="erreur JSON(parse):"+e.getMessage();
		} catch (JSONException e) {
			erreur="erreur JSON:"+e.getMessage();
        }
	}

	private static HttpEntity getHttp(String url) throws ClientProtocolException, IOException
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet http = new HttpGet(url);
		HttpResponse response = httpClient.execute(http);
		return response.getEntity();
	}

	public static Drawable loadHttpImage(String url) throws ClientProtocolException, IOException {
		InputStream is = getHttp(url).getContent();
		Drawable d = Drawable.createFromStream(is, "src");
		return d;
	}

	public String getName() {
		return this.name;
	}
	
	public String getAbv() {
		return this.abv;
	}
	
	public String getErreur() {
		return this.erreur;
	}

	public String getDescription() {
		return this.description;
	}

	public Drawable getImage() {
		return this.image;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	
	public String getStyleName() {
		return this.styleName;
	}
	
	public String getStyleDescritpion() {
		return this.styleDescription;
	}

	public String getImageUrl() {
		return this.iconUrl;
	}
}
