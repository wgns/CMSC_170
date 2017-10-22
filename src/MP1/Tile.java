package MP1;

import static java.lang.Math.*;

public class Tile {
    private char type;
    private boolean visited;
    private int row;
    private int col;
    private double gn;
    private double hn;
    private double fn;
    private Tile parent;

    public Tile() {

    }

    public Tile(char type, int row, int col) {                                  // tile initialization
        this.row = row;
        this.col = col;
        setType(type);
        setVisited(false);
        setGn(0);
        setHn(0);
        setFn();
        setParent(null);
    }

    public void editTile(double gn, Tile goal, int choice, Tile parent) {        // for possible paths
        setGn(gn);
        setHn(goal, choice);
        setFn();
        setParent(parent);
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getGn() {
        return gn;
    }

    public void setGn(double gn) {
        this.gn = gn;
    }

    public double getHn() {
        return hn;
    }

    public void setHn(double hn) {
        this.hn = hn;
    }

    public void setHn(Tile goal, int choice) {
        hn = (choice == 1)? (abs(getRow() - goal.getRow()) + abs(getCol() - goal.getCol())) : (max(abs(getRow() - goal.getRow()), abs(getCol() - goal.getCol())));
    }

    public double getFn() {
        return fn;
    }

    public void setFn() {
        fn = gn + hn;
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }
}
