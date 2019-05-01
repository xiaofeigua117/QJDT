package ui.activity;//package com.example.administrator.demo;
//
//import android.support.annotation.UiThread;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.io.IOException;
//
//import android_serialport_api.SerialPort;
//
//public class SenseActivity extends AppCompatActivity {
//    private TextView co2;
//    private TextView pm;
//    private TextView ch2o;
//    private TextView tvoc;
//    private TextView wendu;
//    private TextView shidu;
//    private Button send;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sense);
//        co2 = (TextView) findViewById(R.id.co2);
//        pm = (TextView) findViewById(R.id.pm);
//        ch2o = (TextView) findViewById(R.id.ch2o);
//        tvoc = (TextView) findViewById(R.id.tvoc);
//        wendu = (TextView) findViewById(R.id.wendu);
//        send = (Button) findViewById(R.id.send);
//        shidu = (TextView) findViewById(R.id.shidu);
//        new MySenserThread().start();
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  SenseUtil.sendData(SenseActivity.this,1,"FE1700000001A006");
//            }
//        });
//    }
//        StringBuffer stringBuffer = new StringBuffer();
//        class MySenserThread extends Thread {
//
//                //字符数组、接收数据
//                byte[] buffer = new byte[1024];
//
//                @Override
//                public void run() {
//                    super.run();
//                    //死循环、保持接收状态
//                    Log.i("接收开始", "---------:");
//                    while (true) {
//                        try {
//
//                            int size = SerialPortUtil.InputStream_senser.read(buffer);
//                            if (size > 0) {
//
//
//                                StringBuffer buff=new StringBuffer(TransformUtils.bytesToHexString(buffer));
//
//
//                                //读到的数据建议设立缓存，不能保证一次完整发送一包，有时会2次发一个
//                                stringBuffer.append(buff);
//
//                                //读到的数据显示到页面
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        int index0= stringBuffer.indexOf("00000000");
//                                        Log.i("index",index0+"");
//                                        String s=stringBuffer.substring(0,index0);
//                                        co2.setText("串口1:" + s);
//                                        stringBuffer.delete(0, stringBuffer.length());
//                                    }
//                                });
//                            } else {
//                                Log.i("myerror", "接收异常咯:" + size);
//                            }
//                            Thread.sleep(400);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            }
//        }
