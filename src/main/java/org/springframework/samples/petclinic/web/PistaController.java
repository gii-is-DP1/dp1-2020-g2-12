package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pista;
import org.springframework.samples.petclinic.service.PistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PistaController {
	
	@Autowired
	private PistaService pistaService;
	
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

}
