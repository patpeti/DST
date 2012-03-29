package dst1.model;

import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grid {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	private String location;
	private BigInteger costsPerCPUMinute;
	@OneToMany
	private Collection<Cluster> clusters;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigInteger getCostsPerCPUMinute() {
		return costsPerCPUMinute;
	}
	public void setCostsPerCPUMinute(BigInteger costsPerCPUMinute) {
		this.costsPerCPUMinute = costsPerCPUMinute;
	}
	public Collection<Cluster> getClusters() {
		return clusters;
	}
	public void setClusters(Collection<Cluster> clusters) {
		this.clusters = clusters;
	}
	
	
	
}
