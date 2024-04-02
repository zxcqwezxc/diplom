package diploma.session.mai.dbmapping;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(int id);
    List<SupplierGoods> getAllSupplierGoods(int supplierId);
}
