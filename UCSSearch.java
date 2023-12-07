//Joe Loparco 12/6/2023
//CS 3100 Group Project Comparison of Search Algorithms
package bruh;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class UCSSearch {
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

    private static class Node {
        int row, col, cost;

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    private static Map<Node, Node> parentMap;
    
    public static int[] ucs() {
        int rows = maze.length;
        int cols = maze[0].length;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        boolean[][] visited = new boolean[rows][cols];

        // Define the starting point
        Node startNode = new Node(1, 11, 0);
        pq.add(startNode);

        int exploredStates = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            // Check if we reached the exit
            if (maze[currentNode.row][currentNode.col] == 'E') {
                return new int[]{currentNode.cost, exploredStates};
            }

            // Skip if already visited
            if (visited[currentNode.row][currentNode.col]) {
                continue;
            }

            // Mark the current node as visited
            visited[currentNode.row][currentNode.col] = true;
            exploredStates++;

            // Explore neighbors
            exploreNeighbor(pq, visited, currentNode.row - 1, currentNode.col, currentNode.cost + 1);
            exploreNeighbor(pq, visited, currentNode.row + 1, currentNode.col, currentNode.cost + 1);
            exploreNeighbor(pq, visited, currentNode.row, currentNode.col - 1, currentNode.cost + 1);
            exploreNeighbor(pq, visited, currentNode.row, currentNode.col + 1, currentNode.cost + 1);
        }

        // If the loop completes without finding the exit, return -1
        return new int[]{-1, exploredStates, -1};
    }

    private static void exploreNeighbor(PriorityQueue<Node> pq, boolean[][] visited, int row, int col, int cost) {
        int rows = maze.length;
        int cols = maze[0].length;

        if (row >= 0 && row < rows && col >= 0 && col < cols && maze[row][col] != '%' && !visited[row][col]) {
            Node neighbor = new Node(row, col, cost);
            pq.add(neighbor);
        }
    }   

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] result = ucs();
        long stop = System.currentTimeMillis();
        if (result[0] != -1) {
            System.out.println("Shortest path cost: " + result[0]);
            System.out.println("Explored states: " + result[1]);
            System.out.println("Time taken: " + (stop - start) + " milliseconds");
        } else {
            System.out.println("No path found to the exit.");
        }
    }
}