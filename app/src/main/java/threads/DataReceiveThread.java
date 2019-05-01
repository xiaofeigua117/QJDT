package threads;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import serialport_utils.DataBack;

/**
 * Created by 10188 on 2019/3/21.
 */

public class DataReceiveThread extends Thread {
    private InputStream inputStream;
    DataBack dataBack;
    int i=0;

    public DataReceiveThread(DataBack dataBack, InputStream inputStream){
        this.inputStream=inputStream;
        this.dataBack=dataBack;
    }
    @Override
    public void run() {
        super.run();
        while(!isInterrupted()) {
            int size;
            try {
                Log.i("接收正常","-------");
                byte[] buffer = new byte[64];
                if (inputStream == null) return;
                size = inputStream.read(buffer);
                if (size > 0) {
                    Log.i("数据接收","size="+size);
                    dataBack.OnDataReceived(buffer, size);
                }
            } catch (IOException e) {
                Log.i("数据","异常");
                e.printStackTrace();
                return;
            }
        }
    }

}
