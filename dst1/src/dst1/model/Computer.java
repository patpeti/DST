package dst1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Computer implements Serializable{
	
	private Long id;
	private String name;
	private Integer cpus;
	private String location;
	private Date creation;
	private Date lastUpdate;
	private Cluster cluster;
	private List<Execution> executions = new ArrayList<Execution>();
	
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
	public List<Execution> getExecutions() {
		return executions;
	}
	public void setExecutions(List<Execution> executions1) {
		if(executions1 == null){
			this.executions = new ArrayList<Execution>();
		}else{
			for(Execution e : executions1){
				e.getComputers().add(this);
			}
			this.executions = executions1;
		}
	}
	
	
	
}
