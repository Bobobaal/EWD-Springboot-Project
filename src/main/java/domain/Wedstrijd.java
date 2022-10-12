package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Een wedstrijd
@Entity
public class Wedstrijd implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wId; //unieke sleutel

    private String[] landen; //2 landen van de wedstrijd

    private Calendar dagEnUur; //dag & tijd van de wedstrijd

    public Wedstrijd() {}

    public Wedstrijd(String[] landen, GregorianCalendar dagEnUur) {
        this.landen = landen;
        this.dagEnUur = dagEnUur;
    }
    
    public Wedstrijd(int id, String[] landen, GregorianCalendar dagEnUur) {
    	this.wId = id;
    	this.landen = landen;
    	this.dagEnUur = dagEnUur;
    }

    public int getwId() {
        return wId;
    }

    public String[] getLanden() {
        return landen;
    }

    public Calendar getDagEnUur() {
        return dagEnUur;
    }
    
    //Niet gebruikt vanwege resourcebundle die de message vertaald kan weergeven
    //En ga niet de resourcebundle mee in de databank steken.
    @Override
    public String toString(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
    	SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
    	dateFormat.setCalendar(dagEnUur);
    	timeFormat.setCalendar(dagEnUur);
    	return String.format("%s vs %s op %s om %s", landen[0], landen[1], dateFormat.format(dagEnUur.getTime()), timeFormat.format(dagEnUur.getTime()));
    }
}
