package diploma.session.mai.dbmapping;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository {

    Optional<Item> getItemById(int id);
    List<Item> getAllItems();
    List<Item> getAllItemsById(int stockId);
    void addItem(Item item);
    List<ChartData> getChartData();
    List<Warehouse> getAllWarehouses();
}
