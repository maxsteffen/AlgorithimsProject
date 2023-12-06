// DFS Algorithm
// COSC 3100
// Group #2 - Tania Mishra

package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
	
	public static int[][] possibleActions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
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
	
	public static int dfs(char[][] maze, int mazeSize, int[] start) {
		Stack<int[]> stack = new Stack<int[]>();
		ArrayList<int[]> explored = new ArrayList<int[]>();
		int numExploredStates = 0;
				
		stack.push(start);

		while (!stack.isEmpty()) {
			int[] currentSpot = stack.pop();
			numExploredStates++;
			if (!isExplored(explored, currentSpot)) {
				for (int i = 0; i < possibleActions.length; i++) {
					int[] newSpot = nextCoordinate(currentSpot, possibleActions[i]);
					
					if (newSpot[0] < maze.length && newSpot[1] < maze[0].length) {		// if new spot is in maze boundaries
						if (maze[newSpot[0]][newSpot[1]] != '%' && !isExplored(explored, newSpot)) {						// if new spot is not a wall and not yet explored
							stack.add(newSpot);
						}
					}
				}
				explored.add(currentSpot);
			}
		}
		
		return numExploredStates;
		
		
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

		System.out.println("Original maze:");
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		
		double startTime = System.nanoTime();

		int numExploredStates = dfs(maze, mazeSize, start);
		
		long endTime   = System.nanoTime();
		double totalTime = (endTime - startTime)/1000;
		
		
		System.out.println("Number of explored states: " + numExploredStates);
		System.out.println("Runtime: " + totalTime);


	}
	

}
