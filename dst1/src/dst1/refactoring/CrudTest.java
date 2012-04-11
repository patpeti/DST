package dst1.refactoring;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import dst1.model.Address;
import dst1.model.Admin;
import dst1.model.Cluster;
import dst1.model.Computer;
import dst1.model.Environment;
import dst1.model.Execution;
import dst1.model.Grid;
import dst1.model.Job;
import dst1.model.Membership;
import dst1.model.Person;
import dst1.model.User;
import dst1.model.enums.JobStatus;

public class CrudTest {
	
	private EntityManager em;

	public CrudTest(EntityManager em) {
		this.em = em;
	}
	
	public void createTest(){
		
		em.getTransaction().begin();
		
		//ENVIRONMENTS
		Environment e1 = new Environment();	
		List<String> params = new LinkedList<String>();
		params.add("1024MB");
		params.add("debug");
		params.add("log4J");
		params.add("showErrors");
		e1.setWorkflow("linux-worklow");
		e1.setParameters(params);
		em.persist(e1);
		
		Environment e2 = new Environment();	
		List<String> params2 = new LinkedList<String>();
		params2.add("a");
		params2.add("b");
		params2.add("c");
		params2.add("d");
		e2.setWorkflow("windows-worklow");
		e2.setParameters(params2);
		em.persist(e2);
		
		Environment e3 = new Environment();	
		List<String> params3 = new LinkedList<String>();
		params3.add("clean");
		params3.add("run");
		params3.add("debug");
		params3.add("a");
		e3.setWorkflow("mac-worklow");
		e3.setParameters(params3);
		em.persist(e3);
		
		//ADDRESSES (embedded)
		
		Address a1 = new Address();
		a1.setCity("Vienna");
		a1.setZipCode("A-1200");
		a1.setStreet("Lorenz Müller gasse");
		
		Address a2 = new Address();
		a2.setCity("London");
		a2.setZipCode("L-1100");
		a2.setStreet("Main street 3");
		
		Address a3 = new Address();
		a3.setCity("Budapest");
		a3.setZipCode("H-8100");
		a3.setStreet("Szabadság utca 5");
		
		Address a4 = new Address();
		a4.setCity("Vienna");
		a4.setZipCode("A-1020");
		a4.setStreet("NestroyPlatz 1A");
		
		Address a5 = new Address();
		a5.setCity("London");
		a5.setZipCode("L-1100");
		a5.setStreet("Liberty Street 4");
		
		Address a6 = new Address();
		a6.setCity("Budapest");
		a6.setZipCode("HU-9000");
		a6.setStreet("Angyal utca 134");
		
		//USERS
		/*
		 *  Check uniqueness of username - OK (Exception thrown)
		 *  Check uniqueness of accounNo-Bankcode - OK (Exception thrown)
		 *  
		 *  Add Jobs - OK 
		 *  
		 */
		String md5 = "";
		try {
		String toEnc = "password"; // Value to encrypt
		MessageDigest mdEnc = MessageDigest.getInstance("MD5");
		mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
		md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		User u1 = new User();
		u1.setUsername("patonaipeter");
		//byte [] b1 = "d41d8cd98f00b204e9800998ecf8427e".getBytes();
		u1.setPassword(md5.getBytes());
		u1.setAddress(a1);
		u1.setFirstName("patonai");
		u1.setLastName("peter");
		u1.setAccountNo("00000");
		u1.setBankCode("00000");
		em.persist(u1);
		
		User u2 = new User();
		u2.setUsername("linuxuser");
		u2.setPassword(md5.getBytes());
		u2.setAddress(a2);
		u2.setFirstName("patonai");
		u2.setLastName("peter");
		u2.setAccountNo("00001");
		u2.setBankCode("00000");
		em.persist(u2);
		
		User u3 = new User();
		u3.setUsername("windowsuser");
		u3.setPassword(md5.getBytes());
		u3.setAddress(a3);
		u3.setFirstName("patonai");
		u3.setLastName("peter");
		u3.setAccountNo("00002");
		u3.setBankCode("00000");
		em.persist(u3);
		
		//ADMINS
		/*
		 * Check Person-Admin, Person-User association - OK
		 * 
		 * 
		 * 
		 */
		
		Admin ad1 = new Admin();
		ad1.setAddress(a4);
		ad1.setFirstName("adam");
		ad1.setLastName("weiss");
		em.persist(ad1);
		
		Admin ad2 = new Admin();
		ad2.setAddress(a5);
		ad2.setFirstName("adam");
		ad2.setLastName("weiss");
		em.persist(ad2);
		
		Admin ad3 = new Admin();
		ad3.setAddress(a6);
		ad3.setFirstName("adam");
		ad3.setLastName("weiss");
		em.persist(ad3);
		
		//JOBS + EXECUTIONS
		/*
		 *  Check getNumCpu OK
		 *  Check getExecutionTime OK
		 */
		
		Job j1 = new Job();
		j1.setEnvironment(e1);
		j1.setPaid(true);
		j1.setUser(u1);
		
		Job j2 = new Job();
		j2.setEnvironment(e2);
		j2.setPaid(true);
		j2.setUser(u2);
		
		Job j3 = new Job();
		j3.setEnvironment(e3);
		j3.setPaid(false);
		j3.setUser(u3);
		
		Job j4 = new Job();
		j4.setEnvironment(e1);
		j4.setPaid(false);
		j4.setUser(u3);
		
		Job j5 = new Job();
		j5.setEnvironment(e2);
		j5.setPaid(true);
		j5.setUser(u2);
		
		Job j6 = new Job();
		j6.setEnvironment(e3);
		j6.setPaid(false);
		j6.setUser(u1);
		
		
		
		
		/*
		 *  Check Job-Execution association - OK (Execution-Job are connected trough Job_id in Execution Table)
		 *	TODO set reasonable dates ok
		 */
		
		Execution ex1 = new Execution();
		ex1.setStart(new Date());
		ex1.setEnd(new Date(new Date().getTime()+10000));
		ex1.setJob(j1);
		ex1.setStatus(JobStatus.SCHEDULED);
		
		Execution ex2 = new Execution();
		ex2.setStart(new Date());
		ex2.setEnd(new Date(new Date().getTime()+20000));
		ex2.setJob(j2);
		ex2.setStatus(JobStatus.FINISHED);
		
		Execution ex3 = new Execution();
		ex3.setStart(new Date());
		ex3.setEnd(new Date(new Date().getTime()+30000));
		ex3.setJob(j3);
		ex3.setStatus(JobStatus.RUNNING);
		
		Execution ex4 = new Execution();
		ex4.setStart(new Date());
		ex4.setEnd(new Date(new Date().getTime()+40000));
		ex4.setJob(j4);
		ex4.setStatus(JobStatus.RUNNING);
		
		Execution ex5 = new Execution();
		ex5.setStart(new Date());
		ex5.setEnd(new Date(new Date().getTime()+50000));
		ex5.setJob(j5);
		ex5.setStatus(JobStatus.FINISHED);
		
		int year = 2011;
	    int month = 12;
	    int day = 12;

	    String date = year + "/" + month + "/" + day;
	    java.util.Date utilDate = null;

	    try {
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	      utilDate = formatter.parse(date);
	      //System.out.println("utilDate:" + utilDate);
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
	    
	    year = 2011;
	    month = 12;
	    day = 13;
	    java.util.Date utilDate2 = null;
	    
	    try {
		      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		      utilDate2 = formatter.parse(date);
		      //System.out.println("utilDate:" + utilDate);
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
	    
		Execution ex6 = new Execution();
		ex6.setStart(utilDate);
		ex6.setEnd(utilDate2);
		ex6.setJob(j6);
		ex6.setStatus(JobStatus.FINISHED);
		
//		j1.setExecution(ex1);
//		j2.setExecution(ex2);
//		j3.setExecution(ex3);
//		j4.setExecution(ex4);
//		j5.setExecution(ex5);
//		j6.setExecution(ex6);
		
		em.persist(ex1);
		em.persist(ex2);
		em.persist(ex3);
		em.persist(ex4);
		em.persist(ex5);
		em.persist(ex6);		
		em.persist(j1);
		em.persist(j2);
		em.persist(j3);
		em.persist(j4);
		em.persist(j5);
		em.persist(j6);
	
		
		//add jobs to users
		
		List<Job> jobs1 = new ArrayList<Job>();
		jobs1.add(j1);
		jobs1.add(j2);
		
		u1.setJobs(jobs1);
		
		List<Job> jobs2 = new ArrayList<Job>();
		jobs2.add(j3);
		jobs2.add(j4);
		
		u2.setJobs(jobs2);
		
		List<Job> jobs3 = new ArrayList<Job>();
		jobs3.add(j5);
		jobs3.add(j6);
		
		u3.setJobs(jobs3);
		
		
		//Grids
		/*
		 *  TODO add Cluster -OK
		 *  
		 */
		
		Grid g1 = new Grid();
		g1.setCostsPerCPUMinute(new BigInteger("1000000000"));
		g1.setLocation("AUT-VIE");
		g1.setName("grid1");
		
		
		Grid g2= new Grid();
		g2.setCostsPerCPUMinute(new BigInteger("1000000000"));
		g2.setLocation("GB-LON");
		g2.setName("grid2");
		
		Grid g3 = new Grid();
		g3.setCostsPerCPUMinute(new BigInteger("1000000000"));
		g3.setLocation("HUN-BUD");
		g3.setName("grid3");
		
		em.persist(g1);
		em.persist(g2);
		em.persist(g3);
		
		//Membership
		
		
		Membership m1 = new Membership();
		m1.setDiscount(new Double(10));
		m1.setRegistration(new Date());
		m1.setGrid(g1);
		m1.setUser(u1);

		
		em.persist(m1);
		
		
		Membership m2 = new Membership();
		m2.setDiscount(new Double(20));
		m2.setRegistration(new Date());
		m2.setGrid(g1);
		m2.setUser(u2);

		
		em.persist(m2);
		
		Membership m3 = new Membership();
		m3.setDiscount(new Double(10));
		m3.setRegistration(new Date());
		m3.setGrid(g1);
		m3.setUser(u3);

		
		em.persist(m3);
		
		Membership m4 = new Membership();
		m4.setDiscount(new Double(10));
		m4.setRegistration(new Date());
		m4.setGrid(g2);
		m4.setUser(u1);

		
		em.persist(m4);
		
		Membership m5 = new Membership();
		m5.setDiscount(new Double(10));
		m5.setRegistration(new Date());
		m5.setGrid(g2);
		m5.setUser(u2);
		
		em.persist(m5);
		
		Membership m6 = new Membership();
		m6.setDiscount(new Double(10));
		m6.setRegistration(new Date());
		m6.setGrid(g3);
		m6.setUser(u3);

		
		em.persist(m6);
		
		//Computers
		/*
		 * TODO Add CLusters OK
		 */
		
		Computer c1 = new Computer();
		c1.setCpus(new Integer(4));
		c1.setCreation(new Date(new Date().getTime()-100000));
		c1.setLastUpdate(new Date(new Date().getTime()-100000));
		c1.setLocation("AUT-VIE");
		c1.setName("c1");
		
		em.persist(c1);
		
		Computer c2 = new Computer();
		c2.setCpus(new Integer(16));
		c2.setCreation(new Date(new Date().getTime()-100000));
		c2.setLastUpdate(new Date(new Date().getTime()-100000));
		c2.setLocation("AUT-VIE");
		c2.setName("c2");
		
		em.persist(c2);
		
		Computer c3 = new Computer();
		c3.setCpus(new Integer(32));
		c3.setCreation(new Date(new Date().getTime()-100000));
		c3.setLastUpdate(new Date(new Date().getTime()-100000));
		c3.setLocation("GB-LON");
		c3.setName("c3");
		
		em.persist(c3);
		
		Computer c4 = new Computer();
		c4.setCpus(new Integer(32));
		c4.setCreation(new Date(new Date().getTime()-100000));
		c4.setLastUpdate(new Date(new Date().getTime()-100000));
		c4.setLocation("GB-LON");
		c4.setName("c4");
		
		em.persist(c4);
		
		Computer c5 = new Computer();
		c5.setCpus(new Integer(32));
		c5.setCreation(new Date(new Date().getTime()-100000));
		c5.setLastUpdate(new Date(new Date().getTime()-100000));
		c5.setLocation("HU-BUD");
		c5.setName("c5");
		
		em.persist(c5);
		
		Computer c6 = new Computer();
		c6.setCpus(new Integer(32));
		c6.setCreation(new Date(new Date().getTime()-100000));
		c6.setLastUpdate(new Date(new Date().getTime()-100000));
		c6.setLocation("HU-BUD");
		c6.setName("c6");
		
		em.persist(c6);
		
		Computer c7 = new Computer();
		c7.setCpus(new Integer(32));
		c7.setCreation(new Date(new Date().getTime()-100000));
		c7.setLastUpdate(new Date(new Date().getTime()-100000));
		c7.setLocation("HU-BUD");
		c7.setName("c7");
		
		em.persist(c7);
		
		Computer c8 = new Computer();
		c8.setCpus(new Integer(32));
		c8.setCreation(new Date(new Date().getTime()-100000));
		c8.setLastUpdate(new Date(new Date().getTime()+100000));
		c8.setLocation("HU-BUD");
		c8.setName("c8");
		
		em.persist(c8);
		
		//executions-computers connection
		
		List<Computer> comps1 = new ArrayList<Computer>();
		comps1.add(c1);
		comps1.add(c3);
		comps1.add(c4);
		
		ex1.setComputers(comps1);
		
		List<Computer> comps2 = new ArrayList<Computer>();
		comps2.add(c2);
		ex2.setComputers(comps2);
		
		List<Computer> comps3 = new ArrayList<Computer>();
		comps3.add(c5);
		ex3.setComputers(comps3);
		
		List<Computer> comps4 = new ArrayList<Computer>();
		comps4.add(c6);
		ex4.setComputers(comps4);
		
		List<Computer> comps5 = new ArrayList<Computer>();
		comps5.add(c7);
		ex5.setComputers(comps5);
		
		List<Computer> comps6 = new ArrayList<Computer>();
		comps6.add(c8);
		ex6.setComputers(comps6);
		
		//Add Clusters
		/**
		 * check name uniqueness - OK
		 */
		
		Cluster cl1 = new Cluster();
		cl1.setName("Vienna-cluster");
		cl1.setNextService(new Date());
		cl1.setLastService(new Date());
		
		em.persist(cl1);
		
		Cluster cl2 = new Cluster();
		cl2.setName("London-cluster");
		cl2.setNextService(new Date());
		cl2.setLastService(new Date());
		
		em.persist(cl2);
		
		Cluster cl3 = new Cluster();
		cl3.setName("Budapest-cluster");
		cl3.setNextService(new Date());
		cl3.setLastService(new Date());
		
		em.persist(cl3);
		
		Cluster cl4 = new Cluster();
		cl4.setName("Budapest2-cluster");
		cl4.setNextService(new Date());
		cl4.setLastService(new Date());
		
		em.persist(cl4);
		
		Cluster cl5 = new Cluster();
		cl5.setName("Vienna2-cluster");
		cl5.setNextService(new Date());
		cl5.setLastService(new Date());
		
		em.persist(cl5);
		
		//connect grid-clusters (1-n)
		
		List<Cluster> clusters1 = new ArrayList<Cluster>();
		clusters1.add(cl1);
		clusters1.add(cl2);
		g1.setClusters(clusters1);
		
		List<Cluster> clusters2 = new ArrayList<Cluster>();
		clusters2.add(cl3);
	
		g2.setClusters(clusters2);
		
		List<Cluster> clusters3 = new ArrayList<Cluster>();
		clusters3.add(cl4);
		clusters3.add(cl5);
		g3.setClusters(clusters3);
		
		
		
		//connect admin-cluster (1-n)
		
		ad1.setClusters(clusters1);
		ad2.setClusters(clusters2);
		ad3.setClusters(clusters3);
		
		
		//connect cluster-computers (1-n)
		
		cl1.setComputers(comps1);
		cl2.setComputers(comps2);
		cl3.setComputers(comps3);
		cl4.setComputers(comps4);
		cl5.setComputers(comps5);
		
		
		//connect cluster-cluster (n-n)
		
		cl1.setChildClusters(clusters3);
		
		
		em.getTransaction().commit();
		//em.close();
		
//		Computer returnComputer = em.find(Computer.class,new Long(1));
//		System.out.println(""+returnComputer.getExecutions().size());
//		Computer returnComputer2 = em.find(Computer.class,new Long(2));
//		System.out.println(""+returnComputer2.getExecutions().size());
		
	}
	
	public void retrieveTest() {
		em.getTransaction().begin();
		Admin retrievedAdmin = em.find(Admin.class, new Long(4));
		System.out.println(" Admin name: "+retrievedAdmin.getFirstName() + retrievedAdmin.getLastName());
		System.out.println("Admin adress: "+retrievedAdmin.getAddress().getCity() + retrievedAdmin.getAddress().getStreet() + retrievedAdmin.getAddress().getZipCode());
		
		List<Cluster> retrievedClusters = retrievedAdmin.getClusters();
		for(Cluster c : retrievedClusters){
			System.out.println(" Clusters from admin: ");
			System.out.println(" Cluster name: "+c.getName()+ " admin name:" + c.getAdmin().getFirstName());
			
			System.out.println(" Grid of culster: ");
			System.out.println("  " + c.getGrid().getName());
			
			System.out.println(" Users of Grid: ");
			for(Membership m : c.getGrid().getMembership()){
				
				System.out.println(" User name: " + m.getUser().getLastName());
				
			}
			
			System.out.println(" Computers from culster: ");
			
			for(Computer retrievedComputer : c.getComputers()){
				System.out.println(" computer location " + retrievedComputer.getLocation());
				System.out.println(" computer name " + retrievedComputer.getName());
				
				System.out.println(" Executions on computer: ");
				for(Execution e : retrievedComputer.getExecutions()){
					System.out.println(" Execution startdate: " + e.getStart());
					
					
					System.out.println(" Job of Execution: id: " + e.getJob().getId());
					System.out.println(" Job of Execution: exTime: " + e.getJob().getExecutionTime());
					System.out.println(" Job of Execution: numCPUS " + e.getJob().getNumCPUs());
				}
				
			}
			
		}
		em.getTransaction().commit();
	}
	
	public void updateTest(){
		
		Person retrievedPerson = em.find(Person.class, new Long(1));
		retrievedPerson.setLastName("Oliver");
		
		Grid retrievedGrid = em.find(Grid.class, new Long(1));
		retrievedGrid.setName("gridrenamed1");
		
		Computer retrievedComputer = em.find(Computer.class, new Long(2));
		retrievedComputer.setName("renamedC3");
		
		Cluster retrievedCluster = em.find(Cluster.class, new Long(2));
		retrievedCluster.setName("renamedCluster-London");
		
		Execution retrievedExecution = em.find(Execution.class, new Long(2));
		retrievedExecution.setStatus(JobStatus.FINISHED);
		
		Environment retrievedEnvironment = em.find(Environment.class, new Long(1));
		retrievedEnvironment.setWorkflow("Blbalrenamed");
		
		Job retrievedJob = em.find(Job.class, new Long(1));
		retrievedJob.setPaid(true);
		
		User retrievedUser = em.find(User.class, new Long(1));
		retrievedUser.setUsername("renameduser");
			
		//necessary?
		em.getTransaction().begin();
		em.persist(retrievedCluster);
		em.persist(retrievedComputer);
		em.persist(retrievedPerson);
		em.persist(retrievedGrid);
		em.persist(retrievedExecution);
		em.persist(retrievedEnvironment);
		em.persist(retrievedJob);
		em.persist(retrievedUser);
		em.getTransaction().commit();
		
		retrieveTest();
	}
	
	
	public void deleteTest(){
		

		
		
		//remove JOB - OK

		em.getTransaction().begin();
		Job retrievedJob = em.find(Job.class, new Long(1));

		retrievedJob.getExecution().setJob(null);
		User u = retrievedJob.getUser();
		List<Job> joblist = u.getJobs();
		joblist.remove(retrievedJob);
		u.setJobs(null);
		u.setJobs(joblist);
		
	
		em.remove(retrievedJob);
		em.getTransaction().commit();
		
		//remove admin ok
		
		em.getTransaction().begin();
		Admin retrievedAdmin = em.find(Admin.class, new Long(4));
		for( Cluster cl :retrievedAdmin.getClusters()){
			cl.setAdmin(null);
		}
		
		
	
		em.remove(retrievedAdmin);
		em.getTransaction().commit();
		
		//remove user + membership OK
		
		
		User retrievedUser = em.find(User.class, new Long(1));
		List<Job> tempJobs = new ArrayList<Job>();
		tempJobs.addAll(retrievedUser.getJobs());
		for(Job iJob : tempJobs){
			System.out.println("job id: " +iJob.getId());
			System.out.println("user id for job: " +iJob.getUser().getId());
			iJob.setUser(null);
		}
		retrievedUser.setJobs(null);
		
		List<Membership> memList = retrievedUser.getMembership();
		for(Membership mem : memList){
			Grid grid = mem.getGrid();
			grid.getMembership().remove(mem);
		
		}
		em.getTransaction().begin();
		for(Membership mem : memList){
			em.remove(mem);
		}
		
		
		em.remove(retrievedUser);
		em.getTransaction().commit();
		
		//////////////////////////////////////////////
		
		
		
		//remove Execution + COmputer 
		
		em.getTransaction().begin();
		Execution retrievedExecution = em.find(Execution.class, new Long(1));
		retrievedExecution.setJob(null);

		Computer retrievedComputer1 = em.find(Computer.class, new Long(1));
		Computer retrievedComputer2 = em.find(Computer.class, new Long(3));
		Computer retrievedComputer3 = em.find(Computer.class, new Long(4));
		Cluster retrievedCluster1 = em.find(Cluster.class, new Long(1));
		retrievedCluster1.setComputers(new ArrayList<Computer>());
		

		em.remove(retrievedComputer1);
		em.remove(retrievedComputer2);
		em.remove(retrievedComputer3);
		
		em.remove(retrievedExecution);
		em.getTransaction().commit();
		
		//remove cluster
		
		em.getTransaction().begin();
		Cluster retrievedCluster2 = em.find(Cluster.class, new Long(1));
		
		Cluster retrievedCluster4 = em.find(Cluster.class, new Long(4));
		Cluster retrievedCluster5 = em.find(Cluster.class, new Long(5));
		retrievedCluster4.setParentCluster(new ArrayList<Cluster>());
		retrievedCluster5.setParentCluster(new ArrayList<Cluster>());
		retrievedCluster2.setChildClusters(new ArrayList<Cluster>());
		
		
		retrievedCluster2.setGrid(null);
		
		Grid retrievedGrid = em.find(Grid.class, new Long(1));
		List<Cluster> clusterList = retrievedGrid.getClusters();
		clusterList.remove(retrievedCluster2);
		retrievedGrid.setClusters(new ArrayList<Cluster>());
		retrievedGrid.setClusters(clusterList);
		
		
		em.remove(retrievedCluster2);
		em.getTransaction().commit();
		
		
		//remove grid
		em.getTransaction().begin();
		Grid retrGrid = em.find(Grid.class, new Long(2));
		Cluster retrievedCluster3 = em.find(Cluster.class, new Long(3));
		Membership retrievedMembership5 = em.find(Membership.class, new Long(5));
		retrievedCluster3.setGrid(null);
		retrievedMembership5.setGrid(null);
		retrGrid.setClusters(new ArrayList<Cluster>());
		retrGrid.setMembership(new ArrayList<Membership>());
		em.remove(retrGrid);
		em.getTransaction().commit();
		
		
	}

	public void computerUpdate() {
		em.getTransaction().begin();
		Computer c = em.find(Computer.class, new Long(2));
		if(c != null){
			c.setCpus(7);
			em.persist(c);
		}
		em.getTransaction().commit();
		
	}



}
