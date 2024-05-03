package diploma.session.mai.dbmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ChartDataMapper implements RowMapper<ChartData> {

    @Override
    public ChartData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChartData(
            rs.getString("name"),
            rs.getInt("free_cells"),
            rs.getInt("occupied_cells")
        );
    }
}