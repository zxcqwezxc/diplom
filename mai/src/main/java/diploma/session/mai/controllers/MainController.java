package diploma.session.mai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/home")
	public String home( Model model) {
		model.addAttribute("title", "Главная страница");
		return "home";
	}

	@GetMapping("/about")
	public String about( Model model) {
		model.addAttribute("title", "О нас");
		return "home";
	}

	@GetMapping("/addItem")
	public String addItem( Model model) {
		model.addAttribute("title", "Добавление товара");
		return "addItem";
	}
	@GetMapping("/map")
	public String map( Model model) {
		model.addAttribute("title", "Карта");
		return "map";
	}

}