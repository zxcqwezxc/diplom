package diploma.session.mai.dbmapping;

import java.util.List;

public interface GoodsService {
    
    Item getItem(int id);
    List<Item> getAllItems();
    List<Item> getAllItemsById(int stockId);
    void addItem(Item item);
    List<ChartData> getChartData();
    List<Warehouse> getAllWarehouses();
}
