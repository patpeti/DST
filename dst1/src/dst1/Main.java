package dst1;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dst1.interceptor.SQLInterceptor;
import dst1.listener.SimpleListener;
import dst1.query.HibernateQuery;
import dst1.query.Queries;
import dst1.query.TotalUsageQuery;
import dst1.refactoring.CrudTest;
import dst1.refactoring.LifeCycle;
import dst1.validator.Validierung;

public class Main {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Logger log = LoggerFactory.getLogger( Main.class );

	private Main() {
		super();
	}

	public static void main(String[] args) {
		dst01();
		dst02a();
		dst02b();
		dst02c();
		dst03();
		dst04a();
		dst04b();
		dst04c();
		dst04d();
		dst05a();
		dst05b();
		dst05c();
	}
	
	public static void dst01() {
		Ejb3Configuration config = new Ejb3Configuration();
		config.configure( "grid", new HashMap() );
		config.setInterceptor(new SQLInterceptor());
		emf = config.buildEntityManagerFactory();
		em = emf.createEntityManager();
	
		

		
		CrudTest c = new CrudTest(em);
		c.createTest();
		c.retrieveTest();
		c.updateTest();
		
		//deleteTest is working, but commented out for the sake of the next exercises.
//		c.deleteTest();
	
	}

	public static void dst02a() {
		Queries q = new Queries(em);
		q.UserWithMembership();
		q.MostActiveUser();

	}

	public static void dst02b() {
		TotalUsageQuery q = new TotalUsageQuery(em);
		q.aufgabe2b();
		
	}

	public static void dst02c() {
		
		
		HibernateQuery q = new HibernateQuery(em);
		q.aufgabe2c();
		
	}

	public static void dst03() {

		Validierung v = new Validierung();
	}

	public static void dst04a() {
		
		LifeCycle l = new LifeCycle(em);
		
		
	}

	public static void dst04b() {
		//update and create listener are tested - OK
		System.out.println("#############Entity Listener##############");
		em = emf.createEntityManager();
		CrudTest c = new CrudTest(em);
		c.computerUpdate();
	}

	public static void dst04c() {

		System.out.println("#####################Default LIstener############################");
		System.out.println("Load Operations    			: " + SimpleListener.getCountLoad());
		System.out.println("Update Operations  			: " + SimpleListener.getCountUpdate());
		System.out.println("Remove Operations  			: " + SimpleListener.getCountRemove());
		System.out.println("Persist Operations 			: " + SimpleListener.getCountPersist());
		System.out.println("Overall Time to Persist 	: " + SimpleListener.getOverallTime() + " ms");
		System.out.println("Average TIme to Persist 	: " + SimpleListener.getAvgTimetoPersist() + " ms");
		
	}

	public static void dst04d() {

		SQLInterceptor.getLogOutput();
		dst02b();
		SQLInterceptor.getLogOutput();
		SQLInterceptor.resetCounters();
		
	}

        public static void dst05a() {

        }

        public static void dst05b() {

        }

        public static void dst05c() {

        }
}
