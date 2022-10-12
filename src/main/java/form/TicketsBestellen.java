package form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TicketsBestellen {
	
	@NotEmpty(message="{validation.all.NotEmpty.message}")
	@Email(message="{validation.email.NotValid.message}")
	private String email;
	
	@NotNull(message="{validation.all.NotEmpty.message}")
	@Min(value=1, message="{validation.aantalTickets.min.message}")
	@Max(value=25, message="{validation.aantalTickets.max.message}")
	private int aantalTickets = 1;
	
	@NotNull(message="{validation.all.NotEmpty.message}")
	private int voetbalCode1 = 10;
	
	@NotNull(message="{validation.all.NotEmpty.message}")
	private int voetbalCode2 = 20;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAantalTickets() {
		return aantalTickets;
	}

	public void setAantalTickets(int aantalTickets) {
		this.aantalTickets = aantalTickets;
	}

	public int getVoetbalCode1() {
		return voetbalCode1;
	}

	public void setVoetbalCode1(int voetbalCode1) {
		this.voetbalCode1 = voetbalCode1;
	}

	public int getVoetbalCode2() {
		return voetbalCode2;
	}

	public void setVoetbalCode2(int voetbalCode2) {
		this.voetbalCode2 = voetbalCode2;
	}
	
	
}
