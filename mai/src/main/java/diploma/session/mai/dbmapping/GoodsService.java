package diploma.session.mai.dbmapping;

import java.util.List;

public interface GoodsService {
    
    Item getItem(int id);
    List<Item> getAllItems();
    void addItem(Item item);
}
