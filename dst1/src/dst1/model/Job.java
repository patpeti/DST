package dst1.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Job {

	@Id
	@GeneratedValue
	private Long id;

	
	private boolean isPaid;
	@OneToOne
	private Environment environment;
	@ManyToOne
	@JoinColumns(@JoinColumn(name = "user_id"))
	private User user;
	
	@OneToOne
	private Execution execution;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Transient
	public Integer getNumCPUs() {
		Execution ex = this.getExecution();
		Collection<Computer> computers = ex.getComputers();
		Integer sum = 0;
		for(Computer c : computers){
			sum += c.getCpus();
		}
		return sum;
	}

	
	@Transient
	public Integer getExecutionTime() {
		Execution ex = this.getExecution();
		Long duration = ex.getEnd().getTime() - ex.getStart().getTime();
		Integer i = duration.intValue();
		return  i;
	}


	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}
	
}
