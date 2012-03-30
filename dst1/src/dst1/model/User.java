package dst1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"accountNo","bankCode"})})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Person{

//	@Id
//	@GeneratedValue
//	private Long id;
//
//	private String firstName;
//	private String lastName;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(columnDefinition="CHAR(32)")
	private byte[] password;
//	@Embedded
//	private Address address;

	
	@OneToMany(mappedBy = "user")
	private List<Job> jobs;
	@Column(name = "accountNo")
	private String accountNo;
	@Column(name = "bankCode")
	private String bankCode;
	
	
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	
}
