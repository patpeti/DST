package dst1.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dst1.model.Job;
import dst1.model.User;

public class Queries {
	
	private EntityManager em;

	public Queries(EntityManager em) {
		this.em = em;
		System.out.println("#######################QUERIES##############################");
	}

	public void UserWithMembership() {
		 /*
		  * 
		  * gridrenamed1(id:1) -> renameduser(id:1) has two jobs
		  * 									    user 1,2,3 has membership
		  * 	
		  * grid2(id:2) --> linuxuser(id:2) has one job
		  * 										user 1,2 has membership
		  * 
		  * grid3(id:3) --> windowsuser(id:3) has one job
		  * 										user 2,3 has memebership
		  * 
		  * OK
		  */
		
		 Query query = em.createNamedQuery("UserWithMembership");

		 query.setParameter("numberofjobs", new Long(0));
		 query.setParameter("gridname", "grid2");
		 
		 List<User> uList = (List<User>) query.getResultList();
		 System.out.println("UserWithMembership: ");
		 for(User u : uList){
			 System.out.println("UserId: " + u.getId() + " Username: " + u.getUsername());
		 }
 
		
		 
		
	}

	public void MostActiveUser() {
		 Query query = em.createNamedQuery("MostActiveUser");
		 //query.setMaxResults(1);
		 List<User> uList = (List<User>) query.getResultList();
		 System.out.println("MostActiveUser(s): ");
		 int temp = uList.get(0).getJobs().size();
		 for(User u : uList){
			 if(u.getJobs().size() == temp)
			 System.out.println("UserId: " + u.getId() + " Username: " + u.getUsername() + " Jobs: " + u.getJobs().size());
		 }
 

		
	}
	
	

}
