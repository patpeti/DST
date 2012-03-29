package dst1.model;

import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	@Embedded
	private Address address;
	@OneToMany
	private Collection<Cluster> clusters;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Collection<Cluster> getClusters() {
		return clusters;
	}
	public void setClusters(Collection<Cluster> clusters) {
		this.clusters = clusters;
	}
	

	
}
