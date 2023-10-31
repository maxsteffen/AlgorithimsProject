package Project;

import java.util.*;

public class BFS {
	
	public static int[][] possibleActions = {{0,1}, {1,0}, {0,-1}, {-1,0}};


	public static void printMaze(char[][] maze) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean isExplored(ArrayList<int[]> explored, int[] currentSpot) {
		boolean found = false;
		
		for (int i = 0; i < explored.size(); i++) {
			if (Arrays.toString(explored.get(i)).equals(Arrays.toString(currentSpot))) {
				found = true;
			}
		}
		
		
		return found;
	}

	public static boolean isExit(char[][] maze, int[] currentSpot) {
		boolean exit = false;
		
		if (maze[currentSpot[0]][currentSpot[1]] == 'E') {
			exit = true;
		}
		
		return exit;
	}
		
	public static int[] nextCoordinate(int[] currentSpot, int[] action) {
		int[] newSpot = {currentSpot[0]+action[0], currentSpot[1]+action[1]};
		return newSpot;
	}
	

	public static void bfs(char[][] maze, int mazeSize, int[] start) {
		Queue<int[]> queue = new LinkedList<int[]>();
		ArrayList<int[]> explored = new ArrayList<int[]>();
		int numExploredStates = 0;
		
		printMaze(maze);
		
		queue.add(start);

		while (!queue.isEmpty()) {
			int[] currentSpot = queue.remove();
			numExploredStates++;
			System.out.println("current spot" + Arrays.toString(currentSpot));
			if (!isExplored(explored, currentSpot)) {
				System.out.println("NOT EXPLORED");
				if (isExit(maze, currentSpot)) {
					System.out.println("AT EXIT");
					break;
				}
				for (int i = 0; i < possibleActions.length; i++) {
					int[] newSpot = nextCoordinate(currentSpot, possibleActions[i]);
					System.out.println(Arrays.toString(newSpot));
					
					if (newSpot[0] < maze.length && newSpot[1] < maze[0].length) {		// if new spot is in maze boundaries
						System.out.println("in boundaries");
						if (maze[newSpot[0]][newSpot[1]] != '%' && !isExplored(explored, newSpot)) {						// if new spot is not a wall and not yet explored
							System.out.println("not wall");
							queue.add(newSpot);
						}
					}
				}
				explored.add(currentSpot);
			}
		}
		
		System.out.println("Number of explored states: " + numExploredStates);
		
		
	}
	
	
	public static void main(String[] args) {
		char[][] maze = {
			{'%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%'},
			{'%', ' ', ' ', ' ', ' ', '%', ' ', ' ', '%', ' ', ' ', '.'},
			{'%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', '%'},
			{'%', ' ', '%', '%', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%'},
			{'%', ' ', '%', '%', '%', ' ', '%', '%', '%', '%', ' ', '%'},
			{'%', ' ', '%', '%', '%', ' ', ' ', '%', '%', ' ', ' ', '%'},
			{'%', ' ', '%', '%', '%', '%', ' ', '%', '%', ' ', '%', '%'},
			{'%', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%'},
			{'%', '%', ' ', '%', '%', '%', '%', '%', '%', '%', ' ', '%'},
			{'%', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', '%'},
			{'%', ' ', '%', '%', '%', ' ', '%', '%', '%', '%', '%', '%'},
			{'%', '%', '%', '%', '%', 'E', '%', '%', '%', '%', '%', '%'}
		};

		
		int mazeSize = maze.length * maze[0].length;
		int[] start = {1,11};
		//int[] end = {11, 5};
		
		bfs(maze, mazeSize, start);
	}

}

