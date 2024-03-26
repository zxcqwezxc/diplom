package diploma.session.mai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import diploma.session.mai.dbmapping.GoodsService;
import diploma.session.mai.dbmapping.Item;
import diploma.session.mai.dbmapping.ItemNotFoundException;

import java.util.List;

@Controller
public class WarehouseController {

    private final GoodsService goodsService;

    public WarehouseController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/warehouse")
    public String showWarehouse(Model model) {
        // Получите данные из базы данных, например, используя сервис
        List<Item> items = goodsService.getAllItems();

        if (items.isEmpty()) {
            throw new ItemNotFoundException(0);
        }

        // Передайте данные в модель для отображения в шаблоне
        model.addAttribute("items", items);

        // Верните имя шаблона Thymeleaf
        return "stock";
    }

    
}