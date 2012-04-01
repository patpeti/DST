package dst1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Membership {


	@Id
	@GeneratedValue
	private Long id;
	private Date registration;
	private Double discount;
	@ManyToOne
	private User user;
	@ManyToOne
	private Grid grid;
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	

	public Date getRegistration() {
		return registration;
	}
	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		user.getMembership().add(this);
		this.user = user;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		grid.getMembership().add(this);
		this.grid = grid;
	}
	
	

	
}
