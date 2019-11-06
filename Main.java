import java.util.Arrays;
public class Main {
  public static void main(String[] args) {
    MazePanel[][] maze = new DFSMazeGenerator().generate(5,5);

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < maze.length; i++) {
      for(int j = 0; j < maze[0].length; j++) {
        sb.append(maze[i][j].toString());
        if(j < maze[0].length - 1)
          sb.append(",");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
