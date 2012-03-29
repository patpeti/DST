package dst1.model;

import java.io.Serializable;
import java.util.Date;

public class Computer implements Serializable{
	
	private Long id;
	private String name;
	private Integer cpus;
	private String location;
	private Date creation;
	private Date lastUpdate;
	private Cluster cluster;
	private Execution execution;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCpus() {
		return cpus;
	}
	public void setCpus(Integer cpus) {
		this.cpus = cpus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Cluster getCluster() {
		return cluster;
	}
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	public Execution getExecution() {
		return execution;
	}
	public void setExecution(Execution execution) {
		this.execution = execution;
	}
	
	
}
