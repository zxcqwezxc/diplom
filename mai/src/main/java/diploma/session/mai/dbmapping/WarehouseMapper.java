package diploma.session.mai.dbmapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class WarehouseMapper implements RowMapper<Warehouse> {
    @Override
    public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Warehouse(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("location"),
            rs.getInt("total_cells"),
            rs.getInt("free_cells"),
            rs.getInt("occupied_cells")
        );
    }
}
