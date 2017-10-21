package MP1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maze maze;
        String fileName;
        int choice = -1;

        do {
            System.out.println("\tMAZE SEARCH HOORAY!\n1. Basic Pathfinding\n2. Search with Multiple Goals\n0. Exit");

            do {
                System.out.print("Enter choice here: ");

                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ime) {
                    scanner.nextLine();
                    System.out.println("Please enter a valid input.");
                }
            } while (choice < 0 || choice > 2);

            switch (choice) {
                case 1:     // Basic Pathfinding
                    System.out.println("\n\tBASIC PATHFINDING\n1. Tiny Maze\n2. Small Maze\n3. Medium Maze\n4. Big Maze\n5. Open Maze\n0. Exit");

                    do {
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

                    System.out.println("\n\tHEURISTICS\n1. Manhattan Distance\n2. Straight-Line Distance\n0. Exit");

                    do {
                        System.out.print("Which heuristic should we use? ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }
                    } while (choice < 0 || choice > 2);

                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            return;
                    }
                    break;
                case 2:     // Search with Multiple Goals
                    System.out.println("\n\tSEARCH WITH MULTIPLE GOALS\n1. Small Search\n2. Medium Search\n3. Big Search\n4. Tricky Search\n0. Exit");

                    do {
                        System.out.print("Enter choice here: ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }

                        switch (choice) {       // File reading, initialize maze
                            case 1:     // Small Search
                                maze = new Maze(5, 20);
                                fileName = "src/Mazes/smallSearch.lay.txt";
                                break;
                            case 2:     // Medium Search
                                maze = new Maze(15, 31);
                                fileName = "src/Mazes/mediumSearch.lay.txt";
                                break;
                            case 3:     // Big Search
                                maze = new Maze(29, 31);
                                fileName = "src/Mazes/bigSearch.lay.txt";
                                break;
                            case 4:     // Tricky Search
                                maze = new Maze(7, 20);
                                fileName = "src/Mazes/trickySearch.lay.txt";
                                break;
                            default:    // Exit
                                return;
                        }
                    } while (choice < 0 || choice > 4);
                    break;
                default:    // Exit
                    return;
            }
        } while (choice != 0);
    }
}