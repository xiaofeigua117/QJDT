package threads;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 10188 on 2018/2/28.
 * @author  blithe
 * @date   20180228
 */

public class SenserThread extends Thread {
    public Context context;
    private InputStream inputStream;
    public static  StringBuffer stringBuffer = new StringBuffer();
    //字符数组、接收数据
    public  SenserThread(){ }
    public  SenserThread( InputStream inputStream){

        this.inputStream=inputStream;
    }
    @Override
    public void run() {
        super.run();
        //死循环、保持接收状态
        Log.i("接收开始", "---------:");
      //  while(true){
            while(true) {
                Log.i("接收...", "---------:");
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (inputStream == null) return;
                    size = inputStream.read(buffer);
                    if (size > 0) {
                     //   onDataReceived(buffer, size);
                        String result = new String(buffer, 0, size).trim().toString();
                        //OrderHandleUtil.receiveOrder=new String(buffer, 0, size);
                        Log.i("接收size",size+"---"+result);
                    }
                    sleep(200);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            try {
//                int size = inputStream.read(buffer);
//                if (size > 0) {
//                    String result = TransformUtils.bytesToHexString(buffer);
//                    //清楚接受数组空数据
//                    int index0= result.indexOf("00000000");
//                     String s=result.substring(0,index0);
//                   // stringBuffer= new StringBuffer(TransformUtils.bytesToHexString(buffer));
//                    //Log.i("mytag", "接收到数据1长度:" + size);
//                   // /Log.i("mytag", "接收到数据1:" + result);
//                   // 读到的数据建议设立缓存，不能保证一次完整发送一包，有时会2次发一个
//                     stringBuffer.append(s);}
//                   //stringBuffer.append(result);}
//                   //Toast.makeText(context,"应按--"+stringBuffer.toString(),Toast.LENGTH_SHORT).show();
//                    Log.i("测试接收--","jieshou+"+stringBuffer.toString());
//
//                //返回值检测
//                if(stringBuffer.toString().startsWith("FF"))
//                {
//                    OrderHandleUtil.receiveOrder=stringBuffer.toString();
//                 }else{//返回值异常
//                }
//                   // stringBuffer.delete(0, stringBuffer.length());
//                    Thread.sleep(200);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

      //  }
    }
}
