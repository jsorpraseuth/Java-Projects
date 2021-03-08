

public class Animator implements Runnable{
    private boolean doStop = false;
    private LiningPanel panel;

    public Animator(LiningPanel panel) {
        this.panel = panel;
    }

    public void doStop() {
        this.doStop = true;
    }

    public void doStart() {
        this.doStop = false;
    }

    public boolean isRunning() {
        return this.doStop == false;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    private synchronized void printLines() {
        while(keepRunning()) {
            panel.repaint();
            panel.increaseCounter();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        while(true) {
            try {
                printLines();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
