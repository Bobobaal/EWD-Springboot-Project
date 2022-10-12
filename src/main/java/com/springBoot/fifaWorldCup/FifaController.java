package com.springBoot.fifaWorldCup;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.Stadium;
import domain.WedstrijdTicket;
import form.TicketsBestellen;
import service.GenericDao;
import service.VoetbalService;
import validator.TicketsBestellenValidator;

@Controller
@RequestMapping("/fifa")
@SessionAttributes({"stadiumNaam", "matchInfo", "aantalTicketsOver"})
public class FifaController {
	
	@Autowired
	private VoetbalService vService;
	
	@Autowired
	private TicketsBestellenValidator tbValidator;
	
	@Autowired
	private MessageSource ms;
	
	@Autowired
	private GenericDao<WedstrijdTicket> genericDaoWedstrijdTicket;
	
	@Autowired
	private GenericDao<Stadium> genericDaoStadium;
	
	@GetMapping
	public String toonSelectStadium(@RequestParam(value="uitverkocht", required=false) Boolean uitverkocht, @RequestParam(value="tickets", required=false) String tickets,
			@RequestParam(value="stadionBestaatNiet", required=false) Boolean stadionBestaatNiet, @RequestParam(value="matchBestaatNiet", required=false) Boolean matchBestaatNiet,
			Model model, Principal principal, Locale locale) {
		model.addAttribute("stadium", new Stadium());
		//model.addAttribute("stadiumList", vService.getStadiumList());
		model.addAttribute("stadiumList", genericDaoStadium.findAll());
		
		if(uitverkocht != null && uitverkocht == true) {
			model.addAttribute("uitverkochtMessage", ms.getMessage("notif.soldOutMatch", new Object[]{}, locale));
		}
		
		if(stadionBestaatNiet != null && stadionBestaatNiet == true) {
			model.addAttribute("nietBestaandStadionMessage", ms.getMessage("notif.unknownStadium", new Object[]{}, locale));
		}
		
		if(matchBestaatNiet != null && matchBestaatNiet == true) {
			model.addAttribute("nietBestaandeMatchMessage", ms.getMessage("notif.unknownMatch", new Object[]{}, locale));
		}
		
		if(tickets != null) {
			if(Integer.parseInt(tickets) == 1) {
				model.addAttribute("aantalGekochteTicketsMessage", ms.getMessage("format.boughtTicketsStringSingular", new Object[]{}, locale));
			}else {
				model.addAttribute("aantalGekochteTicketsMessage", tickets + " " + ms.getMessage("format.boughtTicketsStringPlural", new Object[]{}, locale));
			}
		}
		return "stadiumSelectForm";
	}
	
	@PostMapping
	public String toonWedstrijdenSelectedStadium(@ModelAttribute Stadium stadium, Model model, Locale locale) {
		try {
			Stadium stadion = genericDaoStadium.geefStadium(stadium.getNaam());
			//model.addAttribute("matchLijst", vService.getWedstrijdenByStadium(stadium.getNaam()));
			
			if(stadion == null || stadium == null) {
				throw new IllegalArgumentException();
			}
			model.addAttribute("matchLijst", stadion.getWedstrijden());
			
		} catch(Exception e) {
			return "redirect:/fifa?stadionBestaatNiet=true";
		}
		model.addAttribute("stadiumNaam", stadium.getNaam());
		return "matchList";
	}
	
	@GetMapping(value = "/{id}")
	public String showTicketsBestellenForm(@PathVariable String id, Model model, Locale locale) {
		WedstrijdTicket wedstrijd;
		try {
			//wedstrijd = vService.getWedstrijd(id);
			wedstrijd = genericDaoWedstrijdTicket.get(Integer.parseInt(id));
			
			if(wedstrijd == null) {
				throw new IllegalArgumentException();
			}
			
		} catch(Exception e) {
			return "redirect:/fifa?matchBestaatNiet=true";
		}
		
		String matchInfo = geefMatchInfo(wedstrijd, locale);
		
		if(wedstrijd.uitverkocht()) {
			return "redirect:/fifa?uitverkocht=true";
		}
		model.addAttribute("ticketsBestellen", new TicketsBestellen());
		model.addAttribute("selectedMatch", wedstrijd);
		
		model.addAttribute("matchInfo", matchInfo);
		
		model.addAttribute("aantalTicketsOver", wedstrijd.getTickets());
		model.addAttribute("stadiumNaam");
		return "ticketsBestellenForm";
	}
	
	@PostMapping(value = "/{id}")
	public String bestelTickets(@PathVariable String id, @Valid @ModelAttribute TicketsBestellen tb, BindingResult result, Model model, Locale locale) {
		tbValidator.validate(tb, result);
		if(result.hasErrors()) {
			return "ticketsBestellenForm";
		}
		
		//WedstrijdTicket wedstrijd = vService.getWedstrijd(id);
		WedstrijdTicket wedstrijd = genericDaoWedstrijdTicket.get(Integer.parseInt(id));
		
		String matchInfo = geefMatchInfo(wedstrijd, locale);
		
		//int gekochteTickets = vService.ticketsBestellen(id, tb.getAantalTickets());
		int gekochteTickets = wedstrijd.ticketsKopen(tb.getAantalTickets());
		genericDaoWedstrijdTicket.update(wedstrijd);
		return "redirect:/fifa?tickets=" + gekochteTickets;
	}
	
	private String geefMatchInfo(WedstrijdTicket wedstrijd, Locale locale) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(ms.getMessage("date.format.pattern", new Object[]{}, locale));
    	SimpleDateFormat timeFormat = new SimpleDateFormat(ms.getMessage("time.format.pattern", new Object[]{}, locale));
		
		return String.format(ms.getMessage("format.matchInfo", new Object[]{}, locale),
				ms.getMessage("country." + wedstrijd.getWedstrijd().getLanden()[0], new Object[]{}, locale),
				ms.getMessage("country." + wedstrijd.getWedstrijd().getLanden()[1], new Object[]{}, locale),
				dateFormat.format(wedstrijd.getWedstrijd().getDagEnUur().getTime()),
				timeFormat.format(wedstrijd.getWedstrijd().getDagEnUur().getTime()));
	}
}
