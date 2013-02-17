package br.com.sistemaweb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.velocity.app.Velocity;

import br.com.sistemaweb.utils.Configuration;


public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent e) {
			String appRealPath = e.getServletContext().getRealPath("/");
			Configuration.putValue("config.path",appRealPath+e.getServletContext().getInitParameter("config.path"));
			Configuration.putValue("app.real.path", appRealPath);
		 	Configuration.putValue("app.path","http://127.0.0.1:8080/sistemaweb/");
		 	Configuration.putValue("context.path",e.getServletContext().getContextPath());
		 	System.out.println(e.getServletContext().getServerInfo());
			Configuration.loadConfiguration();
			Velocity.setProperty("file.resource.loader.path",Configuration.valueFor("template.path")); 
			try {
				Velocity.init();
			} catch (Exception e1) {
				//log.error("Exception in contextInitialized", e1);
				e1.printStackTrace();
			} 
	
	}

}
