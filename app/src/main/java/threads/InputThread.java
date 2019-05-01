package threads;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import serialport_utils.InputStatus;
import serialport_utils.TransformUtils;

/**
 * Created by 10188 on 2018/2/28.
 * @author  blithe
 * @date   20180228
 */

public class InputThread extends Thread {
    public Context context;
    private InputStream inputStream;
    public static  StringBuffer stringBuffer = new StringBuffer();
    InputStatus inputStatus_woshi=new InputStatus();
    //字符数组、接收数据
    public InputThread(){ }
    public InputThread(InputStream inputStream){

        this.inputStream=inputStream;
    }
    @Override
    public void run() {
        super.run();
        //死循环、保持接收状态
            while(true) {
                Log.i("接收...", "---------:");
                int size;
                try {
                    byte[] buffer = new byte[128];
                    String ss="";
                  //  if (inputStream == null) return;
                    size = inputStream.read(buffer);
                    if (size > 0) {
                     //   onDataReceived(buffer, size);
                        String result = new String(buffer, 0, size).trim().toString();
                        //截取回来的字符串 result
                        String se= TransformUtils.encode(result);
                        Log.i("传感大于15",""+se);
                        //传感器数据大于34个，基本判断
                        if(se.length()>=34){
                            String s="";
                            se=se.substring(6,se.length());
                            Log.e("zz",se);
                        }
                        if(se.length()>24)

                        {ss=  TransformUtils.encode(result).substring(6,24);}
                        if(ss.startsWith("0204")){
                            /****
                             * 这里判断板子状态，包括烛灯开关，门传感器，窗户传感器。。。
                             */
                            inputStatus_woshi.getInput12(ss.substring(14,ss.length()));
                            Log.i("12路状态值------》","["+inputStatus_woshi.input12+"]");

                            }
                    }
                    sleep(150);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
