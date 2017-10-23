package MP1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int pick, choice;    // part 1 or 2, heuristic

        while (true){
            Maze maze, clone;
            Tile current;
            String fileName;
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n\tMAZE SEARCH HOORAY!\n1. Basic Pathfinding\n2. Search with Multiple Goals\n0. Exit");

            do {
                pick = -1;
                System.out.print("Enter choice here: ");

                try {
                    pick = scanner.nextInt();
                } catch (InputMismatchException ime) {
                    scanner.nextLine();
                    System.out.println("Please enter a valid input.");
                }
            } while (pick < 0 || pick > 2);

            switch (pick) {
                case 1:     // Basic Pathfinding
                    System.out.println("\n\tBASIC PATHFINDING\n1. Tiny Maze\n2. Small Maze\n3. Medium Maze\n4. Big Maze\n5. Open Maze\n0. Exit");

                    do {
                        choice = -1;
                        System.out.print("Enter choice here: ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }
                    } while (choice < 0 || choice > 5);

                    switch (choice) {       // File reading, initialize maze
                        case 1:     // Tiny Maze
                            maze = new Maze(7, 7);
                            fileName = "src/Mazes/tinyMaze.lay.txt";
                            break;
                        case 2:     // Small Maze
                            maze = new Maze(10, 22);
                            fileName = "src/Mazes/smallMaze.lay.txt";
                            break;
                        case 3:     // Medium Maze
                            maze = new Maze(18, 36);
                            fileName = "src/Mazes/mediumMaze.lay.txt";
                            break;
                        case 4:     // Big Maze
                            maze = new Maze(37, 37);
                            fileName = "src/Mazes/bigMaze.lay.txt";
                            break;
                        case 5:     // Open Maze
                            maze = new Maze(23, 37);
                            fileName = "src/Mazes/openMaze.lay.txt";
                            break;
                        default:    // Exit
                            return;
                    }
                    break;
                case 2:     // Search with Multiple Goals
                    System.out.println("\n\tSEARCH WITH MULTIPLE GOALS\n1. Small Search\n2. Medium Search\n3. Big Search\n4. Tricky Search\n0. Exit");

                    do {
                        choice = -1;
                        System.out.print("Enter choice here: ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }
                    } while (choice < 0 || choice > 4);

                    switch (choice) {       // File reading, initialize maze
                        case 1:     // Small Search
                            maze = new Maze(5, 20);
                            fileName = "src/Mazes/smallSearch.lay.txt";
                            break;
                        case 2:     // Medium Search
                            maze = new Maze(8, 31);
                            fileName = "src/Mazes/mediumSearch.lay.txt";
                            break;
                        case 3:     // Big Search
                            maze = new Maze(15, 31);
                            fileName = "src/Mazes/bigSearch.lay.txt";
                            break;
                        case 4:     // Tricky Search
                            maze = new Maze(7, 20);
                            fileName = "src/Mazes/trickySearch.lay.txt";
                            break;
                        default:    // Exit
                            return;
                    }
                    break;
                default:    // Exit
                    return;
            }

            System.out.println("\n\tHEURISTICS\n1. Manhattan Distance\n2. Straight-Line Distance\n0. Exit");

            do {
                choice = -1;
                System.out.print("Which heuristic should we use? ");

                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ime) {
                    scanner.nextLine();
                    System.out.println("Please enter a valid input.");
                }
            } while (choice < 0 || choice > 2);

            ArrayList<Tile> open = new ArrayList<>(), closed = new ArrayList<>(), goalsReached = new ArrayList<>(), goals;
            boolean clear = false;
            int pathCost = 0;

            maze.set(fileName);
            clone = maze;
            current = maze.getOrigin();
            goals = maze.goals(choice, current);
            Tile goal = (pick == 1)? maze.getGoal() : goals.get(0);

            current.editTile(0, goal, choice, null);
            open.add(current);

            while (true) {                                                                                              // kapoy na hunahuna huhuhuh
                current.setVisited(true);
                closed.add(current);
                ArrayList<Tile> next = maze.openPaths(current, choice, pick, goal);     // get possible paths
                ArrayList<Tile> toBeRemovedFromNext = new ArrayList<>(), toBeRemovedFromOpen = new ArrayList<>();

                for (Tile tileNext : next) {                                            // check if next possible path is in the open list already. if yes, check which has a lower g(n) cost
                    for (Tile tileOpen : open) {
                        if (tileNext.getRow() == tileOpen.getRow() && tileNext.getCol() == tileOpen.getCol()) {
                            if (tileNext.getGn() > tileOpen.getGn()) {
                                toBeRemovedFromNext.add(tileNext);
                            } else if (tileNext.getGn() < tileOpen.getGn()) {
                                toBeRemovedFromOpen.add(tileOpen);
                            }
                        }
                    }
                }

                next.removeAll(toBeRemovedFromNext);
                open.removeAll(toBeRemovedFromOpen);
                open.addAll(next);                              // add possible paths to open list
                open.sort((Tile o1, Tile o2) -> {
                    if (o1.getFn() < o2.getFn()) {              // Sort by f(n)
                        return -1;
                    } else if (o1.getFn() > o2.getFn()) {
                        return 1;
                    }

                    if (o1.getRow() < o2.getRow()) {            // if f(n) are equal, sort by row order
                        return -1;
                    } else if (o1.getRow() > o2.getRow()) {
                        return 1;
                    }

                    if (o1.getCol() < o2.getCol()) {            // if rows are also equal, sort by column order. sharo
                        return -1;
                    } else if (o1.getCol() > o2.getCol()) {
                        return 1;
                    }

                    return 0;
                });

                if (pick == 2) {
                    if (current.getType() == '.') {
                        goalsReached.add(current);
                    }

                    goals.clear();
                    goals = maze.goals(choice, current);

                    for (Tile temp1 : open) {
                        for (Tile temp2 : goals) {
                            if (temp1.equals(temp2)) {
                                temp2.setGn(temp1.getGn());
                                temp2.setFn();
                            }
                        }
                    }

                    goals.sort((o1, o2) -> {
                        if (o1.getFn() < o2.getFn()) {                                      // Sort by f(n)
                            return -1;
                        } else if (o1.getFn() > o2.getFn()) {
                            return 1;
                        }

                        if (o1.getRow() < o2.getRow()) {                                    // if f(n) are equal, sort by row order
                            return -1;
                        } else if (o1.getRow() > o2.getRow()) {
                            return 1;
                        }

                        if (o1.getCol() < o2.getCol()) {                                    // if rows are also equal, sort by column order. sharo
                            return -1;
                        } else if (o1.getCol() > o2.getCol()) {
                            return 1;
                        }

                        return 0;
                    });

                    if (!goals.isEmpty()) {
                        goal = goals.get(0);
                    }

                    pathCost++;
                }

                if ((pick == 1 && current.getType() == '.') || (pick == 2 && goals.isEmpty())) {     // check if you've reached goal
                    break;
                }

                for (Tile tileNext : open) {
                    if ((!tileNext.isVisited())) {
                        current = tileNext;
                        break;
                    }
                }
            }

            if (pick == 1) {
                clone.print(current);

                for (Tile temp = current; temp.getParent() != null; pathCost++) {       // get path cost
                    temp = temp.getParent();
                }
            } else {
                clone.print2(goalsReached);
            }

            System.out.println("\nPath Cost: " + pathCost + " (start excluded, goal/s included)\nNo. of nodes expanded: " + closed.size() + "\nSize of frontier: " + open.size());

            if (pick == 2) {
                System.out.print("Order of goals reached: ");

                for (int i = 0; i < goalsReached.size(); i++) {
                    System.out.print("[" + goalsReached.get(i).getRow() + ", " + goalsReached.get(i).getCol() + "]");

                    if (i < goalsReached.size() - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println();
            }

            scanner.nextLine();
            System.out.println("\nPress ENTER to continue");
            scanner.nextLine();
        }
    }
}