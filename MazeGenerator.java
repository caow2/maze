/*
 * Generates a maze.
 */
public abstract class MazeGenerator {

  public abstract MazePanel[][] generate(int length, int width);

  private static boolean validSize(int length, int width) {
    return length > 0 && length <= Integer.MAX_VALUE && width > 0 && width < Integer.MAX_VALUE;
  }
}
