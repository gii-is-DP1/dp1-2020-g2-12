package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenadores/{entrenadorId}")
public class SanciónController {
	private final AthleteService athleteService;

	@Autowired
	public SanciónController(AthleteService athleteService) {
		this.athleteService = athleteService;
	}
		
	@GetMapping(path="/athletes/{athleteId}/newSancion")
	public String sancionarDeportista(@PathVariable("athleteId") int athleteId, ModelMap modelMap) {
		String view="athletes/newSancion";
		Athlete athlete = this.athleteService.findAthleteById(athleteId);
		Sanción sancion = new Sanción();
		athlete.addSancion(sancion);
		modelMap.addAttribute("sancion", sancion);
		return view;
	}
	
	@PostMapping(value = "/athletes/{athleteId}/newSancion")
	public String processNewSancionForm(@PathVariable("athleteId")int athleteId, @Valid Sanción sancion, BindingResult result,final ModelMap model) {
		if (result.hasErrors()) {
			model.put("sancion", sancion);
			return "athletes/newSancion";
		}
		else {
			Athlete athlete = this.athleteService.findAthleteById(athleteId);
			sancion.setAthlete(athlete);
			athlete.addSancion(sancion);
			this.athleteService.saveSancion(sancion);
			this.athleteService.save(athlete);
			return "redirect:/entrenadores/{entrenadorId}/athletes/{athleteId}";
		}
	}
	
	
}
