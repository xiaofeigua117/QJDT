package threads;


import serialport_utils.Send_Controller;

/**
 * Created by 10188 on 2019/1/24.
 */

public class FandelayThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            Send_Controller.isClicked=1;
            Send_Controller.send_type=16;
            sleep(500);
            Send_Controller.isClicked=1;
            Send_Controller.send_type=17;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
