package diploma.session.mai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import diploma.session.mai.dbmapping.Supplier;
import diploma.session.mai.dbmapping.SupplierService;

@Controller
public class SuppliersController {

    private final SupplierService supplierService;

    @Autowired
    public SuppliersController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public String showSuppliers(Model model) {
        model.addAttribute("title", "Поставщики");
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers";
    }

    @GetMapping("/supplier/{id}")
    public String showSupplierPage(@PathVariable int id, Model model) {
        int supplierId = (id);

        // Получение информации о поставщике по его ID
        Supplier supplier = supplierService.getSupplierById(supplierId);

        // Проверка на null, чтобы избежать NullPointerException
        if (supplier == null) {
            // Если поставщик не найден, перенаправляем на страницу ошибки или обрабатываем ошибку иным способом
            return "error"; // Имя вашего HTML-шаблона для страницы ошибки
        }

        // Передача информации о поставщике в модель
        model.addAttribute("supplier", supplier);
        model.addAttribute("products", supplierService.getAllSupplierGoods(supplierId));

    // Возвращаем HTML-шаблон страницы поставщика
    return "supplier"; // Имя вашего HTML-шаблона для страницы поставщика
    }

}
