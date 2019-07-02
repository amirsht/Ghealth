package TaskManagment;

import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

    private Timer timer = new Timer();

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.startTask();
    }

    public void startTask() {
        timer.schedule(new PeriodicTask(), 0);
    }

    private class PeriodicTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " Running");
            
            /**
             * two available options for us
             */

            /* replace with the actual task */
            try {
                Thread.sleep(15 * 1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            /* end task processing */

            System.out.println(System.currentTimeMillis() + "(UTC):" + " Scheduling 24 hours from now");
            timer.schedule(new PeriodicTask(), 86400 * 1000);
        }
    }
}
