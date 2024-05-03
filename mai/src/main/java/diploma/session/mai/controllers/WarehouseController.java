package diploma.session.mai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import diploma.session.mai.dbmapping.ChartData;
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

    @GetMapping("/stocks")
    public String showStocks(Model model) {
        model.addAttribute("title", "Склады");
        model.addAttribute("warehouses", goodsService.getAllWarehouses());
        return "stocks";
    }

    @GetMapping("/stock/{id}")
    public String showSupplierPage(@PathVariable int id, Model model) {
        int stockId = (id);

        // Получение информации о поставщике по его ID
        List<Item> warehouse = goodsService.getAllItemsById(stockId);

        // Проверка на null, чтобы избежать NullPointerException
        if (warehouse == null) {
            // Если поставщик не найден, перенаправляем на страницу ошибки или обрабатываем ошибку иным способом
            return "error"; // Имя вашего HTML-шаблона для страницы ошибки
        }

        // Передача информации о поставщике в модель
        model.addAttribute("items", warehouse);
        // Возвращаем HTML-шаблон страницы поставщика
        return "stock";
    }

    @GetMapping("/cellsChart")
	public String map( Model model) {
		model.addAttribute("title", "График ячеек");
        List<ChartData> chartsData = goodsService.getChartData();
        model.addAttribute("chartsData", chartsData);
        
		return "cellsChart";
	}

    
}