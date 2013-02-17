/**
 * Este artefato de software foi desenvolvido por mim (root) em 16/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


import org.apache.commons.io.IOUtils;
import org.hibernate.stat.Statistics;

import br.com.sistemaweb.IApplicationContext;
import br.com.sistemaweb.reporting.ContentType;

/**
 * @author root
 *
 */
public class Utils {
	
	public static Statistics STAT;
	
	public static Object getUIParameter(final String key, final ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		for(UIComponent component: comandLink.getChildren()){
			if(component instanceof UIParameter){
				UIParameter parameter = (UIParameter) component;
				if(parameter.getName().equals(key)){
					return parameter.getValue();
				}
			}
		}
		throw new RuntimeException(String.format("Parameter named '%s' not found",key));	
	}
	
	public static InputStream getResourceAsStream(String str) throws FileNotFoundException{
		InputStream resourceAsStream = Utils.class.getResourceAsStream(str);
		if(resourceAsStream==null){
			resourceAsStream=new FileInputStream(new File(str));
		}
		return resourceAsStream;
	}
	
	public static String fileToString(String path){
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = "";
			while((line=reader.readLine())!=null){
				buffer.append(line);
			}
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	/**
	 * 
	 */
	public static void printHibernateStatistics() {
			System.out.println(STAT);
	}

	/**
	 * @return
	 */
	public static IApplicationContext getApplicationContext() {
		
		return (IApplicationContext) getManagedBean("context");
	}
	
	public static Object getManagedBean(String mb){
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding binding =context.getApplication().createValueBinding("#{"+mb+"}");
		return (Object) binding.getValue(context);
	}

	/**
	 * @param id
	 * @return
	 */
	public  static String captalizar(String str){
		byte[] bytes = str.getBytes();
		bytes[0]=(byte) (bytes[0]-32);
		return new String(bytes);
	}
	
	public static <T> Collection<SelectItem> createCollectionItems(Collection<T> c) {
		ArrayList<SelectItem> items = new ArrayList<SelectItem>();
		for (Object o : c) {
			SelectItem item = new SelectItem();
			item.setLabel("Selecione um Registro");
			if (o != null) {
				item.setLabel(o.toString());
				item.setValue(o);
			}
			items.add(item);
		}
		return items;
	}

	public static Collection<SelectItem> createCollectionItems(Object[] c) {
		ArrayList<SelectItem> items = new ArrayList<SelectItem>();
		for (Object o : c) {
			SelectItem item = new SelectItem();
			item.setValue(o);
			item.setLabel(o.toString());
			items.add(item);
		}
		return items;
	}
	
	
	public static File bytesToFile(String path,byte[] data) throws IOException{
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			FileOutputStream output = new FileOutputStream(file);
			output.write(data);
			output.flush();
			output.close();
			return null;

	}
	
	public static byte[] FileToBytes(File data){
		try {
			IOUtils.toByteArray(new FileInputStream(data));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToSession(String name, ContentType exportType,byte[] stream) {
		getApplicationContext().setSessionValue(name,new ServletDataHelper(name,exportType,stream));
	}
}
