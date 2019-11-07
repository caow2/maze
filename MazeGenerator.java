/*
 * Generates a maze.
 */
public abstract class MazeGenerator {

  public abstract MazePanel[][] generate(int length, int width);

  protected static boolean validSize(int length, int width) {
    return length > 0 && length <= Integer.MAX_VALUE && width > 0 && width < Integer.MAX_VALUE;
  }

  /**
   * Open the maze by setting the start on the top left and end on the bottom right.
   */
  protected static void openMaze(MazePanel[][] maze) {
    maze[0][0].breakDownWall(MazePanel.LEFT);
    maze[maze.length - 1][maze[0].length - 1].breakDownWall(MazePanel.RIGHT);
  }
}
