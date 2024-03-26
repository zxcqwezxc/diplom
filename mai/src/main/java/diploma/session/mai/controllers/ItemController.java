package diploma.session.mai.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import diploma.session.mai.dbmapping.GoodsService;
import diploma.session.mai.dbmapping.Item;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
    
    private final GoodsService goodsService;

    public ItemController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping(value = "/{id:\\d+}")
    public Item getItem(@PathVariable int id) {
        return goodsService.getItem(id);
    }

    @PostMapping("/add")
    public RedirectView addItem(@RequestParam int id, 
                          @RequestParam String name, 
                          @RequestParam int income, 
                          @RequestParam int instorage,
                          @RequestParam int outcome, 
                          @RequestParam int vendorCode,
                          RedirectAttributes redirectAttributes) {
        
        // Создаем объект Item на основе полученных данных
        Item item = new Item(id, name, income, instorage, outcome, vendorCode);
        
        // Вызываем метод добавления объекта в репозиторий
        goodsService.addItem(item);

        redirectAttributes.addFlashAttribute("message", "Item added successfully!");
        // Перенаправляем пользователя на какую-то страницу (например, на главную)
        return new RedirectView("/home");
    }
}
