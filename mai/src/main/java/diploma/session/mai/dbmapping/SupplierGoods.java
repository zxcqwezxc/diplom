package diploma.session.mai.dbmapping;

import java.math.BigDecimal;

public record SupplierGoods(
    int id,
    int supplierId,
    String name,
    String description,
    BigDecimal price
) {}

