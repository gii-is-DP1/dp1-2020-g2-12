package org.springframework.samples.petclinic.web;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.service.AthleteService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AthleteController {
	
	@Autowired
	private AthleteService athleteService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	private static final String VIEWS_ATHLETE_CREATE_OR_UPDATE_FORM = "athletes/createOrUpdateAthleteForm";
	
	
	@ModelAttribute("genero")
	public Collection<String> getGenero(){
		Collection<String> genero = Arrays.asList("HOMBRE","MUJER");
		return genero;
	}
	
	@ModelAttribute("sancion")
	public Collection<String> getSancionated(){
		Collection<String> sancion = Arrays.asList("SANCIONADO","NO SANCIONADO");
		return sancion;
	}
	
	@Autowired
	public AthleteController(AthleteService athleteService) {
		this.athleteService = athleteService;
	}
	
	@GetMapping( value = "/entrenadores/{entrenadorId}/athletes/new")
	public String initCreationForm(Entrenador entrenador, ModelMap model) {
		Athlete athlete = new Athlete();
		entrenador.addAthlete(athlete);
		model.put("athlete", athlete);
		return VIEWS_ATHLETE_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/entrenadores/{entrenadorId}/athletes/new")
	public String processCreationForm(@PathVariable("entrenadorId") int entrenadorId, @Valid final Athlete athlete, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("athlete", athlete);
			return AthleteController.VIEWS_ATHLETE_CREATE_OR_UPDATE_FORM;
		} else {
			Entrenador entrenador = entrenadorService.findEntrenadorById(entrenadorId);
			entrenador.addAthlete(athlete);
			this.athleteService.save(athlete);
			this.entrenadorService.save(entrenador);
			return "redirect:/entrenadores/{entrenadorId}";
			}
		}
	
	@GetMapping(value= "/entrenadores/{entrenadorId}/athletes/{athleteId}/edit")
	public String initUpdateForm(@PathVariable("athleteId") int athleteId, ModelMap model) {
		Athlete athlete = this.athleteService.findAthleteById(athleteId);
		boolean edit = true;
		model.put("athlete", athlete);
		model.put("edit", edit);
		return VIEWS_ATHLETE_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/entrenadores/{entrenadorId}/athletes/{athleteId}/edit")
	public String processUpdateForm(@Valid Athlete athlete, BindingResult result,@PathVariable("athleteId") int athleteId, ModelMap model) {		
		
		boolean edit = true;

		if(result.hasErrors()) {
			model.put("athlete", athlete);
			model.put("edit", edit);
			return VIEWS_ATHLETE_CREATE_OR_UPDATE_FORM;
		} else {
			Athlete athleteToUpdate=this.athleteService.findAthleteById(athleteId);
			BeanUtils.copyProperties(athlete, athleteToUpdate, "id","entrenador"); 
			this.athleteService.save(athleteToUpdate); 
			return "redirect:/entrenadores/{entrenadorId}";
		}
		
	}
	
	@GetMapping("/athletes")
	public String athletesList(ModelMap modelMap) {
		String vista = "athletes/listAthletes";
		Iterable<Athlete> athletes = athleteService.findAll();
		modelMap.addAttribute("athletes", athletes);
		return vista;
		
	}
	
	@GetMapping(value = "/entrenadores/{entrenadorId}/athletes/{athleteId}")
	public String showAthlete(@PathVariable("athleteId") int athleteId, ModelMap modelMap) {
		Athlete athlete = this.athleteService.findAthleteById(athleteId);
		modelMap.addAttribute("athlete",athlete);
		return "athletes/athleteDetails";
		
	}
	
}
	