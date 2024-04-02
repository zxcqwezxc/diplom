package diploma.session.mai.dbmapping;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{
    
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Order getOrder(int orderId) {
        return ordersRepository.getOrderById(orderId).orElseThrow(() -> new ItemNotFoundException(orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return ordersRepository.getAllOrders();
    }

    @Override
    public void makeOrder(int supplierId, int productId, int quantity, int warehouseId, String storageCell) {
        ordersRepository.makeOrder(supplierId, productId, quantity, warehouseId, storageCell);
    }

    @Override
    public String getProductName(int productId) {
        return ordersRepository.getProductName(productId);
    }
}
