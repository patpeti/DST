package dst1.validator;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import dst1.model.Computer;

public class Validierung {

	public Validierung() {
		System.out.println("################VALIDIERUNG##################");
		
		//not ok (all constraints violated)
		Computer c = new Computer();
		c.setCpus(3);
		c.setCreation(new Date(new Date().getTime()+100000));
		c.setLastUpdate(new Date(new Date().getTime()+100000));
		c.setLocation("df");
		c.setName("d");
		
		validieren(c);
		
		//ok (some violations)
		Computer c2 = new Computer();
		c.setCpus(3);
		c.setCreation(new Date(new Date().getTime()-100000));
		c.setLastUpdate(new Date(new Date().getTime()-100000));
		c.setLocation("df");
		c.setName("d");
		
		validieren(c2);
		
		// ok (no violations)
		Computer c3 = new Computer();
		c.setCpus(6);
		c.setCreation(new Date(new Date().getTime()-100000));
		c.setLastUpdate(new Date(new Date().getTime()-100000));
		c.setLocation("AUT-VIE@1040");
		c.setName("computername");
		
		validieren(c3);
	}
	
	public void validieren(Computer c){
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Computer>> constarintViolations = validator.validate(c);
		if(constarintViolations.size() > 4){
			System.out.println("Computer Validation Failed");
			for(ConstraintViolation<Computer> violation: constarintViolations){
				System.out.println(""+ violation.getMessage());
			}
		}else{
			System.out.println("Validierung ok");
		}
	}

	
	
	
}
