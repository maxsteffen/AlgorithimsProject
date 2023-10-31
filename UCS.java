//UCS ALG for Group Project 
//Group # 2 @ Joe Loparco CS 3100
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Creates Node object
class Node {
    int[] state;
    Node parent;
    int[] action;
    int pathCost;

    public Node(int[] state, Node parent, int[] action, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
    }
}
// Creates Maze when properly formatted file is input
class Maze {
    int height;
    int width;
    int[] s0;
    int[] goal;
    Map<int[], Integer> costs;
    Set<int[]> walls;

    public Maze(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> contents = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }
            reader.close();
            this.height = contents.size();
            this.width = contents.stream().map(String::length).max(Integer::compareTo).orElse(0);
            this.costs = new HashMap<>();
            this.walls = new HashSet<>();

            for (int i = 0; i < this.height; i++) {
                String row = contents.get(i);
                for (int j = 0; j < this.width; j++) {
                    char cellValue = (j < row.length()) ? row.charAt(j) : ' ';
                    int[] position = {i, j};

                    if (cellValue == '.') {
                        this.s0 = position;
                        this.costs.put(position, 0);
                    } else if (cellValue == 'P') {
                        this.goal = position;
                        this.costs.put(position, 0);
                    } else if (cellValue == ' ') {
                        this.costs.put(position, 1);
                    } else if (Character.isDigit(cellValue)) {
                        this.costs.put(position, Character.getNumericValue(cellValue));
                    } else {
                        this.walls.add(position);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Provides list of possible actions for given state
    List<int[]> actions(int[] state) {
        int row = state[0];
        int col = state[1];
        int[][] candidates = {
            {0, -1},  // left
            {-1, 0},  // up
            {0, 1},   // right
            {1, 0}    // down
        };
        List<int[]> validActions = new ArrayList<>();

        for (int[] a : candidates) {
            int r = row + a[0];
            int c = col + a[1];
            int[] action = {a[0], a[1]};

            if (r >= 0 && r < this.height && c >= 0 && c < this.width && !walls.contains(action)) {
                validActions.add(action);
            }
        }

        return validActions;
    }
    //Returns results state given a state and action
    int[] result(int[] state, int[] action) {
        int r = state[0] + action[0];
        int c = state[1] + action[1];
        return new int[]{r, c};
    }
    //Returns ture if state is goal State
    boolean terminal(int[] state) {
        return Arrays.equals(state, goal);
    }
    //Solves the Maze... Hopefully... WIP
    void solve() {
        // Implement algorithm
    }
    //WIP
    void reconstructPath(Node node) {
        // Build the path from the goal to the start
    }
}
