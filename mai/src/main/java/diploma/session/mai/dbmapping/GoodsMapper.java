package diploma.session.mai.dbmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GoodsMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        return new Item(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("income"),
            rs.getInt("instorage"),
            rs.getInt("outcome"),
            rs.getInt("vendor_code")
        );
    }
    
}
