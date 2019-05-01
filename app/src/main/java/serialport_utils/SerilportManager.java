package serialport_utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

/**
 * 串口操作类
 * @data 2019_3_18
 * @author blithe
 *
 */

public class SerilportManager {
    private  int PortNum;
    private  int baterate;
   public static SerialPort serialPort;
    static int serialPort_num;
    public static InputStream inputStream;
    public static OutputStream outputStream;

    public static void initPort(int num) throws IOException {

        //默认初始化115200
        serialPort = new SerialPort(new File("/dev/ttyS7"), 115200, 0);
        serialPort_num=num;

        inputStream=serialPort.getInputStream();
        outputStream=serialPort.getOutputStream();
        outputStream.write(TransformUtils.hexStringToBytes(TransformUtils.encode("S1:9600")));
    }
    //
    public static void sendData(String data){
        //初始化字节数组

        //目前串口需要分发，通用串口直接发送 T1 代表从串口1发送

        String o16= TransformUtils.encode("T"+serialPort_num+":");

        byte[] outs= TransformUtils.hexStringToBytes(o16+data);
        try {
            outputStream.write(outs);
            //换行发送
//            outputStream.write('\n');
              outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void closeSerialPort() {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

}
