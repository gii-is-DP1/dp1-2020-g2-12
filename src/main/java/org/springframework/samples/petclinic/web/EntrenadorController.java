package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
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

	@Autowired
	public EntrenadorController(EntrenadorService entrenadorService, UserService userService, AuthoritiesService authoritiesService) {
		this.entrenadorService = entrenadorService;
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
		}
		else {
			this.entrenadorService.save(entrenador);
			
			return "redirect:/entrenadores/" + entrenador.getId();
		}
	}

	

	@GetMapping("/entrenadores")
	public String athletesList(ModelMap modelMap) {
		String vista = "entrenadores/listEntrenadores";
		Iterable<Entrenador> entrenadores= entrenadorService.findAll();
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
		}
		else {
			entrenador.setId(entrenadorId);
			this.entrenadorService.save(entrenador);
			return "redirect:/entrenadores/{entrenadorId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/entrenadores/{entrenadorId}")
	public ModelAndView showEntrenador(@PathVariable("entrenadorId") int entrenadorId) {
		ModelAndView mav = new ModelAndView("entrenadores/entrenadorDetails");
		mav.addObject(this.entrenadorService.findEntrenadorById(entrenadorId));
		return mav;
	}
}
