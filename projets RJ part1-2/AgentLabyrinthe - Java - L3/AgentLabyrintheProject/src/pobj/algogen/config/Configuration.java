package pobj.algogen.config;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private static Configuration uniqueInstance;
	private Map<String,String> params = new HashMap<String,String>();
	
	private Configuration() {
		super();
	}
	
	public static Configuration getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new Configuration();
		return uniqueInstance;
	}

	public String getParameterValue(String param){
		return params.get(param);
	}

	public void setParameterValue(String param, String value){
		params.put(param, value);
	}
	
	public Map<String, String> getParams() {
		return params;
	}
}
