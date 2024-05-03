package diploma.session.mai.dbmapping;

public record Warehouse(
    int id,
    String name,
    String location,
    int totalCells,
    int freeCells,
    int occupiedCells
    ) {
}
