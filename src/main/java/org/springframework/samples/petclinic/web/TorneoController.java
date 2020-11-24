package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.model.Torneo;
import org.springframework.samples.petclinic.service.TorneoService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/torneos")
public class TorneoController {

	private static final String VIEWS_TORNEO = "torneos/editTorneos";
	@Autowired
	private TorneoService torneoService;

	@GetMapping()
	public String listadoTorneos(ModelMap modelMap) {
		String view = "torneos/listTorneos";
		Iterable<Torneo> torneos = torneoService.findAll();
		modelMap.addAttribute("torneos", torneos);
		return view;
	}

	@GetMapping(path = "/new")
	public String crearTorneo(ModelMap modelMap) {
		String view = "torneos/editTorneos";
		modelMap.addAttribute("torneo", new Torneo());
		return view;
	}

	@PostMapping(path = "/save")
	public String salvarTorneo(@Valid Torneo torneo, BindingResult result, ModelMap modelMap) {
		String view = "torneos/listTorneos";
		if (result.hasErrors()) {
			modelMap.addAttribute("torneo", torneo);
			return "torneos/editTorneo";
		} else {
			torneoService.save(torneo);
			modelMap.addAttribute("message", "Torneo guardado con exito");
			view = listadoTorneos(modelMap);
		}

		return view;
	}

	@GetMapping(path = "/delete/{torneoId}")
	public String borrarTorneo(@PathVariable("torneoId") int torneoId, ModelMap modelMap) {
		String view = "torneos/listTorneos";
		Optional<Torneo> torneo = torneoService.findTorneoById(torneoId);
		if (torneo.isPresent()) {
			torneoService.delete(torneo.get());
			modelMap.addAttribute("message", "Torneo borrado con exito");
			view = listadoTorneos(modelMap);
		} else {
			modelMap.addAttribute("message", "Torneo no encontrado");
			view = listadoTorneos(modelMap);
		}
		return view;
	}

@GetMapping(value = "/edit/{torneoId}")
public String editarTorneo(@PathVariable("torneoId") int torneoId, ModelMap model) {
	System.out.println("hola");
	Optional<Torneo> torneo = this.torneoService.findTorneoById(torneoId);
	
	model.addAttribute("torneo", torneo.get() );
	return "torneos/editTorneos";
}

	@PostMapping(value = "/edit/{torneoId}")
	public String processUpdateOwnerForm(@Valid Torneo torneo, BindingResult result,
			@PathVariable("torneoId") int torneoId,  ModelMap model) {
		if (result.hasErrors()) {
			model.put("torneo", torneo);
			return VIEWS_TORNEO;
		} else {
			Optional<Torneo> torneoAc = this.torneoService.findTorneoById(torneoId);
			BeanUtils.copyProperties(torneo, torneoAc,"id");
			
			this.torneoService.save(torneo);
			
			return "redirect:/torneos/";
		}

	}

}

