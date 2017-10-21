package MP1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\tMAZE SEARCH HOORAY!\n1. Basic Pathfinding\n2. Search with Multiple Goals\n0. Exit");

            do {
                System.out.print("\nEnter choice here: ");

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
                        System.out.print("\nEnter choice here: ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }
                    } while (choice < 0 || choice > 5);

                    switch (choice) {
                        case 1:     // Tiny Maze
                            break;
                        case 2:     // Small Maze
                            break;
                        case 3:     // Medium Maze
                            break;
                        case 4:     // Big Maze
                            break;
                        case 5:     // Open Maze
                            break;
                        default:    // Exit
                            return;
                    }
                    break;
                case 2:     // Search with Multiple Goals
                    System.out.println("\n\tSEARCH WITH MULTIPLE GOALS\n1. Small Search\n2. Medium Search\n3. Big Search\n4. Tricky Search\n0. Exit");

                    do {
                        System.out.print("\nEnter choice here: ");

                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException ime) {
                            scanner.nextLine();
                            System.out.println("Please enter a valid input.");
                        }

                        switch (choice) {
                            case 1:     // Small Search
                                break;
                            case 2:     // Medium Search
                                break;
                            case 3:     // Big Search
                                break;
                            case 4:     // Tricky Search
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
