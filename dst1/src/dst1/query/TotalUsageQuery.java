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
		System.out.print("Total Usage: ");
		List<Computer> cList = q.getResultList();
		for(Computer c : cList){
			
			System.out.print("Computer name: " +  c.getName());
			Long sumExecutions = new Long(0);
			for(Execution e : c.getExecutions()){
				sumExecutions += e.getEnd().getTime() - e.getStart().getTime();
			}
			System.out.println(" Total Executiontime: "+sumExecutions);
			
		}
		
	}

}
