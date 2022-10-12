package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import form.TicketsBestellen;

public class TicketsBestellenValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> klass) {
		return TicketsBestellen.class.isAssignableFrom(klass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		TicketsBestellen tb = (TicketsBestellen) target;

		int voetbalCode1 = tb.getVoetbalCode1();
		int voetbalCode2 = tb.getVoetbalCode2();
		
		if(voetbalCode2 < voetbalCode1) {
			errors.rejectValue("voetbalCode1", "validation.voetbalcodes.groterdan", "voetbalCode1 moet kleiner zijn dan voetbalCode2");
		}
	}
}
