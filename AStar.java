import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarMazeSolver {
    // Define cell directions: UP, DOWN, LEFT, RIGHT
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Define the new maze
    private static char[][] maze = {
        {'%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%'},
        {'%', ' ', ' ', ' ', ' ', '%', ' ', ' ', '%', ' ', ' ', '.'},
        {'%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', '%'},
        {'%', ' ', '%', '%', ' ', ' ', '%', ' ', '%', ' ', ' ', '%'},
        {'%', ' ', '%', '%', '%', ' ', '%', '%', '%', '%', ' ', '%'},
        {'%', ' ', '%', '%', '%', ' ', ' ', '%', '%', ' ', ' ', '%'},
        {'%', ' ', '%', '%', '%', '%', ' ', '%', '%', ' ', '%', '%'},
        {'%', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%'},
        {'%', '%', ' ', '%', '%', '%', '%', '%', '%', '%', ' ', '%'},
        {'%', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', '%'},
        {'%', '%', '%', '%', '%', 'E', '%', '%', '%', '%', '%', '%'},
    };

    public static void main(String[] args) {
        int exploredStates = aStarSearch(maze);
        System.out.println("Number of explored states: " + exploredStates);
    }

    public static int aStarSearch(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Create a priority queue for open nodes (cells)
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        // Create the start node and add it to the open set
        Node start = new Node(1, 1, 0, heuristic(1, 1, rows - 2, cols - 2) * 9);
        openSet.add(start);

        Set<Node> exploredSet = new HashSet<>(); // To keep track of explored nodes
        Set<Node> visitedSet = new HashSet<>(); // To keep track of visited nodes

        int exploredStates = 0; // Initialize the count of explored states

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            int x = current.x;
            int y = current.y;

            // Mark the current node as explored
            exploredSet.add(current);

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    int gScore = current.g + 1;
                    int hScore = heuristic(newX, newY, rows - 2, cols - 2) * 9;
                    int fScore = gScore + hScore;

                    Node neighbor = new Node(newX, newY, gScore, hScore);

                    // Check if the neighbor is not a wall ('%') and has not been explored
                    if (maze[newX][newY] != '%' && !exploredSet.contains(neighbor)) {
                        if (maze[newX][newY] == 'E') {
                            // If the neighbor is the exit, we're done
                            return exploredStates + 1; // Increment the count and return
                        }
                        openSet.add(neighbor);
                        visitedSet.add(neighbor);
                    }
                }
            }
            exploredStates++; // Increment the count of explored states
        }

        return -1; // No path found
    }
