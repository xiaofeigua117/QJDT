package ui.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.demo.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import serialport_utils.CRC16M;
import serialport_utils.DataBack;
import serialport_utils.InputStatus;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;
import serialport_utils.SerilportManager;
import serialport_utils.TransformUtils;
import threads.DataReceiveThread;
import threads.FandelayThread;
import ui.adapter.MyFragmentPagerAdapter;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener, DataBack {

    //几个代表页面的常量
    private static int SENSER_TYPE = 12;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    @BindView(R.id.rb_state)
    RadioButton rbState;
    @BindView(R.id.rb_control)
    RadioButton rbControl;
    @BindView(R.id.rb_center)
    RadioButton rbCenter;
    @BindView(R.id.rb_service)
    RadioButton rbService;
    @BindView(R.id.rg_tab_bar)
    RadioGroup rgTabBar;
    @BindView(R.id.vpager)
    ViewPager vpager;
    private MyFragmentPagerAdapter mAdapter;
    InputStatus inputStatus_canting;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    if (msg.obj != null) {

                        //判断打开窗户
                        if (InputStatus.Is_openwin == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 19;
                            InputStatus.Is_openwin = 0;
                        }
                        if (InputStatus.Is_closeWin == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 20;
                            InputStatus.Is_closeWin = 0;
                        }
                        //判断厕所开关状态
                        if (InputStatus.key_restroom == 1) {
                            Log.i("检测到厕所", "做控制输出");
                            new FandelayThread().start();
                            InputStatus.key_restroom = 0;
                        }
                        //厕所灯开
                        if (InputStatus.key_lighton_restroom == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 13;
                            InputStatus.key_lighton_restroom = 0;
                        }
                        //厕所灯关
                        if (InputStatus.key_lightoff_restroom == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 14;
                            InputStatus.key_lightoff_restroom = 0;
                        }
                        //厨房灯开
                        if (InputStatus.chufangkai == 1) {

                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 11;
                            InputStatus.chufangkai = 0;

                        }
                        //厨房灯关
                        if (InputStatus.chufangguan == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 12;
                            InputStatus.chufangguan = 0;

                        }
                        //厕所人感  检测
                        if (InputStatus.rengan_bathroom == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 13;
                            InputStatus.rengan_bathroom = 0;

                        }


                        if (InputStatus.KeyLight == true) {
                            if (InputStatus.KeyLight != InputStatus.KeyLight_pre) {
                                Send_Controller.isClicked = 1;
                                Send_Controller.send_type = 1;
                                InputStatus.KeyLight_pre = InputStatus.KeyLight;
                            }

                        } else if (InputStatus.KeyLight == false) {
                            if (InputStatus.KeyLight != InputStatus.KeyLight_pre) {
                                Send_Controller.isClicked = 1;
                                Send_Controller.send_type = 0;
                                InputStatus.classLight = 1;
                                InputStatus.KeyLight_pre = InputStatus.KeyLight;
                            }
                        }
                        if (InputStatus.KeyLight == true) {
                            switch (InputStatus.classLight) {

                                case 1:

                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 1;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("一级灯", "....");
                                    }
                                    break;
                                case 2:
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 2;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("二级灯", "....");
                                    }
                                    break;
                                case 3:
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 3;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("三级灯", "....");
                                    }
                                    break;
                                case 4:
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 8;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("筒灯", "....");
                                    }
                                    break;
                                case 5:
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 9;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("筒灯2门厅", "....");
                                    }
                                    break;

                            }
                        } else if (InputStatus.KeyLight == false) {
                            //  Send_Controller.isClicked=1;
                            //  Send_Controller.send_type=1;
                        }
                    }

                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //设置横向布局
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //1，添加容器
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        //2，加载
        bindViews();


//初始化串口
        try {
            //初始化串口
            SerilportManager.initPort(1);
            DataReceiveThread dataReceiveThread=new DataReceiveThread(this,SerilportManager.inputStream);
            dataReceiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    SerilportManager.sendData("020407E20001020000D31D");
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private void bindViews() {
        rgTabBar.setOnCheckedChangeListener(this);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_state:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_control:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_center:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_service:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rbState.setChecked(true);
                    break;
                case PAGE_TWO:
                    rbControl.setChecked(true);
                    break;
                case PAGE_THREE:
                    rbCenter.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rbService.setChecked(true);
                    break;
            }
        }
    }


    @Override
    public void OnDataReceived(byte[] buffer, int size) throws UnsupportedEncodingException {
        String se= TransformUtils.encode(new String(buffer, 0, size,"ISO-8859-1"));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Log.i("返回值",""+se);
//        if(se.length()>=24){
//            String s=  se.substring(6,24);
//            InputStatus.getInput12(s.substring(14,s.length()));
//            Log.i("状态",""+ InputStatus.input12);
//        }

//        Message message=new Message();
//        message.what=1;
//        mHandler.sendMessage(message);
    }
}