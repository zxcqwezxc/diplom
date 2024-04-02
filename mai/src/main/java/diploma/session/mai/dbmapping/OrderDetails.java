package diploma.session.mai.dbmapping;

import java.math.BigDecimal;

public record OrderDetails(
    int id,
    int orderId,
    int goodId,
    int quantity,
    BigDecimal price,
    int warehouseId,
    String storageCell
) {}

