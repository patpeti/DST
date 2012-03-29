package dst1.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
//This here is redundant but whatever...
@AssociationOverrides({
	@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
	@AssociationOverride(name = "id.grid", joinColumns = @JoinColumn(name = "grid_id"))
})
public class Membership {

	@EmbeddedId
	private MembershipId id = new MembershipId();
	private Date registration;
	private Double discount;
	
	/*********************************************      GETTERS - SETTERS           *************************************************/
	
	
	public MembershipId getId() {
		return id;
	}
	public void setId(MembershipId id) {
		this.id = id;
	}
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
	
	

	
}
