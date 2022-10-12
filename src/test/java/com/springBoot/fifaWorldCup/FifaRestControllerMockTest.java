package com.springBoot.fifaWorldCup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.GregorianCalendar;

import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.GenericDao;

@WebMvcTest(FifaRestController.class)
class FifaRestControllerMockTest {

	@Autowired
	private MockMvc mMvc;
	
	@MockBean
	private GenericDao<Wedstrijd> wedstrijdDaoMock;
	
	@MockBean
	private GenericDao<WedstrijdTicket> wedstrijdTicketDaoMock;
	
	@MockBean
	private GenericDao<Stadium> stadiumDaoMock;
	
	@Test
	public void GetfifaDetail_bestaandId_KrijgtLandenTerug() throws Exception {
		Wedstrijd result = new Wedstrijd(new String[]{"Belgium","Canada"}, new GregorianCalendar(2021, 10, 26, 21, 0));
		
		Mockito.when(wedstrijdDaoMock.get(1)).thenReturn(result);
		
		mMvc.perform(get("/fifaDetail/1")).andExpect(jsonPath("$[0]", is("Belgium"))).andExpect(jsonPath("$[1]", is("Canada")));
	}
	
	@Test
	public void GetfifaDetail_nietBestaandId_Error() throws Exception {		
		Mockito.when(wedstrijdDaoMock.get(50)).thenReturn(null);
		
		assertThrows(NestedServletException.class, () -> mMvc.perform(get("/fifaDetail/50")));
	}
}
