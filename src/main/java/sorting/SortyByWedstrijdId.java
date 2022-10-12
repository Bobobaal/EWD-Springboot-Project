package sorting;

import java.util.Comparator;

import domain.WedstrijdTicket;

public class SortyByWedstrijdId implements Comparator<WedstrijdTicket>{

	@Override
	public int compare(WedstrijdTicket wt1, WedstrijdTicket wt2) {
		return wt1.getWedstrijd().getwId() - wt2.getWedstrijd().getwId();
	}

}
