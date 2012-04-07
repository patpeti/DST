package dst1.refactoring;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dst1.model.User;

public class Queries {
	
	private EntityManager em;

	public Queries(EntityManager em) {
		this.em = em;
	}

	public void UserWithMembership() {
		 
		
		 Query query = em.createNamedQuery("UserWithMembership");
//		 query.setParameter("username", "linuxuser");
//		 query.setParameter("num", new Long(0));
//		 List<User> uList = (List<User>) query.getResultList();
//		 if(uList != null){
//			 if(!uList.isEmpty()){ 
//				 for(User u : uList){
//					 System.out.println("PersonID: "+u.getId());
//					 System.out.println("Username : "+u.getUsername());
//				 }
//			 }
//		 }else{
//			 System.out.println("Result null or empty");
//		 }
		  query.setParameter("gridname", "gridrenamed3");
		 Long int1 = (Long) query.getSingleResult();
		
		 System.out.println(""+ int1);
		
	}

	public void MostActiveUser() {
		// TODO Auto-generated method stub
		
	}
	
	

}
