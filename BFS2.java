import java.util.*;

public class BFS2 {

	public static int[][] possibleActions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void printMaze(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printMazeInt(int[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				
				if(maze[i][j] == 0)
					System.out.print("-  ");
				else if (maze[i][j] < 10)
					System.out.print(maze[i][j] + "  ");
				else
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
		int[] newSpot = { currentSpot[0] + action[0], currentSpot[1] + action[1] };
		return newSpot;
	}

	public static void bfs(char[][] maze, int[] start) {

		Queue<int[]> queue = new LinkedList<int[]>();
		ArrayList<int[]> explored = new ArrayList<int[]>();
		int numExploredStates = 0;

		int[][] order = new int[maze.length][maze.length];

		queue.add(start);

		while (!queue.isEmpty()) {
			int[] currentSpot = queue.remove();
			numExploredStates++;
			order[currentSpot[0]][currentSpot[1]] = numExploredStates;

			if (!isExplored(explored, currentSpot)) {
				if (isExit(maze, currentSpot)) {
					break;
				}

				for (int i = 0; i < possibleActions.length; i++) {

					int[] newSpot = nextCoordinate(currentSpot, possibleActions[i]);

					if (newSpot[0] < maze.length && newSpot[1] < maze[0].length) {

						if (maze[newSpot[0]][newSpot[1]] != '%' && !isExplored(explored, newSpot)) {
							queue.add(newSpot);
						}

					}
				}

				explored.add(currentSpot);
			}
		}
		
		System.out.println("Traversed Array: ");
		printMazeInt(order);
		System.out.println();

		System.out.println("Number of explored states: " + numExploredStates);

	}

	public static void main(String[] args) {
		char[][] maze = { { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', ' ', '%', ' ', ' ', '%', ' ', ' ', '.' },
				{ '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', '%' },
				{ '%', ' ', '%', '%', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', '%', '%', '%', ' ', '%', '%', '%', '%', ' ', '%' },
				{ '%', ' ', '%', '%', '%', ' ', ' ', '%', '%', ' ', ' ', '%' },
				{ '%', ' ', '%', '%', '%', '%', ' ', '%', '%', ' ', '%', '%' },
				{ '%', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', ' ', '%', '%', '%', '%', '%', '%', '%', ' ', '%' },
				{ '%', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', '%', '%', '%', ' ', '%', '%', '%', '%', '%', '%' },
				{ '%', '%', '%', '%', '%', 'E', '%', '%', '%', '%', '%', '%' } };

		int[] start = { 1, 11 };

		System.out.println("Original Maze: ");
		printMaze(maze);
		System.out.println();
		
		long startTime = System.nanoTime();
		bfs(maze, start);
		long endTime = System.nanoTime();

		System.out.println("Time (ms): " + ((double) endTime - startTime) / 1000000);
	}

}
