import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * Draw the given maze.
 */
public class MazeFrame extends JFrame {
  private int cellSize, padding = 20; // cell size is square with length c
  private MazeCanvas canvas;
  public static final int CANVAS_WIDTH = 500, CANVAS_HEIGHT = 500;

  public MazeFrame(MazePanel[][] maze) {
    // calculate cell size with respect to canvas size
    cellSize = Math.min(CANVAS_WIDTH / maze[0].length, CANVAS_HEIGHT / maze.length);
    canvas = new MazeCanvas(maze);
    canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

    JPanel centerPanel = new JPanel(new GridBagLayout());
    centerPanel.setBackground(Color.WHITE);
    centerPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
    this.setContentPane(centerPanel);
    this.add(canvas);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setTitle("Maze");
    setVisible(true);
  }

  private class MazeCanvas extends JPanel {
    MazePanel[][] maze;

    public MazeCanvas(MazePanel[][] maze) {
      this.maze = maze;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      g.setColor(Color.BLACK); // draw lines in Black

      for(int i = 0; i < maze.length; i++) {
        for(int j = 0; j < maze[0].length; j++) {
          MazePanel m = maze[i][j];
          int x = j * cellSize, y = i * cellSize; // top left point of the cell

          // draw border walls if they are closed
          if(! m.upOpen)
            g.drawLine(x, y, x + cellSize, y);
          if(! m.downOpen)
            g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
          if(! m.leftOpen)
            g.drawLine(x, y, x, y + cellSize);
          if(! m.rightOpen)
            g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
        }
      }
    }
  }
}
