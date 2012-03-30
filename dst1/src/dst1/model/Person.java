package dst1.model;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	
	@Embedded
	private Address address;

	
}
