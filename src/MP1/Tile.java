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

    public Tile() {

    }

    public Tile(char type, boolean visited, int row, int col) {
        this.row = row;
        this.col = col;
        setType(type);
        setVisited(visited);
        setGn(1);
        setHn(0);
        setFn();
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
        switch (choice) {
            case 1:     // Manhattan
                this.hn = abs(getRow() - goal.getRow()) + abs(getCol() - goal.getCol());
                break;
            case 2:     // Straight-Line
                this.hn = max(abs(getRow() - goal.getRow()), abs(getCol() - goal.getCol()));
        }
    }

    public double getFn() {
        return fn;
    }

    public void setFn() {
        this.fn = gn + hn;
    }
}
