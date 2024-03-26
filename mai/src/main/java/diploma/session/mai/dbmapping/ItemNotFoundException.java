package diploma.session.mai.dbmapping;

public class ItemNotFoundException extends RuntimeException {
    
    private final int id;

    public ItemNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Item with id = " + id + " not found";
    }
}
