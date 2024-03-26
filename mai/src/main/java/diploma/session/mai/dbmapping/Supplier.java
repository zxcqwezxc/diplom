package diploma.session.mai.dbmapping;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Supplier(
    int id,
    String name,
    String contactEmail,
    String contactPhone,
    String type,
    String description,
    BigDecimal rating,
    LocalDate registrationDate,
    LocalDate lastOrderDate,
    String deliveryTerms,
    String additionalInfo
) {}

