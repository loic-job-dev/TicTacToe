package fr.campus.loic.tictactoe.material;

public class Tile {

    private final int coordinateX;
    private final int coordinateY;
    private boolean hasPawn;
    private String representation;

    public Tile(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.hasPawn = false;
        this.representation = "   ";
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
        System.out.print(representation);
    }

    public void setPawn(boolean hasPawn) {
        this.hasPawn = hasPawn;
    }
    public void setRepresentation(String representation) {
        this.representation = " " + representation + " ";
    }
}
