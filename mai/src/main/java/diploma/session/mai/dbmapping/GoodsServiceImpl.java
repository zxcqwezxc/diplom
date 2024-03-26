package diploma.session.mai.dbmapping;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService{
    
    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Item getItem(int id) {
        return goodsRepository.getItemById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public List<Item> getAllItems() {
        return goodsRepository.getAllItems();
    }

    @Override
    public void addItem(Item item) {
        goodsRepository.addItem(item);
    }
}
