package MP1;

import java.io.*;
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

    public void setRow(int i, String string) {
        for (int j = 0; j < cols; j++) {
            board[i][j] = new Tile(string.charAt(j), i, j);
        }
    }

    public void set(String fileName) {
        BufferedReader br = null;

        try {
            String current;
            br = new BufferedReader(new FileReader(fileName));

            for (int i = 0; (current = br.readLine()) != null; i++) {
                this.setRow(i, current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
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

    public ArrayList<Tile> goals(int choice, Tile tile) {                       // for part two
        ArrayList<Tile> goals = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == '.' && !board[i][j].isVisited()) {
                    board[i][j].setHn(tile, choice);
                    board[i][j].setFn();
                    goals.add(board[i][j]);
                }
            }
        }

        return goals;
    }

    public ArrayList<Tile> openPaths(Tile tile, int choice, int pick, Tile goal) {           // By row order, then by column, so ULRD order
        ArrayList<Tile> paths = new ArrayList<>(4);                             // for multiple goals, pwede muagi balik (?) sa visited tiles so...

        if ((board[tile.getRow() - 1][tile.getCol()].getType() == ' ' || board[tile.getRow() - 1][tile.getCol()].getType() == '.') && !(pick == 1 && board[tile.getRow() - 1][tile.getCol()].isVisited())) {   // Up
            board[tile.getRow() - 1][tile.getCol()].editTile(tile.getGn() + 1, goal, choice, tile);
            paths.add(board[tile.getRow() - 1][tile.getCol()]);
        }

        if ((board[tile.getRow()][tile.getCol() - 1].getType() == ' ' || board[tile.getRow()][tile.getCol() - 1].getType() == '.') && !(pick == 1 && board[tile.getRow()][tile.getCol() - 1].isVisited())) {   // Left
            board[tile.getRow()][tile.getCol() - 1].editTile(tile.getGn() + 1, goal, choice, tile);
            paths.add(board[tile.getRow()][tile.getCol() - 1]);
        }

        if ((board[tile.getRow()][tile.getCol() + 1].getType() == ' ' || board[tile.getRow()][tile.getCol() + 1].getType() == '.') && !(pick == 1 && board[tile.getRow()][tile.getCol() + 1].isVisited())) {   // Right
            board[tile.getRow()][tile.getCol() + 1].editTile(tile.getGn() + 1, goal, choice, tile);
            paths.add(board[tile.getRow()][tile.getCol() + 1]);
        }

        if ((board[tile.getRow() + 1][tile.getCol()].getType() == ' ' || board[tile.getRow() + 1][tile.getCol()].getType() == '.') && !(pick == 1 && board[tile.getRow() + 1][tile.getCol()].isVisited())) {   // Down
            board[tile.getRow() + 1][tile.getCol()].editTile(tile.getGn() + 1, goal, choice, tile);
            paths.add(board[tile.getRow() + 1][tile.getCol()]);
        }

        Collections.sort(paths, Comparator.comparingDouble(Tile::getFn));

        return paths;
    }

    public void print(Tile current) {
        while (current.getParent() != null) {
            if (current.getType() == ' ') {
                current.setType('.');
            }

            current = current.getParent();
        }

        for (int i = 0; i < rows; i++) {
            System.out.println();

            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j].getType());
            }
        }
    }

    public void print2(ArrayList<Tile> goalsReached) {              // it works afaik
        for (int goalNum = 1, i = 0; i < goalsReached.size(); goalNum++, i++) {
            Tile current = goalsReached.get(i);

            if (goalNum < 10) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum + 48));
            } else if (goalNum < 36) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum + 87));
            } else if (goalNum < 62) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum + 29));
            } else if (goalNum < 71) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 13));
            } else if (goalNum < 97) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum + 26));
            } else if (goalNum < 123) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 32));
            } else if (goalNum < 132) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 74));
            } else if (goalNum < 158) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 35));
            } else if (goalNum < 184) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 93));
            } else if (goalNum < 193) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 135));
            } else if (goalNum < 219) {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 96));
            } else {
                board[current.getRow()][current.getCol()].setType((char) (goalNum - 154));
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == ' ' && board[i][j].isVisited()) {
                    board[i][j].setType('.');
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            System.out.println();

            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j].getType());
            }
        }
    }
}