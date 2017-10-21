package MP1;

import java.util.*;

public class Maze {
    private Tile[][] board;
    private int rows;
    private int cols;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Tile[rows][cols];
    }

    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    public void setTile(Tile tile) {
        board[tile.getRow()][tile.getCol()] = tile;
    }

    public void setRow(int i, String string) {
        for (int j = 0; j < cols; j++) {
            board[i][j] = new Tile(string.charAt(j), false, i, j);
        }
    }

    public Tile getOrigin() {
        Tile tile = new Tile();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == 'P') {
                    tile = board[i][j];
                }
            }
        }

        return tile;
    }

    public Tile getGoal() {
        Tile tile = new Tile();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == '.') {
                    tile = board[i][j];
                }
            }
        }

        return tile;
    }

    public List<Tile> getVisited() {
        List<Tile> visited = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].isVisited()) {
                    visited.add(board[i][j]);
                }
            }
        }

        return visited;
    }

    public List<Tile> openPaths(Tile tile, int choice) {
        List<Tile> paths = new ArrayList<>(4);

        if ((board[tile.getRow() - 1][tile.getCol()].getType() == ' ' || board[tile.getRow() - 1][tile.getCol()].getType() == '.') && !board[tile.getRow() - 1][tile.getCol()].isVisited()) {     // Up
            board[tile.getRow() - 1][tile.getCol()].setGn(tile.getGn() + 1);
            board[tile.getRow() - 1][tile.getCol()].setHn(getGoal(), choice);
            board[tile.getRow() - 1][tile.getCol()].setFn();
            paths.add(board[tile.getRow() - 1][tile.getCol()]);
        }

        if ((board[tile.getRow()][tile.getCol() - 1].getType() == ' ' || board[tile.getRow()][tile.getCol() - 1].getType() == '.') && !board[tile.getRow()][tile.getCol() - 1].isVisited()) {
            board[tile.getRow()][tile.getCol() - 1].setGn(tile.getGn() + 1);
            board[tile.getRow()][tile.getCol() - 1].setHn(getGoal(), choice);
            board[tile.getRow()][tile.getCol() - 1].setFn();
            paths.add(board[tile.getRow()][tile.getCol() - 1]);
        }

        if ((board[tile.getRow()][tile.getCol() + 1].getType() == ' ' || board[tile.getRow()][tile.getCol() + 1].getType() == '.') && !board[tile.getRow()][tile.getCol() + 1].isVisited()) {
            board[tile.getRow()][tile.getCol() + 1].setGn(tile.getGn() + 1);
            board[tile.getRow()][tile.getCol() + 1].setHn(getGoal(), choice);
            board[tile.getRow()][tile.getCol() + 1].setFn();
            paths.add(board[tile.getRow()][tile.getCol() + 1]);
        }

        if ((board[tile.getRow() + 1][tile.getCol()].getType() == ' ' || board[tile.getRow() + 1][tile.getCol()].getType() == '.') && !board[tile.getRow() + 1][tile.getCol()].isVisited()) {
            board[tile.getRow() + 1][tile.getCol()].setGn(tile.getGn() + 1);
            board[tile.getRow() + 1][tile.getCol()].setHn(getGoal(), choice);
            board[tile.getRow() + 1][tile.getCol()].setFn();
            paths.add(board[tile.getRow() + 1][tile.getCol()]);
        }

        Collections.sort(paths, (o1, o2) -> o1.getFn() < o2.getFn() ? -1 : o1.getFn() == o2.getFn() ? 0 : 1);

        return paths;
    }
}