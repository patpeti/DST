package dst1;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dst1.model.Address;
import dst1.model.User;
import dst1.sub.CrudTest;

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
		emf = config.buildEntityManagerFactory();
		em = emf.createEntityManager();
	
		

		
		CrudTest c = new CrudTest(em);
		c.createTest();
		c.retrieveTest();
		c.updateTest();
		//deleteTest is working, but commented out for the sake of the next exercises.
		//c.deleteTest();
	
	}

	public static void dst02a() {

	}

	public static void dst02b() {

	}

	public static void dst02c() {

	}

	public static void dst03() {

	}

	public static void dst04a() {

	}

	public static void dst04b() {

	}

	public static void dst04c() {

	}

	public static void dst04d() {

	}

        public static void dst05a() {

        }

        public static void dst05b() {

        }

        public static void dst05c() {

        }
}
