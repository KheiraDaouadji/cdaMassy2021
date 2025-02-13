package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.service.EFGService;

@Controller
public class EFGController {

	@Autowired
	private EFGService efgService;

	@RequestMapping("canaux/{idCanal}/EFGs")
	public ModelAndView allEFGs(@PathVariable(value = "idCanal") int idCanal, ModelAndView mv) {
		mv.setViewName("EFGs");
		List<EFG> efgs = efgService.listByCanal(idCanal);
		mv.addObject("EFGs", efgs);
		mv.addObject("idCanal", idCanal);
		return mv;
	}

	@RequestMapping("canaux/{idCanal}/EFGs/{idEFG}")
	public ModelAndView oneEFG(@PathVariable(value = "idEFG") int idEFG, @PathVariable(value = "idCanal") int idCanal,
			ModelAndView mv) {
		mv.setViewName("EFG");
		EFG efg = efgService.findById(idEFG);
		mv.addObject("EFG", efg);
		return mv;
	}
	
	@RequestMapping("canaux/{idCanal}/EFGs/new")
	public ModelAndView newEFG(@PathVariable(value="idCanal") int idCanal, ModelAndView mv) {
		mv.setViewName("createEFG");
		mv.addObject("newEFG", new EFG());
		return mv;
	}
	
	@PostMapping("canaux/{idCanal}/EFGs/new") 
	public ModelAndView postForm(@ModelAttribute("newEFG") EFG efg, @PathVariable(value="idCanal") int idCanal) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("EFG", efg);
		System.out.println("coucou");
		efg.setIdCreateur(1);
		efg.setIdCanal(idCanal);
		EFG efgSaved = efgService.saveEFG(efg);
		System.out.println(efgSaved);
		mv.setViewName("redirect:/canaux/{idCanal}/EFGs/"+efgSaved.getIdEfg());
		return mv;
	}
}
