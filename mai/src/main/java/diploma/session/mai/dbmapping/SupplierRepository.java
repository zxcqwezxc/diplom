package diploma.session.mai.dbmapping;

import java.util.List;

public interface SupplierRepository {
    List<Supplier> findAll();
    Supplier getSupplierById(int id);
}
