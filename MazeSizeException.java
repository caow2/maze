
public class MazeSizeException extends Exception {
  protected static String formatString = "%1$s x %2$s maze cannot be created.";
  private String defaultMessage;

  public MazeSizeException(int length, int width) {
    super(String.format(formatString, length, width));
  }

  public String toString() {
    return super.toString();
  }
}
