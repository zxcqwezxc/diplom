package diploma.session.mai.dbmapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@ComponentScan(basePackages={"diploma"})

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {

    private static final String SQL_GET_ORDER_BY_ID = 
                                "SELECT o.id as order_id, o.supplier_id, o.order_date, o.total_amount, " +
                                "od.good_id, od.quantity, od.price, od.warehouse_id, od.storage_cell " +
                                "FROM orders o " +
                                "JOIN order_details od ON o.id = od.order_id " +
                                "WHERE o.id = :id";

    private static final String SQL_GET_ALL_ORDERS = 
                                "SELECT o.id as order_id, o.supplier_id, o.order_date, o.total_amount, " +
                                "od.good_id, od.quantity, od.price, od.warehouse_id, od.storage_cell " +
                                "FROM orders o " +
                                "JOIN order_details od ON o.id = od.order_id";
        
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrdersRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        var params = new MapSqlParameterSource();
        params.addValue("id", orderId);
        return jdbcTemplate.query(SQL_GET_ORDER_BY_ID, params, (rs, rowNum) -> {
            return new Order(
                rs.getInt("order_id"),
                rs.getInt("supplier_id"),
                rs.getInt("good_id"),
                rs.getInt("warehouse_id"),
                rs.getDate("order_date").toLocalDate(),
                rs.getInt("quantity"),
                rs.getBigDecimal("price"),
                rs.getBigDecimal("total_amount"),
                rs.getString("storage_cell")
            );
        }).stream().findFirst();
    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query(SQL_GET_ALL_ORDERS, (rs, rowNum) -> {
            return new Order(
                rs.getInt("order_id"),
                rs.getInt("supplier_id"),
                rs.getInt("good_id"),
                rs.getInt("warehouse_id"),
                rs.getDate("order_date").toLocalDate(),
                rs.getInt("quantity"),
                rs.getBigDecimal("price"),
                rs.getBigDecimal("total_amount"),
                rs.getString("storage_cell")
            );
        });
    }

    @Override
    public void makeOrder(int supplierId, int productId, int quantity, int warehouseId, String storageCell) {
        // Запрос для получения цены товара из supplier_goods
        String SQL_GET_PRICE = "SELECT price FROM supplier_goods WHERE id = :goodId";

        String SQL_ADD_ORDER = "INSERT INTO orders (supplier_id, order_date) VALUES (:supplierId, :orderDate) RETURNING id";

        String SQL_ADD_ORDER_DETAILS = "INSERT INTO order_details (order_id, good_id, quantity, warehouse_id, storage_cell, price) " +
                "VALUES (:orderId, :goodId, :quantity, :warehouseId, :storageCell, :price)";
        
        String SQL_CALCULATE_TOTAL_AMOUNT = "SELECT calculate_total_amount(:orderId)";

        String SQL_UPDATE_TOTAL_AMOUNT = "UPDATE orders SET total_amount = :totalAmount WHERE id = :orderId";


        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("supplierId", supplierId);
        params.addValue("orderDate", LocalDate.now());

        // Добавляем заказ и получаем его идентификатор
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_ADD_ORDER, params, keyHolder);
        int orderId = keyHolder.getKey().intValue();

        // Получаем цену товара из supplier_goods
        params.addValue("goodId", productId);
        BigDecimal price = jdbcTemplate.queryForObject(SQL_GET_PRICE, params, BigDecimal.class);

        // Добавляем детали заказа
        params.addValue("orderId", orderId);
        params.addValue("quantity", quantity);
        params.addValue("warehouseId", warehouseId);
        params.addValue("storageCell", storageCell);
        params.addValue("price", price); // Используем полученную цену товара

        jdbcTemplate.update(SQL_ADD_ORDER_DETAILS, params);

        BigDecimal totalAmount = jdbcTemplate.queryForObject(SQL_CALCULATE_TOTAL_AMOUNT, params, BigDecimal.class);

        params.addValue("totalAmount", totalAmount);
        jdbcTemplate.update(SQL_UPDATE_TOTAL_AMOUNT, params);
    }

    public String getProductName(int productId) {
        String SQL_GET_PRODUCT_NAME = "SELECT name FROM supplier_goods WHERE id = :productId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_NAME, params, String.class);
    }

    @Override
    public List<String> getOrdersCoordinates() {
        String SQL_GET_ORDERS_COORDINATES = "SELECT coordinates FROM order_details";
        return jdbcTemplate.query(SQL_GET_ORDERS_COORDINATES, (rs, rowNum) -> rs.getString("coordinates"));
    }
    
}
