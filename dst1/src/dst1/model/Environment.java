package dst1.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
public class Environment {

	
	@Id
	@GeneratedValue
	private Long id;
	private String workflow;
	//to preserve order: 
	//Set  --> SortedSet 
	//List --> LinkedList
	//Map  --> SortedMap
	@ElementCollection
	@CollectionTable(name="environment_parameters", joinColumns=@JoinColumn(name="environment_id"))
	@Column(name="parameters")
	private List<String> parameters;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public List<String> getParameters() {
		return parameters;
	}
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	
	
	
	
}
