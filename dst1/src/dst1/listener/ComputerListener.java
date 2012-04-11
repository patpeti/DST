package dst1.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import dst1.model.Computer;

public class ComputerListener {

	@PrePersist
	public void setCreation(Computer c){
		c.setCreation(new Date());
		c.setLastUpdate(new Date());
	}
	
	@PreUpdate
	public void setLastUpdate(Computer c){
		c.setLastUpdate(new Date());
	}
	
	
	
}
