package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Athlete;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Sanción;
import org.springframework.samples.petclinic.service.AthleteService;
import org.springframework.samples.petclinic.service.SanciónService;
import org.springframework.samples.petclinic.service.exceptions.IncongruentSancionDateExcepcion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SanciónController {
	private final AthleteService athleteService;
	private final SanciónService sancionService;

	@Autowired
	public SanciónController(AthleteService athleteService, SanciónService sancionService) {
		this.athleteService = athleteService;
		this.sancionService =sancionService;
	}
	
	@InitBinder("sancion")
	public void initSancionBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
		
	@GetMapping(path="/athletes/{athleteId}/newSancion")
	public String sancionarDeportista(@PathVariable("athleteId")int athleteId,ModelMap model) {
		String view="athletes/createOrUpdateSancionForm";
		Sanción sancion = new Sanción();
		Athlete athlete = this.athleteService.findAthleteById(athleteId);
		athlete.addSancion(sancion);
		model.put("sancion", sancion);
		return view;
	}
	
	@PostMapping(value = "/athletes/{athleteId}/newSancion")
	public String processNewSancionForm(@PathVariable("athleteId")int athleteId, @Valid Sanción sancion, BindingResult result,ModelMap model) {
		Athlete athlete = this.athleteService.findAthleteById(athleteId);
		if (result.hasErrors()) {
			model.put("athlete", athlete);
			model.put("sancion", sancion);
			return "athletes/createOrUpdateSancionForm";
		}
		else {
			try {
			athlete.addSancion(sancion);
			this.sancionService.saveSancion(sancion);
			}catch (IncongruentSancionDateExcepcion ex) {
				result.rejectValue("fechaFin", "incongruent", "Debe ser posterior al dia actual");
				model.put("athlete", athlete);
				model.put("sancion", sancion);
				return "athletes/createOrUpdateSancionForm";
			}
			return "redirect:/athletes/{athleteId}";
		}
	}
	
//	@GetMapping(path="/athletes/{athleteId}/editSancion/{sancionId}")
//	public String editarSancion(@PathVariable("athleteId")int athleteId, @PathVariable("sancionId")int sancionId, ModelMap model) {
//		String view="athletes/createOrUpdateSancionForm";
//		Sanción sancion = this.sancionService.findSancionById(sancionId);
//		model.put("sancion", sancion);
//		return view;
//	}
//	
//	@PostMapping(value = "/athletes/{athleteId}/editSancion/{sancionId}")
//	public String processEditSancionForm(@PathVariable("athleteId")int athleteId, @PathVariable("sancionId")int sancionId, @Valid Sanción sancion, BindingResult result,ModelMap model) {
//		if (result.hasErrors()) {
//			model.put("sancion", sancion);
//			return "athletes/createOrUpdateSancionForm";
//		}
//		else {
//			Sanción sancionToUpdate=this.sancionService.findSancionById(sancionId);
//			BeanUtils.copyProperties(sancion, sancionToUpdate, "id","athlete");
//			this.sancionService.saveSancion(sancionToUpdate);
//			return "redirect:/entrenadores/{entrenadorId}/athletes/{athleteId}";
//		}
//	}

	
	@GetMapping(path = "athletes/{athleteId}/delete/{sancionId}")
	public String eliminarSanción(@PathVariable("athleteId") int athleteId, @PathVariable("sancionId") int sancionId, ModelMap modelMap) {
		Sanción sancion = this.sancionService.findSancionById(sancionId);
		this.sancionService.deleteSancion(sancion);
		return "redirect:/athletes/{athleteId}";
		
	}
	
}
