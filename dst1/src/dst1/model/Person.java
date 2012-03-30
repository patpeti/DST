package dst1.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	
	@Embedded
	private Address address;

	
}
