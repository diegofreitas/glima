package br.com.sistemaweb.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static ThreadLocal<Session> sessions;

	private static SessionFactory sessionFactory;
	
	static{
		
		
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			sessions= new ThreadLocal<Session>();
		}

		private static Session openSession() {
			return sessionFactory.openSession();
		}

		public static Session getCurrentSession() {
			if (sessions.get() == null) {
				sessions.set(openSession());
			}
			return sessions.get();
		}

		public static void closeCurrentSession() {
			getCurrentSession().close();
			sessions.set(null);
		}

	
}
