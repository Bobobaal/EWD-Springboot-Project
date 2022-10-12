package com.springBoot.fifaWorldCup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Wedstrijd;
import service.GenericDao;

@RestController
@RequestMapping("/fifaDetail")
public class FifaRestController {
	@Autowired
	private GenericDao<Wedstrijd> genericDaoWedstrijd;
	
	@GetMapping("/{id}")
	public String[] getWedstrijd(@PathVariable int id) throws IllegalArgumentException{
		Wedstrijd w = genericDaoWedstrijd.get(id);
		if(w == null) {
			throw new IllegalArgumentException("Wedstrijd bestaat met ID " + id + " bestaat niet");
		}
		return w.getLanden();
	}
}
