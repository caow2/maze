/*
 * An individual component of a maze.
 * A MazePanel initially starts with closed 'walls' 4 in the cardinal directions.
 * MazePanels can connect to other MazePanels if they are adjacent and walls are open.
 */
public class MazePanel {
  protected static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; // wall numbers
  protected boolean upOpen, downOpen, leftOpen, rightOpen; // initially all false

  public MazePanel(){
  }

  /* Breaks down a wall in the mazePanel.
   * @param wallNum refers to the wall that we want to break down.
   */
  public void breakDownWall(int wallNum) {
    switch(wallNum) {
      case(UP):
        upOpen = true;
        break;
      case(DOWN):
        downOpen = true;
        break;
      case(LEFT):
        leftOpen = true;
        break;
      case(RIGHT):
        rightOpen = true;
        break;
      default:
        System.err.println("Invalid wall number : " + wallNum);
        break;
    }
  }
}
