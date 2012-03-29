package dst1.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MembershipId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User user;
	@ManyToOne
	private Grid grid;
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public int hashCode() {
		 int result;
	        result = (user != null ? user.hashCode() : 0);
	        result = 31 * result + (grid != null ? grid.hashCode() : 0);
	        
	        return result;
	}
	@Override
	public boolean equals(Object o) {
		 if (this == o) {
	        	return true;
	        }
	        
	        if (o == null || getClass() != o.getClass()) {
	        	return false;
	        }
	 
	        MembershipId that = (MembershipId) o;
	 
	        if (user != null ? !user.equals(that.user) : that.user != null) {
	        	return false;
	        }
	        
	        if (grid != null ? !grid.equals(that.grid) : that.grid != null) {
	            return false;
	        }
	 
	        return true;
	}
	
	
	
}
