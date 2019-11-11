import java.util.Arrays;
/*
 * Union Find with rank and path compression for Kruskal's algorithm.
 */
public class UnionFind {
  private int[] parents, rank;
  protected int numComponents;

  public UnionFind(int size) {
    numComponents = size;
    parents = new int[size];
    rank = new int[size];

    for(int i = 0; i < parents.length; i++) {
      parents[i] = i;
      rank[i] = 1;
    }
  }

  /**
   * Find the parent of component x and compress the path.
   */
  public int find(int x) {
    if(parents[x] != x)
      parents[x] = find(parents[x]);
    return parents[x];
  }

  /**
   * Connect two components if they are not already connected.
   * Returns true if successful union, false otherwise
   */
  public boolean union(int x, int y) {
    int parentX = find(x), parentY = find(y);
    if(connected(x, y))
      return false;

    // merge y into x -> let parentX be larger ranked
    if(rank[parentX] < rank[parentY]) {
      int temp = parentX;
      parentX = parentY;
      parentY = temp;
    }
    else if(rank[parentX] == rank[parentY])
      rank[parentX]++;

    parents[parentY] = parentX;
    numComponents--;
    return true;
  }

  /**
   * Return whether 2 components x and y are connected.
   */
  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }

  public int rank(int x) {
    return rank[x];
  }

  public int numComponents() {
    return numComponents;
  }
}
