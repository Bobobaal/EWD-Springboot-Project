package com.springBoot.fifaWorldCup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import domain.*;
import service.GenericDao;
import service.GenericDaoJpa;
import service.VoetbalService;
import service.VoetbalServiceImpl;
import validator.TicketsBestellenValidator;

@SpringBootApplication
public class A3VanMeerbeeckDieterApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(A3VanMeerbeeckDieterApplication.class, args);
	}
	
	@Bean
	public VoetbalService vService() {
		return new VoetbalServiceImpl();
	}
	
	@Bean
	public TicketsBestellenValidator tbValidator() {
		return new TicketsBestellenValidator();
	}
	
	@Bean
	public MessageSource ms() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("messages");
		return ms;
	}
	
	@Bean
	public GenericDao<Wedstrijd> genericDaoWedstrijd(){
		return new GenericDaoJpa<>(Wedstrijd.class);
	}
	
	@Bean
	public GenericDao<WedstrijdTicket> genericDaoWedstrijdTicket(){
		return new GenericDaoJpa<>(WedstrijdTicket.class);
	}
	
	@Bean
	public GenericDao<Stadium> genericDaoStadium(){
		return new GenericDaoJpa<>(Stadium.class);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
	}
	
	@Override
    public void addResourceHandlers(
                              ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/css/**").
                                addResourceLocations("resources/css/");
    }

}
