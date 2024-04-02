package diploma.session.mai.dbmapping;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Order(
    int orderId,
    int supplierId,
    int goodId,
    int warehouseId,
    LocalDate orderDate,
    int quantity,
    BigDecimal price,
    BigDecimal totalAmount,
    String storageCell
) {
} 