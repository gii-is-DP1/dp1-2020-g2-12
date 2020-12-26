package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Torneo;
import org.springframework.samples.petclinic.service.AthleteService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EntrenadorController {

	private static final String VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM = "entrenadores/createOrUpdateEntrenadorForm";

	private final EntrenadorService entrenadorService;
	private final AthleteService athleteService;

	@Autowired
	public EntrenadorController(EntrenadorService entrenadorService, AthleteService athleteService,
			UserService userService, AuthoritiesService authoritiesService) {
		this.entrenadorService = entrenadorService;
		this.athleteService = athleteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/entrenador/new")
	public String initCreationForm(Map<String, Object> model) {
		Entrenador entrenador = new Entrenador();
		model.put("entrenador", entrenador);
		return VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/entrenador/new")
	public String processCreationForm(@Valid Entrenador entrenador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		} else {
			this.entrenadorService.save(entrenador);

			return "redirect:/entrenadores/" + entrenador.getId();
		}
	}

	@GetMapping("/entrenadores")
	public String entrenadoresList(ModelMap modelMap) {
		String vista = "entrenadores/entrenadorList";
		Iterable<Entrenador> entrenadores = entrenadorService.findAll();
		modelMap.addAttribute("entrenadores", entrenadores);
		return vista;

	}

	@GetMapping(value = "/entrenadores/{entrenadorId}/edit")
	public String initUpdateOwnerForm(@PathVariable("entrenadorId") int entrenadorId, Model model) {
		Entrenador entrenador = this.entrenadorService.findEntrenadorById(entrenadorId);
		model.addAttribute(entrenador);
		return VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/entrenadores/{entrenadorId}/edit")
	public String processUpdateOwnerForm(@Valid Entrenador entrenador, BindingResult result,
			@PathVariable("entrenadorId") int entrenadorId) {
		if (result.hasErrors()) {
			return VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		} else {
			entrenador.setId(entrenadorId);
			this.entrenadorService.save(entrenador);
			return "redirect:/entrenadores/{entrenadorId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 * 
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/entrenadores/{entrenadorId}")
	public ModelAndView showEntrenador(@PathVariable("entrenadorId") int entrenadorId) {
		ModelAndView mav = new ModelAndView("entrenadores/entrenadorDetails");
		mav.addObject(this.entrenadorService.findEntrenadorById(entrenadorId));
		return mav;
	}

	@GetMapping("/entrenadores/{entrenadorId}/showAthletes")
	public String atletasEntrenadosPor(@PathVariable("entrenadorId") int entrenadorId, ModelMap modelMap) {
		String vista = "entrenadores/entrenados";
		Set<Athlete> entrenados = this.athleteService.findAthleteByEntrenadorId(entrenadorId);
		modelMap.addAttribute("entrenados", entrenados);
		Entrenador entrenador = this.entrenadorService.findEntrenadorById(entrenadorId);
		modelMap.addAttribute("entrenador", entrenador);
		return vista;

	}

	@GetMapping(path = "/entrenadores/{entrenadorId}/delete/{athleteId}")
	public String eliminarEntrenado(@PathVariable("entrenadorId") int entrenadorId, @PathVariable("athleteId") int athleteId, ModelMap modelMap) {
		this.athleteService.eliminarEntrenadorDeAtleta(athleteId);
		return "redirect:/entrenadores/{entrenadorId}";
		
	}
	
	@GetMapping(path = "/entrenadores/{entrenadorId}/add")
	public String buscarEntrenado(@PathVariable("entrenadorId") int entrenadorId, ModelMap modelMap) {
		Set <Athlete> atletas = this.athleteService.buscarAtletaSinEntrenador();
		Entrenador entrenador = this.entrenadorService.findEntrenadorById(entrenadorId);
		modelMap.addAttribute("atletas", atletas);
		modelMap.addAttribute("entrenador", entrenador);
		String vista = "entrenadores/entrenados";
		return vista;
		
	}
	
	@GetMapping(path = "/entrenadores/{entrenadorId}/add/{athleteId}")
	public String añadirEntrenado(@PathVariable("entrenadorId") int entrenadorId, @PathVariable("athleteId") int athleteId, ModelMap modelMap) {
		Athlete atleta = this.athleteService.findAthleteById(athleteId);
		this.athleteService.añadirEntrenadorDeAtleta(athleteId,entrenadorId);
		return "redirect:/entrenadores/{entrenadorId}";
		
	}

}