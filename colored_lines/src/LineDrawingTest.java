import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineDrawingTest {
    private final JFrame container = new JFrame();
    private final JPanel mainPanel = new JPanel();
    private final JPanel controlPanel = new JPanel();
    private final JButton controlBtn = new JButton("Draw");
    private LiningPanel panel;
    private Animator animator;
    private Thread thread;

    public LineDrawingTest() {
        this.panel = new LiningPanel(13);
        this.animator = new Animator(panel);
        this.thread = new Thread(animator);
    }

    public static void main(String[] args) {
        LineDrawingTest application = new LineDrawingTest();
        application.initUI(); // Set up the UI
    }

    private void initUI() {
        Color controlBackground = new Color(223, 230, 233); // Control panel background
        mainPanel.setBackground(controlBackground);
        controlPanel.setBackground(controlBackground);
        panel.setBackground(new Color(178, 190, 195)); // Animated section background
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        container.add(mainPanel);
        controlPanel.setMaximumSize(new Dimension(300, 30)); // Control section cannot exceed 300x30
        attachActions(); // Attach Controls
        controlPanel.add(controlBtn); // Add button to control panel
        mainPanel.add(controlPanel); // Add control panel to main
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Border graphicsBorder = BorderFactory.createLineBorder(Color.BLACK); // Border around drawing section
        panel.setBorder(graphicsBorder);
        mainPanel.add(panel); // Add the drawing section
        container.setSize(450, 400);
        container.setTitle("Lining Art - Dreger & James");
        container.setVisible(true);
    }

    private void attachActions() {
        controlBtn.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        if(thread.isAlive()) { // Check if thread is started
                            if(animator.isRunning()) { // Running State
                                System.out.println("Pausing..");
                                animator.doStop();
                                controlBtn.setText("Resume");
                            } else { // Stopped State
                                System.out.println("Resuming..");
                                animator.doStart();
                                controlBtn.setText("Stop");
                            }
                            System.out.println("Animator status: " + ((animator.isRunning()) ? "Running" : "Stopped"));
                        } else { // Start Thread
                            System.out.println("Starting Animator Thread..");
                            thread.start();
                            controlBtn.setText("Stop");
                        }

                    } // end method actionPerformed
                } // end anonymous inner class
        ); // end call to addActionListener
    }
}
