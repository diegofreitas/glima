/**
 * Este artefato de software foi desenvolvido por mim (root) em 28/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author root
 *
 */
public class Configuration {

	private static Configuration instance;
	
	private Properties properties=new Properties();
	
	static{
		instance=new Configuration();
	}
	
	private Configuration(){}
	
	public static void loadConfiguration(){
		try {
			instance.properties.load(Utils.getResourceAsStream(instance.valueFor("config.path")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String valueFor(String prop ){
		String value;
		if((value=instance.properties.getProperty(prop))!=null){
			if(value.contains("{")){
				int i = value.indexOf("{");
				int j = value.indexOf("}");
				String innerProp = value.substring(i+1, j);
				String innerValue ;
				if((innerValue = valueFor(innerProp))==null){
					throw new RuntimeException("Propertie "+innerProp+" not set");
				}
				return value.replace("{"+innerProp+"}", innerValue);
			}
		}
		return value;
	}
	
	public static void putValue(String p,String value){
		instance.properties.setProperty(p, value);
	}


}
