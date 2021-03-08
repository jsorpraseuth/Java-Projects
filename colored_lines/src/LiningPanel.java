import java.awt.*;
import java.awt.Color;
import java.util.Random;

public class LiningPanel extends javax.swing.JPanel {
    private Random random = new Random();
    private double lines = 1.0;
    private double totalLines;
    public LiningPanel(double totalLines) {
        this.totalLines = totalLines;
    }

    // Responsible for drawing the lines on the GUI
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (int i = 0; i < lines; i++) {
            int w = getWidth();
            int h = getHeight();

            int w2 = (int) ((i / lines) * w);
            int h2 = (int) ((i / lines) * h);

            // Generate a random color for the lines in this loop
            Color randomColor = getRandomColor();

            // Bottom Left
            g.setColor(randomColor);
            g.drawLine(0, h2, w2, h);

            // Bottom Right
            g.setColor(randomColor);
            g.drawLine(w, h2, w - w2, h);

            // Top Right
            g.setColor(randomColor);
            g.drawLine(w, h - h2, w - w2, 0);

            // Top Left
            g.setColor(randomColor);
            g.drawLine(0, h - h2, w2, 0);
        }
    }


    /*
     * Generate a random color
     */
    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    /*
     * Increase Line Count so that we can manipulate how many lines are drawn
     * Will reset Line Count back to 1
     *
     */
    public void increaseCounter() {
        if (this.lines < this.totalLines) {
            this.lines++;
        } else {
            this.lines = 1;
        }
    }

    
}
