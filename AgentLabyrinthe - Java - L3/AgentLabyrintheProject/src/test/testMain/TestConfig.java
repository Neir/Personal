package test.testMain;

import java.io.IOException;

import pobj.algogen.config.ChargeurConfig;
import pobj.algogen.config.Configuration;



public class TestConfig {
	public static void main(String []args){
		Configuration conf = Configuration.getInstance();
		conf.setParameterValue("ça", "marche");
		conf.setParameterValue("super", "bien");
		try {
			//ChargeurConfig.sauverConfig("/users/Etu3/3000623/LI314/testConfig.txt", conf);
			ChargeurConfig.sauverConfigPerso("config/testConfig.txt", Configuration.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ChargeurConfig.chargerConfigPerso("config/testConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf.setParameterValue("supere", "biene");
		System.out.println(Configuration.getInstance().getParams());
		
		try {
			ChargeurConfig.sauverConfigPerso("config/testConfigCopie.txt", Configuration.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
