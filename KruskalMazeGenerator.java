import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/*
 * Maze Generator based on randomized Kruskal's algorithm.
 */
public class KruskalMazeGenerator extends MazeGenerator {
  public KruskalMazeGenerator() {
  }

  public MazePanel[][] generate(int length, int width) {
    MazePanel[][] maze = init(length, width);

    int numElements = length * width; // number of elements in the maze
    UnionFind uf = new UnionFind(numElements);

    Map<Integer, CoordinatePair> map = generateEdges(maze);
    shuffleMap(map);
    //connect randomized edges b/w components if possible
    for(int i = 0; uf.numComponents() > 1; i++) {
      CoordinatePair cp = map.remove(i);
      int compX = getComponentNumber(cp.getKey(), maze), compY = getComponentNumber(cp.getVal(), maze);

      // only break down walls if they are not connected
      if(uf.union(compX, compY)) {
        int x1 = cp.getKey()[0], y1 = cp.getKey()[1], x2 = cp.getVal()[0], y2 = cp.getVal()[1];
        breakDownWalls(maze, x1, y1, x2, y2);
      }
    }

    return maze;
  }


  /**
   * Create a collection of CoordinatePair between adjacent coordinates in the maze.
   * A CoordinatePair represents a bidirectional edge.
   * To avoid redundant edges and keeping track of processed edges, we will only
   * consider the following edges for each coordinate (x,y):
   * 1. (x, y + 1) left
   * 2. (x + 1, y) down
   *
   * We process the maze grid from left to right, top to bottom.
   * A Map is used where the key is a 0-based index and the value is a CoordinatePair.
   */
  private Map<Integer, CoordinatePair> generateEdges(MazePanel[][] maze) {
    HashMap<Integer, CoordinatePair> map = new HashMap<Integer, CoordinatePair>();
    int index = 0;

    for(int i = 0; i < maze.length; i++) {
      for(int j = 0; j < maze[0].length; j++) {
        int[] src = {i, j}, left = {i, j + 1}, down = {i + 1, j};

        if(validCoordinate(left, maze))
          map.put(index++, new CoordinatePair(src, left));
        if(validCoordinate(down, maze))
          map.put(index++, new CoordinatePair(src, down));
      }
    }

    return map;
  }

  /**
   * Shuffle the CoordinatePairs within the map.
   * Based on Fisher-Yates algorithm. Similar to shuffle() from DFSMazeGenerator.
   */
  private void shuffleMap(Map<Integer, CoordinatePair> map) {
    Random r = new Random();
    for(int i = 0; i < map.size(); i++) {
      int idx = r.nextInt(map.size() - i) + i; // random idx b/w [i, map.size() - 1]
      // swap values for indices
      CoordinatePair pair =  map.get(i);
      map.put(i, map.get(idx));
      map.put(idx, pair);
    }
  }

  /**
   * Check if a coordinate pair is within the bounds of the maze.
   */
  private boolean validCoordinate(int[] coordinate, MazePanel[][] maze) {
    int x = coordinate[0], y = coordinate[1];
    return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
  }

  /**
   * Calculate the UnionFind component number for this coordinate.
   * i.e. in 3 x 3 grid, [0,0] = 0, [0,1] = 1, [1,0] = 3
   */
  private int getComponentNumber(int[] coordinate, MazePanel[][] maze) {
    if(! validCoordinate(coordinate, maze))
      return -1;

    int x = coordinate[0], y = coordinate[1];
    return (x * maze[0].length) + y;
  }
}
