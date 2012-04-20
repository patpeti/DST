package dst1.refactoring;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import dst1.model.Job;
import dst1.model.enums.JobStatus;

public class NoSQLTest {
	
	private EntityManager em;
	private List<Job> jobs;
	private Session session;
	
	private Mongo m;
	private DB db;
	private DBCollection coll;
	
			
	public NoSQLTest(EntityManager em) {
		this.em = em;
		System.out.println("#################NOSQL Part#######################");
		retrieveFinishedJObs();
		initDB();
		saveWorkflowOutput();
		System.out.println("Mongo Query: ");
		
		for(Job j : jobs){
			this.makeQuery(j.getId());
		}
		System.out.println("Mongo Query2: ");
		this.makeQuery2(new Long(1325397600));
	}

	

	



	private void retrieveFinishedJObs() {
		
		HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
		session = hem.getSession();
		
	
		Criteria crit = session.createCriteria(Job.class);
		crit.createCriteria("execution").add(Restrictions.eq("status", JobStatus.FINISHED));
		jobs = (List<Job>) crit.list();
		System.out.println("Finished Jobs: ");
		for(Job j : jobs){
			System.out.println("Job id : "+ j.getId());
		}
		
		
		
		
		
	}

	private void initDB() {
		try {
			m = new Mongo();
			System.out.println("Mongodb connetion ready...");
			m.dropDatabase("worklfow_output");
			db = m.getDB("worklfow_output");
			System.out.println("Mongodb db ready...");
			coll = db.getCollection("worklow_collection");
			System.out.println("Mongodb collection ready...");
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void saveWorkflowOutput() {
		//Struktur 1:
		
		BasicDBObject one = new BasicDBObject();
		one.put("job_id", this.jobs.get(0).getId());
		one.put("lastUpdated", new Date(1325397500));
		one.put("type", "typeOne");
		BasicDBObject subType = new BasicDBObject();
		subType.put("x", 2323);
		subType.put("y", 2323);
		one.put("result_matrix", subType);
		
		coll.insert(one);
		System.out.println("Struktur 1 added successfully");
		
		//Struktur 2:
		
		BasicDBObject two = new BasicDBObject();
		two.put("job_id", this.jobs.get(1).getId());
		two.put("lastUpdated", new Date());
		two.put("type", "typeTwo");
		BasicDBObject subTypeTwo = new BasicDBObject();
		BasicDBObject subSubTypeTwo = new BasicDBObject();
		subSubTypeTwo.put("x", 3434);
		subSubTypeTwo.put("y", 4343);
		
		subTypeTwo.put("matrix", subSubTypeTwo);

		two.put("result_matrix", subTypeTwo);
		
		coll.insert(two);
		System.out.println("Struktur 2 added successfully");
		//Struktur 3:
		
		BasicDBObject three = new BasicDBObject();
		three.put("job_id", this.jobs.get(2).getId());
		three.put("lastUpdated", new Date());
		three.put("type", "typeThree");
		BasicDBObject subTypeThree = new BasicDBObject();
		BasicDBObject subSubTypeThree = new BasicDBObject();
		subSubTypeThree.put("x", 3434);
		subSubTypeThree.put("y", 4343);
		subSubTypeThree.put("z", 343);
		
		subTypeThree.put("matrix", subSubTypeThree);

		three.put("result_matrix", subTypeThree);
		
		coll.insert(three);
		System.out.println("Struktur 3 added successfully");
		//Struktur 4:
		
		BasicDBObject four = new BasicDBObject();
		four.put("job_id", this.jobs.get(3).getId());
		four.put("lastUpdated", new Date(1325397500));
		four.put("type", "typeFour");
		BasicDBObject subTypeFour = new BasicDBObject();
		BasicDBObject subSubTypeFour = new BasicDBObject();
		subSubTypeFour.put("x", 3434);
		subSubTypeFour.put("y", 4343);
		subSubTypeFour.put("z", 4343);
		subSubTypeFour.put("a", 4343);
		subTypeFour.put("matrix", subSubTypeFour);

		four.put("result_matrix", subTypeFour);
		
		coll.insert(four);
		System.out.println("Struktur 4 added successfully");
		//Struktur 5:
		
		BasicDBObject five = new BasicDBObject();
		five.put("job_id", this.jobs.get(4).getId());
		five.put("lastUpdated", new Date());
		five.put("type", "typeFive");
		BasicDBObject subTypeFive = new BasicDBObject();
		List<List<Integer>> matr = new ArrayList<List<Integer>>();
		for(int i = 0; i < 4; i++){
			List<Integer> tempListe = new ArrayList<Integer>();
			for(int j = 0; j < 4; j++){
				tempListe.add(i*j);
			}
			matr.add(tempListe);
			
		}
		subTypeFive.put("matrix", matr);

		five.put("result_matrix", subTypeFive);
		
		coll.insert(five);
		System.out.println("Struktur 5 added successfully");
		
		//create ascending index on job_id
		coll.createIndex(new BasicDBObject("job_id", 1));
	}
	
	private void makeQuery(Long jobId){
		
		 BasicDBObject query = new BasicDBObject();

	     query.put("job_id", jobId);
	     
	     
	     BasicDBObject subSet = new BasicDBObject();
	     subSet.put("result_matrix",1);
	       
	     DBCursor cur = coll.find(query, subSet);

	     while(cur.hasNext()) {
	           System.out.println(cur.next());
	     }
		
	}
	
	private void makeQuery2(Long long1) {
		 BasicDBObject query = new BasicDBObject();
		 

	     query.put("lastUpdated", new BasicDBObject("$gt",new Date(long1)));
	     
	     
	     BasicDBObject subSet = new BasicDBObject();
	     subSet.put("result_matrix",1);
	       
	     DBCursor cur = coll.find(query, subSet);

	     while(cur.hasNext()) {
	           System.out.println(cur.next());
	     }
		
	}

}
