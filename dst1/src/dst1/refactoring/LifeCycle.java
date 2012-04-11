package dst1.refactoring;

import java.util.List;

import javax.persistence.EntityManager;

import dst1.model.Job;
import dst1.model.User;

public class LifeCycle {

	private EntityManager em;

	public LifeCycle(EntityManager em) {
		this.em = em;
		System.out.println("##################LIFECYCLE####################");
		em.getTransaction().begin();
		User u = em.find(User.class, new Long(1));
		//States: new, managed, detached, removed
		
		Job j = new Job();
		//State: NEW
		j.setUser(u);
		j.setPaid(true);
		em.persist(j);
		em.getTransaction().commit();
		//State: managed;
		
		em.getTransaction().begin();
		j = em.find(Job.class, new Long(7));
		u = em.find(User.class, new Long(1));
		//State: managed
		//dereference user
		j.setUser(null);
		
		em.remove(j);
		em.getTransaction().commit();
		//State: Removed
		em.close();
		//State: Detached
		
		
		
	}

	
	
}
