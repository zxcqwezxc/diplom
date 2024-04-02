package diploma.session.mai.dbmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        return new Order(
            rs.getInt("id"),
            rs.getInt("supplier_id"),
            rs.getInt("good_id"),
            rs.getInt("warehouse_id"),
            rs.getDate("order_date").toLocalDate(),
            rs.getInt("quantity"),
            rs.getBigDecimal("price"),
            rs.getBigDecimal("total_amount"),
            rs.getString("storage_cell")



        );
    }
    
}
