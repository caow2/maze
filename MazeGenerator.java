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

  /**
   * Generate an initial maze with specified length and width.
   * All walls are initially closed.
   */
  protected MazePanel[][] init(int length, int width) {
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
   * Breaks down the walls between two adjacent MazePanels of coordinates {x,y}
   * and {parentX, parentY}
   */
  protected void breakDownWalls(MazePanel[][] maze, int x, int y, int parentX, int parentY) {
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
}
