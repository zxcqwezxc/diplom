package diploma.session.mai.dbmapping;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {

    Optional<Order> getOrderById(int orderId);
    List<Order> getAllOrders();
    void makeOrder(int supplierId, int productId, int quantity, int warehouseId, String storageCell);
    String getProductName(int productId);
    List<String> getOrdersCoordinates();
}
