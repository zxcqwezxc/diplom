package diploma.session.mai.dbmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Supplier> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, contact_email, contact_phone, type, description, rating, registration_date, last_order_date, delivery_terms, additional_info FROM suppliers",
                (resultSet, rowNum) -> new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact_email"),
                        resultSet.getString("contact_phone"),
                        resultSet.getString("type"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("rating"),
                        resultSet.getDate("registration_date").toLocalDate(),
                        resultSet.getDate("last_order_date").toLocalDate(),
                        resultSet.getString("delivery_terms"),
                        resultSet.getString("additional_info")
                )
        );
    }

    @Override
    public Supplier getSupplierById(int id) {
        String query = "SELECT id, name, contact_email, contact_phone, type, description, rating, registration_date, last_order_date, delivery_terms, additional_info " +
                       "FROM suppliers " +
                       "WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) ->
            new Supplier(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("type"),
                resultSet.getString("description"),
                resultSet.getBigDecimal("rating"),
                resultSet.getDate("registration_date").toLocalDate(),
                resultSet.getDate("last_order_date").toLocalDate(),
                resultSet.getString("delivery_terms"),
                resultSet.getString("additional_info")
            ),
            id
        );
    }

    //TODO поправить запрос, чтобы была защита от SQL иньекций
    @Override
    public List<SupplierGoods> getAllSupplierGoods(int supplierId) {
        return jdbcTemplate.query(
                "SELECT id, supplier_id, name, description, price FROM supplier_goods WHERE supplier_id = " + supplierId,
                (resultSet, rowNum) -> new SupplierGoods(
                        resultSet.getInt("id"),
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                )
        );
    }
}
