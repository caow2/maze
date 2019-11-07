import java.util.Random;
import java.util.Stack;
import java.util.HashMap;
/*
 * Generates a maze using randomized DFS.
 */
public class DFSMazeGenerator extends MazeGenerator {

  public DFSMazeGenerator() {}

  /*
   * Generate a maze using a randomized depth first search approach.
   * Because of the nature of DFS, the maze will have long corridors
   * and will backtrack once we can't go anymore.
   */
  public MazePanel[][] generate(int length, int width) {
    //if(! validSize(length, width))
    //  throw new MazeSizeException(length, width);

    MazePanel[][] maze = init(length, width);
    boolean[][] seen = new boolean[length][width]; // track visited panel

    Random random = new Random();
    int[] start = { random.nextInt(length), random.nextInt(width) }; // choose a random start point
    Stack<int[]> st = new Stack<int[]>();
    st.push(start);

    HashMap<int[], int[]> parentMap = new HashMap<>(); // <child coordinate, direction of parent>

    while(! st.isEmpty()) {
      search(st, maze, seen, parentMap);
    }

    return maze;
  }

  private MazePanel[][] init(int length, int width) {
    MazePanel[][] maze = new MazePanel[length][width];
    for(int i = 0; i < length; i++) {
      for(int j = 0; j < width; j++) {
        maze[i][j] = new MazePanel();
      }
    }

    openMaze(maze);
    return maze;
  }

  /*
   * Return whether the given coordinate is valid for depth first search on the maze.
   */
  private boolean validCoordinate(int[] coord, boolean[][] seen) {
    int x = coord[0], y = coord[1];
    return x >= 0 && x < seen.length && y >= 0 && y < seen[0].length && ! seen[x][y];
  }

  /*
   * Uses the top element of the DFS Stack to search for random adjacent neighbors and
   * continue the DFS.
   */
  private void search(Stack<int[]> st, MazePanel[][] maze, boolean[][] seen,
                      HashMap<int[], int[]> parentMap) {
    int[] coord = st.pop();
    int x = coord[0], y = coord[1];
    if(seen[x][y])
      return; // already visited
    seen[x][y] = true;

    // break down walls of parent MazePanel and the current MazePanel
    if(parentMap.get(coord) != null) {
      int[] parentCoord = parentMap.get(coord);
      breakDownWalls(maze, x, y, parentCoord[0], parentCoord[1]);
      parentMap.remove(coord);
    }

    // neighbors ordered up, down, left, right to match MazePanel walls
    int[][] neighbors = {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}};
    shuffle(neighbors);
    for(int i = 0; i < neighbors.length; i++) {
      int[] n = neighbors[i];
      if(validCoordinate(n, seen)) {
        parentMap.put(n, coord); // parentMap will always have most recent mapping
        st.push(n);
      }
    }
  }

  /*
   * Breaks down the walls between two adjacent MazePanels of coordinates {x,y}
   * and {parentX, parentY}
   */
  private void breakDownWalls(MazePanel[][] maze, int x, int y, int parentX, int parentY) {
    if(x == parentX) {
      if(parentY > y) {   // parent is {x, y + 1}
        maze[x][y].breakDownWall(MazePanel.RIGHT);
        maze[parentX][parentY].breakDownWall(MazePanel.LEFT);
      }
      else {  // parent is {x, y - 1}
        maze[x][y].breakDownWall(MazePanel.LEFT);
        maze[parentX][parentY].breakDownWall(MazePanel.RIGHT);
      }
    }
    else {
      if(parentX > x) { // parent is {x + 1, y}
        maze[x][y].breakDownWall(MazePanel.DOWN);
        maze[parentX][parentY].breakDownWall(MazePanel.UP);
      }
      else { // parent is {x - 1, y}
        maze[x][y].breakDownWall(MazePanel.UP);
        maze[parentX][parentY].breakDownWall(MazePanel.DOWN);
      }
    }
  }

  /*
   * Shuffle the input array and return an array of indices with respect to the original ordering.
   * Based on Fisher-Yates algorithm.
   */
  private Integer[] shuffle(Object[] arr) {
    Integer[] indices = new Integer[arr.length];
    for(int i = 0; i < arr.length; i++) {
      indices[i] = i;
    }

    Random r = new Random();
    for(int i = 0; i < arr.length; i++) {
      int idx = r.nextInt(arr.length - i) + i; // idx is [i, arr.length - 1]
      swap(arr, i, idx);
      swap(indices, i, idx);
    }

    return indices;
  }

  /*
   * Swap two indices in the array.
   */
  private void swap(Object[] arr, int i, int j) {
    Object temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
