//package threads;
//
//import android.content.Context;
//import android.util.Log;
//
//import serialport_utils.OrderUtil;
//import serialport_utils.OrderUtil01;
//import serialport_utils.OrderUtil02;
//import serialport_utils.PLCSerialPortUtil;
//import serialport_utils.Send_Controller;
//
///**
// * Created by 10188 on 2019/1/7.
// */
//
//public class ReadbroadThread extends Thread {
//    Context context;
//    int i=5 ;
//        public ReadbroadThread(Context context){
//            this.context=context;
//        }
//    @Override
//    public void run() {
//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        while (true){
//
//            if(Send_Controller.isClicked==1){
//                OrderUtil.senOrder2( Send_Controller.send_type);
//                Log.e("点击了",""+ Send_Controller.send_type);
//                Send_Controller.isClicked=0;
//                Send_Controller.send_type=0;
//
//
//            }else if(Send_Controller.isClicked==2){
//                OrderUtil02.senOrder2( Send_Controller.send_type);
//                Send_Controller.isClicked=0;
//                Send_Controller.send_type=0;
//
//
//            }
//            else if(Send_Controller.isClicked==3){
//                OrderUtil01.senOrder2(context, Send_Controller.send_type);
//                Send_Controller.isClicked=0;
//                Send_Controller.send_type=0;
//
//
//            }else{
//
//                //PLCSerialPortUtil.sendData(1,"030407E20001020000DE8D");
//
//                PLCSerialPortUtil.sendData(1,"020407E20001020000D31D");
//
//
//            }
//            try {
//                sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            super.run();
//        }
//    }
//}
