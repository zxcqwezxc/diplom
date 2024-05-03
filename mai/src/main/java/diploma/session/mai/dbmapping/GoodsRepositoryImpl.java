package diploma.session.mai.dbmapping;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@ComponentScan(basePackages={"diploma"})

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

    private static final String SQL_GET_ITEM_BY_ID = 
                                "SELECT id, name, instorage, income, outcome, vendor_code FROM goods WHERE id = :id";

    private static final String SQL_GET_ALL_ITEMS = 
                                "SELECT * from goods";
    
    private static final String SQL_GET_ALL_WAREHOUSES = 
                                "SELECT * from warehouses";
        
    private final GoodsMapper goodsMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ChartDataMapper chartDataMapper;
    private final WarehouseMapper warehouseMapper;

    public GoodsRepositoryImpl(GoodsMapper goodsMapper, NamedParameterJdbcTemplate jdbcTemplate, ChartDataMapper chartDataMapper, WarehouseMapper warehouseMapper) {
        this.goodsMapper = goodsMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.chartDataMapper = chartDataMapper;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public Optional<Item> getItemById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(SQL_GET_ITEM_BY_ID, params, goodsMapper).stream().findFirst();
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_GET_ALL_ITEMS, goodsMapper);
    }

    @Override
    public void addItem(Item item) {
        String SQL_ADD_ITEM = "INSERT INTO goods (id, name, instorage, income, outcome, vendor_code) " +
                            "VALUES (:id, :name, :instorage, :income, :outcome, :vendorCode)";

        String SQL_GET_CURRENT_INSTORAGE = "SELECT instorage FROM goods WHERE id = :id";

        String SQL_UPDATE_INSTORAGE = "UPDATE goods SET instorage = :instorage WHERE id = :id";
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", item.id());
        params.addValue("name", item.name());
        params.addValue("income", item.income());
        params.addValue("outcome", item.outcome());
        params.addValue("vendorCode", item.vendorCode());
        params.addValue("instorage", 1); // Значение по умолчанию или извлекаемое из базы данных

        List<Integer> result = jdbcTemplate.queryForList(SQL_GET_CURRENT_INSTORAGE, params, Integer.class);

        if (result.isEmpty()) {
            // Значение instorage не существует, добавляем новую запись
            jdbcTemplate.update(SQL_ADD_ITEM, params);
        } else {
            // Значение instorage существует, обновляем его
            Integer currentInstorageCount = result.get(0); // Получаем первый элемент списка
            params.addValue("instorage", currentInstorageCount + 1);
            jdbcTemplate.update(SQL_UPDATE_INSTORAGE, params);
        }
    }   

    @Override
    public List<ChartData> getChartData() {
        final String SQL_GET_WAREHOUSES_DATA =
            "SELECT name, free_cells, occupied_cells FROM warehouses";
        return jdbcTemplate.query(SQL_GET_WAREHOUSES_DATA, chartDataMapper);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return jdbcTemplate.query(SQL_GET_ALL_WAREHOUSES, warehouseMapper);
    }

    @Override
    public List<Item> getAllItemsById(int stockId) {
        return jdbcTemplate.query(
                "SELECT id, name, income, instorage, outcome, vendor_code FROM goods WHERE warehouse_id = " + stockId,
                (resultSet, rowNum) -> new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("income"),
                        resultSet.getInt("instorage"),
                        resultSet.getInt("outcome"),
                        resultSet.getInt("vendor_code")
                )
        );
    }
}
