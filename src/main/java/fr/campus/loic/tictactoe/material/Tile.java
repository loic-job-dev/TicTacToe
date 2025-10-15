package fr.campus.loic.tictactoe.material;

public class Tile {

    private final int coordinateX;
    private final int coordinateY;
    private boolean hasPawn;

    public Tile(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.hasPawn = false;
    }

    public int getCoordinateX() {
        return coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }
    public boolean hasPawn() {
        return hasPawn;
    }

    public void getRepresentation() {
        System.out.print("   ");
    }
}
