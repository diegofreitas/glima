/**
 *
 */
package br.com.sistemaweb.persistence;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.stat.Statistics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import br.com.sistemaweb.utils.Utils;

public abstract class AbstractHibernateSessionFilter implements Filter {

	private SessionFactory sessionFactory;

	private HibernatePicofier interceptor;

	public static final String SESSION = "_hibernate_session_27547237291l";

	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain)
				throws ServletException {
		Session session = sessionFactory.openSession(interceptor);
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

	public void destroy() {
		sessionFactory.close();
		sessionFactory = null;
	}

	public void init(FilterConfig arg0) throws ServletException {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		MutablePicoContainer container = buildContainer();
		interceptor=new HibernatePicofier(container);
		final Statistics stat = sessionFactory.getStatistics();
		Utils.STAT=stat;
		Utils.printHibernateStatistics();
	}

	/**
	 * @return
	 */
	public abstract MutablePicoContainer buildContainer() ;
	
	
}