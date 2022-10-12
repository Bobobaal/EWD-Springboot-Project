package com.springBoot.fifaWorldCup;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.GenericDao;

@Component
public class Dataloader implements ApplicationRunner{
	private GenericDao<Stadium> genericDaoStadium;
	private GenericDao<Wedstrijd> genericDaoWedstrijd;
	private GenericDao<WedstrijdTicket> genericDaoWedstrijdTicket;

	@Autowired
	public Dataloader(GenericDao<Stadium> genericDaoStadium, GenericDao<Wedstrijd> genericDaoWedstrijd, GenericDao<WedstrijdTicket> genericDaoWedstrijdTicket) {
		this.genericDaoWedstrijd = genericDaoWedstrijd;
		this.genericDaoWedstrijdTicket = genericDaoWedstrijdTicket;
		this.genericDaoStadium = genericDaoStadium;
	}

	public void run(ApplicationArguments args) {
		//insertData();
	}
	
	private void insertData() {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
        Wedstrijd w2 = new Wedstrijd(new String[]{"Brazil","Switzerland"}, new GregorianCalendar(2021, 10, 27, 18, 0));
        Wedstrijd w3 = new Wedstrijd(new String[]{"Morocco","Croatia"}, new GregorianCalendar(2021, 10, 28, 15, 0));
        Wedstrijd w4 = new Wedstrijd(new String[]{"Spain","Germany"}, new GregorianCalendar(2021, 10, 29, 18, 0));
        Wedstrijd w5 = new Wedstrijd(new String[]{"France","Denmark"}, new GregorianCalendar(2021, 10, 30, 15, 0));
        Wedstrijd w6 = new Wedstrijd(new String[]{"Argentina","Mexico"}, new GregorianCalendar(2021, 10, 30, 18, 0));
        Wedstrijd w7 = new Wedstrijd(new String[]{"England","USA"}, new GregorianCalendar(2021, 11, 1, 18, 0));
        Wedstrijd w8 = new Wedstrijd(new String[]{"Netherlands","Qatar"}, new GregorianCalendar(2021, 11, 1, 21, 0));
        
        genericDaoWedstrijd.insert(w1);
        genericDaoWedstrijd.insert(w2);
        genericDaoWedstrijd.insert(w3);
        genericDaoWedstrijd.insert(w4);
        genericDaoWedstrijd.insert(w5);
        genericDaoWedstrijd.insert(w6);
        genericDaoWedstrijd.insert(w7);
        genericDaoWedstrijd.insert(w8);
        
        WedstrijdTicket wt1 = new WedstrijdTicket(w1, 35);
        WedstrijdTicket wt2 = new WedstrijdTicket(w2, 21);
        WedstrijdTicket wt3 = new WedstrijdTicket(w3, 5);
        WedstrijdTicket wt4 = new WedstrijdTicket(w4, 94);
        WedstrijdTicket wt5 = new WedstrijdTicket(w5, 45);
        WedstrijdTicket wt6 = new WedstrijdTicket(w6, 10);
        WedstrijdTicket wt7 = new WedstrijdTicket(w7, 22);
        WedstrijdTicket wt8 = new WedstrijdTicket(w8, 16);
        
        genericDaoWedstrijdTicket.insert(wt1);
        genericDaoWedstrijdTicket.insert(wt2);
        genericDaoWedstrijdTicket.insert(wt3);
        genericDaoWedstrijdTicket.insert(wt4);
        genericDaoWedstrijdTicket.insert(wt5);
        genericDaoWedstrijdTicket.insert(wt6);
        genericDaoWedstrijdTicket.insert(wt7);
        genericDaoWedstrijdTicket.insert(wt8);
        
        Stadium s1 = new Stadium("Al Bayt Stadium", Arrays.asList(wt1, wt2, wt3, wt6, wt7));
        Stadium s2 = new Stadium("Al Thumama Stadium", Arrays.asList(wt4, wt5, wt8));
        
        genericDaoStadium.insert(s1);
        genericDaoStadium.insert(s2);
	}
	
	
}
