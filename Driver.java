public class Driver {
  public static void main(String[] args) {
    int len = 20, width = 20; // maze dimensions
    MazePanel[][] maze = new DFSMazeGenerator().generate(len, width);
    new MazeFrame(maze);
  }
}
