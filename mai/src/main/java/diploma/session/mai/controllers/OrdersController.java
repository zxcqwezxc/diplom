package diploma.session.mai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import diploma.session.mai.dbmapping.OrdersService;
import diploma.session.mai.dbmapping.ItemNotFoundException;

@Controller
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/create-order")
    public String showCreateOrderForm(
        @RequestParam("productId") int productId,
        @RequestParam("supplierId") int supplierId, 
        Model model) {
        try {
            model.addAttribute("productName", ordersService.getProductName(productId));
            model.addAttribute("productId", productId);
            model.addAttribute("supplierId", supplierId);
            return "create-order";
        } catch (ItemNotFoundException e) {
            return "error";
        }
    }

    @PostMapping("/submit-order-and-redirect")
    public RedirectView submitOrderAndRedirect(
        @RequestParam("warehouseId") int warehouseId, 
        @RequestParam("storageCell") String storageCell,
        @RequestParam("productId") int productId, 
        @RequestParam("supplierId") int supplierId, 
        @RequestParam("quantity") int quantity, 
        RedirectAttributes redirectAttributes) {
        // Выполните метод, создающий заказ
        try {
            ordersService.makeOrder(supplierId, productId, quantity, warehouseId, storageCell);
            redirectAttributes.addFlashAttribute("message", "Order added successfully!");
            // Перенаправляем пользователя на какую-то страницу (например, на главную)
            return new RedirectView("/home?orderSuccess=true");
        } catch (ItemNotFoundException e) {
            return new RedirectView("/error");
        }
        // Перенаправьте на главную страницу или другую страницу по вашему выбору
    }
}
