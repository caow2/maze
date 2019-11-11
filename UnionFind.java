import java.util.Arrays;
/*
 * Union Find with rank and path compression for efficient Kruskal's algorithm.
 */
public class UnionFind {
  private int[] parents, rank;
  protected int numComponents;

  public UnionFind(int size) {
    numComponents = size;
    parents = new int[size];
    rank = new int[size];
    Arrays.fill(rank, 1); // all components initially have rank of 1

    for(int i = 0; i < parents.length; i++) {
      parents[i] = i;
    }
  }

  public int find(int x) {
    if(parents[x] != x)
      parents[x] = find(parents[x]); // path compression
    return parents[x];
  }

  public void union(int x, int y) {
    int parentX = find(x), parentY = find(y);
    if(connected(x, y))
      return;

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
  }

  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }

  public int rank(int x) {
    return rank[x];
  }
}
