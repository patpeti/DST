package dst1.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dst1.model.Computer;
import dst1.model.Execution;

public class TotalUsageQuery {
	
	private EntityManager em;

	public TotalUsageQuery(EntityManager em) {
		this.em = em;
	}
	
	public void aufgabe2b(){
		
		Query q = em.createNamedQuery("TotalUsage");
		System.out.println("Total Usage: ");
		List<Long> cIdList = q.getResultList();
		em.getTransaction().begin();
		for (int batch = 0; batch < cIdList.size(); batch += 100) {
		      for (int index = 0; index < 100 && (batch + index) < cIdList.size(); index++) {
		    	  Long id = cIdList.get(batch + index);
		    	  Computer c = em.find(Computer.class, id);
		    	  System.out.print("Computer name: " +  c.getName());
		    	  Long sumExecutions = new Long(0);
					for(Execution e : c.getExecutions()){
						sumExecutions += e.getEnd().getTime() - e.getStart().getTime();
					}
					System.out.println(" Total Executiontime: "+sumExecutions);
		    	  
		      }
		      
		      em.flush();
		      em.clear(); 
		}

		em.getTransaction().commit();
	}

}
