import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.HashSet;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
/*
 * Maze generator using a randomized Prim's algorithm.
 * For cells that are adjacent to the maze but not currently in it,
 * we assign a random weight for the edge to simulate randomly choosing
 * a cell to include.
 * A CoordinatePair is used to map each Coordinate to its parent so we can break down walls.
 */
public class PrimMazeGenerator extends MazeGenerator {
  public MazePanel[][] generate(int length, int width) {
    MazePanel[][] maze = init(length, width);
    boolean[][] seen = new boolean[length][width]; // ignore cells already seen
    PriorityQueue<Entry<Integer, CoordinatePair>> pq = new PriorityQueue<>(
          (x,y) -> x.getKey() - y.getKey()); // lower weight has higher priority
    Random r = new Random();

    int[] coord = { r.nextInt(length), r.nextInt(width) }; // src coordinate
    // a src coordinate has no parent
    pq.offer(new SimpleEntry<Integer, CoordinatePair>(r.nextInt(), new CoordinatePair(coord, null)));

    while(! pq.isEmpty()) {
      CoordinatePair cp = pq.poll().getValue();
      coord = cp.getKey();
      System.out.println(Arrays.toString(coord));

      int[] parent = cp.getVal();
      int x = coord[0], y = coord[1];
      if(seen[x][y])
        continue; // ignore this one

      seen[x][y] = true;
      if(parent != null)
        breakDownWalls(maze, x, y, parent[0], parent[1]);

      populate(seen, coord, pq);
    }

    return maze;
  }

  private void populate(boolean[][] seen, int[] parent,
   PriorityQueue<Entry<Integer, CoordinatePair>> pq) {
    int x = parent[0], y = parent[1];
    int[][] neighbors = {{x + 1, y}, {x - 1, y}, {x, y + 1}, {x, y - 1}};

    Random r = new Random();
    for(int[] n : neighbors) {
      int nx = n[0], ny = n[1];
      if(validCoordinate(nx, ny, seen.length, seen[0].length) && ! seen[nx][ny])
        pq.offer(new SimpleEntry<Integer, CoordinatePair>(r.nextInt(),
                  new CoordinatePair(n, parent)));

    }
  }

  /**
   * Check if a coordinate pair is within the bounds of the maze.
   */
  private boolean validCoordinate(int x, int y, int length, int width) {
    return x >= 0 && x < length && y >= 0 && y < width;
  }
}
