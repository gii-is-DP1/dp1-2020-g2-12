package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  
		  List<Person> personas = new ArrayList<Person>();
		  Person julcarcos= new Person();
		  julcarcos.setFirstName("Julián");
		  julcarcos.setLastName("Carrascosa Cosano");
		  personas.add(julcarcos);
		  
		  Person luicharom= new Person();
		  luicharom.setFirstName("Luis");
		  luicharom.setLastName("Chancón Romero");
		  personas.add(luicharom);
		  
		  Person jualeoval= new Person();
		  jualeoval.setFirstName("Juan Jose");
		  jualeoval.setLastName("León Valderrama");
		  personas.add(jualeoval);
		  
		  Person josmarsan= new Person();
		  josmarsan.setFirstName("José");
		  josmarsan.setLastName("Martín Sanchez");
		  personas.add(josmarsan);
		  
		  Person fersilleo= new Person();
		  fersilleo.setFirstName("Fernando");
		  fersilleo.setLastName("Silva León");
		  personas.add(fersilleo);
		  
		  model.put("personas", personas);
		  model.put("title", "Pet Clinic");
		  model.put("group", "g2-12");
	    return "welcome";
	  }
}
