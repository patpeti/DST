package dst1.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cluster {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	private Date lastService;
	private Date nextService;
	
	@ManyToMany
	@JoinTable(name="cluster_connection", joinColumns = @JoinColumn(name = "clusterChild_id"),
										  inverseJoinColumns = @JoinColumn(name = "parentCluster_id"))
	private Collection<Cluster> childClusters;
	
	@ManyToOne
	@JoinColumn(name = "admin_fk")
	private Admin admin;
	
	@ManyToOne
	@JoinColumn(name = "grid_fk")
	private Grid grid;
	
	@OneToMany
	private Collection<Computer> computers;
	
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

	public Date getLastService() {
		return lastService;
	}

	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	public Date getNextService() {
		return nextService;
	}

	public void setNextService(Date nextService) {
		this.nextService = nextService;
	}

	public Collection<Cluster> getChildClusters() {
		return childClusters;
	}

	public void setChildClusters(Collection<Cluster> childClusters) {
		this.childClusters = childClusters;
	}

	
	public Admin getAdmin() {
		return admin;
	}

	
	public Grid getGrid() {
		return grid;
	}



	public Collection<Computer> getComputers() {
		return computers;
	}

	public void setComputers(Collection<Computer> computers) {
		this.computers = computers;
	}
	
	
	
}
