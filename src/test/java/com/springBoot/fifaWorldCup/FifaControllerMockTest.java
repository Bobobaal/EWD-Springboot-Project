package com.springBoot.fifaWorldCup;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.GenericDao;
import validator.TicketsBestellenValidator;

class FifaControllerMockTest {

	private MockMvc mMvc;
	
	private FifaController fc;
	
	@Mock
	private MessageSource msMock;
	
	@Mock
	private GenericDao<Wedstrijd> wedstrijdDaoMock;
	
	@Mock
	private GenericDao<WedstrijdTicket> wedstrijdTicketDaoMock;
	
	@Mock
	private GenericDao<Stadium> stadiumDaoMock;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		fc = new FifaController();
		mMvc = standaloneSetup(fc).build();
	}
	
	@Test
	public void toonSelectStadium_uitverkochtFalse_GeenTickets_toontStadiumLijst() throws Exception {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		
		
		mMvc.perform(get("/fifa")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("uitverkochtMessage", "aantalGekochteTicketsMessage", "nietBestaandStadionMessage", "nietBestaandeMatchMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonSelectStadion_uitverkochtTrue_toontStadiumLijstEnUitverkochtMsg() throws Exception {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(get("/fifa").param("uitverkocht", "true")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("aantalGekochteTicketsMessage", "nietBestaandStadionMessage", "nietBestaandeMatchMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList", "uitverkochtMessage"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonSelectStadion_stadionBestaatNietTrue_toontStadiumLijstEnToonMsg() throws Exception{
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(get("/fifa").param("stadionBestaatNiet", "true")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("aantalGekochteTicketsMessage", "uitverkochtMessage", "nietBestaandeMatchMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList", "nietBestaandStadionMessage"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonSelectStadion_matchBestaatNietTrue_toontStadiumLijstEnToonMsg() throws Exception{
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(get("/fifa").param("matchBestaatNiet", "true")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("aantalGekochteTicketsMessage", "uitverkochtMessage", "nietBestaandStadionMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList", "nietBestaandeMatchMessage"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonSelectStadion_TicketsGekocht_toontStadiumLijstEnTicketsGekochtMsg() throws Exception {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(get("/fifa").param("tickets", "3")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("uitverkochtMessage", "nietBestaandStadionMessage", "nietBestaandeMatchMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList", "aantalGekochteTicketsMessage"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonSelectStadion_eenTicketGekocht_toontStadiumLijstEnTicketGekochtMsg() throws Exception {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		List<Stadium> stadions = new ArrayList<>();
		stadions.add(new Stadium("Ghelamco Arena", wtLijst));
		
		Mockito.when(stadiumDaoMock.findAll()).thenReturn(stadions);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(get("/fifa").param("tickets", "1")).andExpect(view().name("stadiumSelectForm"))
		.andExpect(model().attributeDoesNotExist("uitverkochtMessage", "nietBestaandStadionMessage", "nietBestaandeMatchMessage"))
		.andExpect(model().attributeExists("stadium", "stadiumList", "aantalGekochteTicketsMessage"))
		.andExpect(model().attribute("stadiumList", stadions));
	}
	
	@Test
	public void toonWedstrijdenSelectedStadium_BestaandStadion_toontWedstrijden() throws Exception {
		Wedstrijd w1 = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		List<WedstrijdTicket> wtLijst = new ArrayList<>();
		wtLijst.add(new WedstrijdTicket(w1, 35));
		Stadium stadion = new Stadium("Ghelamco Arena", wtLijst);
		
		Mockito.when(stadiumDaoMock.geefStadium("Ghelamco Arena")).thenReturn(stadion);
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		
		mMvc.perform(post("/fifa").param("naam", "Ghelamco Arena"))
		.andExpect(view().name("matchList"))
		.andExpect(model().attributeExists("matchLijst", "stadiumNaam"))
		.andExpect(model().attribute("matchLijst", stadion.getWedstrijden()))
		.andExpect(model().attribute("stadiumNaam", stadion.getNaam()));
	}
	
	@Test
	public void toonWedstrijdenSelectedStadium_stadionBestaatNiet_Error() throws Exception {
		
		Mockito.when(stadiumDaoMock.geefStadium("Ghelamco Arena")).thenReturn(null);
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		
		ReflectionTestUtils.setField(fc, "genericDaoStadium", stadiumDaoMock);
		ReflectionTestUtils.setField(fc, "ms", msMock);
		
		mMvc.perform(post("/fifa").param("naam", "Ghelamco Arena"))
		.andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?stadionBestaatNiet=true"));
	}
	
	@Test
	public void ShowTicketsBestellenForm_matchNietUitverkocht_toontForm() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		
		mMvc.perform(get("/fifa/1"))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().attributeExists("ticketsBestellen", "selectedMatch", "matchInfo", "aantalTicketsOver"))
		.andExpect(model().attribute("aantalTicketsOver", wt.getTickets()))
		.andExpect(model().attribute("selectedMatch", wt));
	}
	
	@Test
	public void showTicketsBestellenForm_matchUitverkocht_redirectStadiumList() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 0);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		
		mMvc.perform(get("/fifa/1")).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?uitverkocht=true"));
	}
	
	@Test
	public void showTicketsBestellenForm_matchBestaatNiet_redirectStadiumList() throws Exception {	
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(null);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		
		mMvc.perform(get("/fifa/1")).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?matchBestaatNiet=true"));
	}
	
	@Test
	public void bestelTickets_correcteInvoer_redirectStadiumList() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "2");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params)).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?tickets=2"));
	}
	
	@Test
	public void bestelTickets_correcteInvoerAantalTicketsGrenswaarde1_redirectStadiumList() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "1");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params)).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?tickets=1"));
	}
	
	@Test
	public void bestelTickets_correcteInvoerAantalTicketsGrenswaarde25_redirectStadiumList() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "25");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params)).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa?tickets=25"));
	}
	
	@Test
	public void bestelTickets_fouteInvoerEmail_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddddddd");
		params.add("aantalTickets", "2");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(1));
	}
	
	@Test
	public void bestelTickets_fouteInvoerAantalTicketsNegatief_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "-2");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(1));
	}
	
	@Test
	public void bestelTickets_fouteInvoerAantalTickets0_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "0");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(1));
	}
	
	@Test
	public void bestelTickets_fouteInvoerAantalTickets26_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "26");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(1));
	}
	
	@Test
	public void bestelTickets_fouteInvoerCodes_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "10");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "9");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(1));
	}
	
	@Test
	public void bestelTickets_fouteInvoerEmailEnAantalTickets_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddddddd");
		params.add("aantalTickets", "26");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "20");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(2));
	}
	
	@Test
	public void bestelTickets_fouteInvoerEmailEnCodes_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddddddd");
		params.add("aantalTickets", "10");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "9");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(2));
	}
	
	@Test
	public void bestelTickets_fouteInvoerAantalTicketsEnCodes_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddd@dddd");
		params.add("aantalTickets", "26");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "9");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(2));
	}
	
	@Test
	public void bestelTickets_AllesFouteInvoer_blijftOpFormPaginaErrorMsg() throws Exception {
		Wedstrijd w = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		WedstrijdTicket wt = new WedstrijdTicket(w, 35);
		
		Mockito.when(msMock.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("");
		Mockito.when(wedstrijdTicketDaoMock.get(1)).thenReturn(wt);
		
		ReflectionTestUtils.setField(fc, "ms", msMock);
		ReflectionTestUtils.setField(fc, "genericDaoWedstrijdTicket", wedstrijdTicketDaoMock);
		ReflectionTestUtils.setField(fc, "tbValidator", new TicketsBestellenValidator());
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "dddddddd");
		params.add("aantalTickets", "26");
		params.add("voetbalCode1", "10");
		params.add("voetbalCode2", "9");
		
		mMvc.perform(post("/fifa/1").params(params))
		.andExpect(view().name("ticketsBestellenForm"))
		.andExpect(model().hasErrors())
		.andExpect(model().errorCount(3));
	}
}