package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Deporte;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.service.PistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pistas")
public class PistaController {
	
	
	@Autowired
	private PistaService pistaService;
	
	@ModelAttribute("types")
	public Collection<Deporte> populateDeportes() {
		return this.pistaService.findDeportes();
	}
	
	@GetMapping()
	public String listadoPistas(ModelMap modelMap) {
		String view="pistas/listPistas";
		Iterable<Pista> pistas = pistaService.findAll();
		modelMap.addAttribute("pistas", pistas);
		return view;
	}
	
	@GetMapping(path="/new")
	public String crearPista(ModelMap modelMap) {
		String view="pistas/editPista";
		modelMap.addAttribute("pista", new Pista());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarPista(@Valid Pista pista , BindingResult result,ModelMap modelMap) {
		String view="pistas/listPistas";
		if (result.hasErrors()) {
			modelMap.addAttribute("pista", pista);
			return "pistas/editPista";
		} else {
			pistaService.save(pista);
			modelMap.addAttribute("message", "Pista guardada con exito");
			view = listadoPistas(modelMap);
		}
		
		return view;
	}

	@GetMapping(path="/delete/{pistaId}")
	public String borrarPista(@PathVariable("pistaId") int pistaId, ModelMap modelMap) {
		String view="pistas/listPistas";
		Optional<Pista> pista = pistaService.findPistaById(pistaId);
		if (pista.isPresent()) {
			pistaService.delete(pista.get());
			modelMap.addAttribute("message", "Pista borrada con exito");
			view = listadoPistas(modelMap);
		} else {
			modelMap.addAttribute("message", "Pista no encontrada");
			view = listadoPistas(modelMap);
		}
		return view;
	}
}
