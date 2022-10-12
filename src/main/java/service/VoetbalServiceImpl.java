package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;

public class VoetbalServiceImpl implements VoetbalService{

    private List<String> stadiumList = new ArrayList<>();

    //zonder databank
    //--------------------
    //mapWedstrijdenByStadium, per stadium, een lijst van wedstrijden
    private final Map<String, List<WedstrijdTicket>> mapWedstrijdenByStadium = new HashMap<>();

    //mapWedstrijdById, per id een wedstrijdTicket
    private final Map<String, WedstrijdTicket> mapWedstrijdById = new HashMap<>();

    public VoetbalServiceImpl() {
        //zonder databank
        stadiumList = new ArrayList<>(Arrays.asList(new String[]{"Al Bayt Stadium", "Al Thumama Stadium"}));

        mapWedstrijdById.put("1", new WedstrijdTicket(new Wedstrijd(1, new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0)), 35));
        mapWedstrijdById.put("2", new WedstrijdTicket(new Wedstrijd(2, new String[]{"Brazil","Switzerland"}, new GregorianCalendar(2021, 10, 27, 18, 0)), 21));
        mapWedstrijdById.put("3", new WedstrijdTicket(new Wedstrijd(3, new String[]{"Morocco","Croatia"}, new GregorianCalendar(2021, 10, 28, 15, 0)), 5));
        mapWedstrijdById.put("4", new WedstrijdTicket(new Wedstrijd(4, new String[]{"Spain","Germany"}, new GregorianCalendar(2021, 10, 29, 18, 0)), 95));
        mapWedstrijdById.put("5", new WedstrijdTicket(new Wedstrijd(5, new String[]{"France","Denmark"}, new GregorianCalendar(2021, 10, 30, 15, 0)), 45));
        mapWedstrijdById.put("6", new WedstrijdTicket(new Wedstrijd(6, new String[]{"Argentina","Mexico"}, new GregorianCalendar(2021, 10, 30, 18, 0)), 10));
        mapWedstrijdById.put("7", new WedstrijdTicket(new Wedstrijd(7, new String[]{"England","USA"}, new GregorianCalendar(2021, 11, 1, 18, 0)), 22));
        mapWedstrijdById.put("8", new WedstrijdTicket(new Wedstrijd(8, new String[]{"Netherlands","Qatar"}, new GregorianCalendar(2021, 11, 1, 21, 0)), 16));


        mapWedstrijdenByStadium.put(stadiumList.get(0),
                new ArrayList<>(Arrays.asList(new WedstrijdTicket[]{
            mapWedstrijdById.get("1"),
            mapWedstrijdById.get("2"),
            mapWedstrijdById.get("3"),
            mapWedstrijdById.get("6"),
            mapWedstrijdById.get("7")
        })));

        mapWedstrijdenByStadium.put(stadiumList.get(1),
                new ArrayList<>(Arrays.asList(new WedstrijdTicket[]{
            mapWedstrijdById.get("4"),
            mapWedstrijdById.get("5"),
            mapWedstrijdById.get("8")
        })));
        
        //Met Databank
        //Zie Dataloader in de package met de controllers

    }

    public List<String> getStadiumList() {
        return stadiumList;
    }

    //geeft de lijst "tickets per wedstrijden" terug volgens stadium
    public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium) {
        return mapWedstrijdenByStadium.get(stadium);
    }

    //geeft aantal tickets voor een wedstrijd terug volgens id
    public WedstrijdTicket getWedstrijd(String id) {
        return mapWedstrijdById.get(id);
    }

    public int ticketsBestellen(String id, int teBestellen) {
        WedstrijdTicket wedstrijdTicket = mapWedstrijdById.get(id);
        return wedstrijdTicket.ticketsKopen(teBestellen);
    }
}
