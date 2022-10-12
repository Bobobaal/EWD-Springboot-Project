package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import sorting.SortyByWedstrijdId;

@NamedQueries({
	@NamedQuery(name = "Stadium.geefStadiumViaNaam", query = "SELECT s FROM Stadium s WHERE s.naam like concat('%', :x, '%')")
})

@Entity
public class Stadium implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stadiumId;
	
	@Column(unique = true)
	private String naam;
	
	@OneToMany
	private List<WedstrijdTicket> wedstrijden;
	
	public Stadium(String naam, List<WedstrijdTicket> wedstrijden) {
		this.naam = naam;
		this.wedstrijden = new ArrayList<>(wedstrijden);
	}

	public Stadium() {}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public void setWedstrijden(List<WedstrijdTicket> wedstrijden) {
		this.wedstrijden = new ArrayList<>(wedstrijden);
	}
	
	public String getNaam() {
		return naam;
	}
	
	public int getStadiumId() {
		return stadiumId;
	}
	
	public List<WedstrijdTicket> getWedstrijden(){
		return wedstrijden.stream().sorted(new SortyByWedstrijdId()).toList();
	}

	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stadium other = (Stadium) obj;
		return Objects.equals(naam, other.naam);
	}
}
