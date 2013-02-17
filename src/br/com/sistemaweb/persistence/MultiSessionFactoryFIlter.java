/**
 *
 */
package br.com.sistemaweb.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.stat.Statistics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import br.com.sistemaweb.utils.Utils;

public abstract class MultiSessionFactoryFIlter implements Filter {

	private HibernatePicofier interceptor;

	public static final String SESSION = "_hibernate_session_27547237291l";

	private static  final Map<String, SessionFactory> sessionFactories = new HashMap<String, SessionFactory>();

	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain)
				throws ServletException {
		Session session = getSessionFactory(servletRequest).openSession();
		servletRequest.setAttribute(SESSION, session);
		Transaction tr = session.getTransaction();
			try {
				tr.begin();
				filterChain.doFilter(servletRequest, servletResponse);
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
			} finally {
				session.close();
				servletRequest.setAttribute(SESSION, null);
			}
	}

	private SessionFactory getSessionFactory(ServletRequest servletRequest){
		String nomeEmpresa = getNomeEmpresa(servletRequest);
		SessionFactory sessionFactory;
		if((sessionFactory=sessionFactories.get(nomeEmpresa))==null){
			sessionFactory=buildSessionFactory(nomeEmpresa);
			sessionFactories.put(nomeEmpresa,sessionFactory);
		}
		return sessionFactory;
	}

	private String getNomeEmpresa(ServletRequest servletRequest) {
		
		String nomeEmpresa = "distcauper"; 
			//((HttpServletRequest)servletRequest).getSession().getAttribute("empresa").toString();
		return nomeEmpresa;
	}

	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	protected SessionFactory buildSessionFactory(String nomeEmpresa){
		AnnotationConfiguration conf = new AnnotationConfiguration().configure();
		conf.setProperty("hibernate.default_schema", nomeEmpresa);
		MutablePicoContainer container = buildContainer();
		conf.setInterceptor(new HibernatePicofier(container));
		return conf.buildSessionFactory();
	}
	/**
	 * @return
	 */
	public abstract MutablePicoContainer buildContainer() ;
	
	
}