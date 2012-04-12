package dst1.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

import dst1.model.Execution;
import dst1.model.Job;
import dst1.model.enums.JobStatus;

public class HibernateQuery {

	//private EntityManagerFactory emf;
	private Session session = null;

	public HibernateQuery(EntityManager em) {
		HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
		session = hem.getSession();
	
		System.out.println("#####################Hibernate Queries#########################");
		
	}

	public void aufgabe2c() {
		
		//should be job 2 - OK
		this.jobsByUserAndWorkflow("renameduser" , "windows-worklow");
		
		//
		int year = 2011;
	    int month = 12;
	    int day = 12;

	    String date = year + "/" + month + "/" + day;
	    java.util.Date startDate = null;

	    try {
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	      startDate = formatter.parse(date);
	      //System.out.println("utilDate:" + utilDate);
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
	    
	    year = 2011;
	    month = 12;
	    day = 13;
	    date = year + "/" + month + "/" + day;
	    java.util.Date endDate = null;
	    
	    try {
		      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		      endDate = formatter.parse(date);
		      //System.out.println("utilDate:" + utilDate);
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
		this.findAllFinished(startDate, endDate);
		
	}

	private void findAllFinished(Date startDate, Date endDate) {
		
		Job job = new Job();
		Execution ex = new Execution();
		ex.setStatus(JobStatus.FINISHED);
		if(startDate != null) ex.setStart(startDate);
		if(endDate != null) ex.setEnd(endDate);
		job.setExecution(ex);
		
		List result = session.createCriteria(Job.class)
				.add(Example.create(job))
				.createCriteria("execution")
					.add(Example.create(ex))
				.list();
		
		System.out.println("Query by Example: ");
		for(Object o : result){
			Job j = (Job) o;
			System.out.println("Job id: "+ j .getId());
			
		}
		
	}

	private void jobsByUserAndWorkflow(String username, String workflow) {
		 //Criteria Query Example

		
		Criteria crit = session.createCriteria(Job.class);
		if(username != null) crit.createCriteria("user").add(Restrictions.like("username",username));
		if(workflow != null) crit.createCriteria("environment").add(Restrictions.like("workflow", workflow));
		List<Job> jobs = (List<Job>) crit.list();
		System.out.println("Hibernate Criteria Query: ");
		for(Job j : jobs){
			System.out.println("Job id : "+ j.getId());
		}
		
		//session.close();
	}
}
