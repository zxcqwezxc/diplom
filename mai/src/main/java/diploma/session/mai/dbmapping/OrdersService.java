package diploma.session.mai.dbmapping;

import java.util.List;

public interface OrdersService {
    
    Order getOrder(int orderId);
    List<Order> getAllOrders();
    void makeOrder(int supplierId, int productId, int quantity, int warehouseId, String storageCell);
    String getProductName(int productId);
    List<String> getOrdersCoordinates();
}
