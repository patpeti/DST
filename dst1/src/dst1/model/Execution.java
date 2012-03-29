package dst1.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import dst1.model.enums.JobStatus;

@Entity
public class Execution {

	@Id
	@GeneratedValue
	private Long id;
	private Date start;
	private Date end;
	@Enumerated
	private JobStatus status;
	@OneToOne
	private Job job;
	@ManyToMany
	@JoinTable(name="computer_execution", joinColumns = @JoinColumn(name = "execution_id"),
										  inverseJoinColumns = @JoinColumn(name = "computer_id"))
	private Collection<Computer> computers;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Collection<Computer> getComputers() {
		return computers;
	}
	public void setComputers(Collection<Computer> computers) {
		this.computers = computers;
	}
	
	
	
}
