
public class Driver {
  public static void main(String[] args) {
    //testMazeKruskals();
    //testMazeDFS();
    //testUF();
    testMazePrims();
  }

  public static void testMazeDFS() {
    int len = 20, width = 20; //maze dimensions
    MazePanel[][] maze = new DFSMazeGenerator().generate(len, width);
    new MazeFrame(maze);
  }

  public static void testMazeKruskals() {
    int len = 20, width = 20;
    MazePanel[][] maze = new KruskalMazeGenerator().generate(len, width);
    new MazeFrame(maze);
  }

  public static void testMazePrims() {
    int len = 20, width = 20;
    MazePanel[][] maze = new PrimMazeGenerator().generate(len, width);
    new MazeFrame(maze);
  }

  public static void testUF() {
    UnionFind uf = new UnionFind(10);
    uf.union(1,3);
    System.out.println(uf.find(3) == 1);
    System.out.println(uf.find(1) == 1);

    uf.union(5,3); // parent of 3 is rank 2, while 5 is rank == 1 1
    System.out.println(uf.find(5) == 1);
    System.out.println(uf.find(3) == 1);

    System.out.println(uf.connected(5, 1));
    System.out.println(! uf.connected(3, 4));
  }
}
