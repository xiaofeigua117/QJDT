package ui.fargment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;
import ui.activity.TimingActivity;
import utils.Constants;
import utils.Logger;

import utils.PreferenceUtils;
import utils.ToastUtils;

/**
 * Created by Lenovo on 2018/1/4.
 * 控制中心风机窗户
 */

public class ControlFargment extends BaseFragment implements View.OnClickListener {

    private String TAG = "ControlFargment";

    @BindView(R.id.fan_open)
    Button fanOpen;
    @BindView(R.id.fan_time)
    Button fanTime;
    @BindView(R.id.fan_open2)
    Button fanOpen2;
    @BindView(R.id.fan_time2)
    Button fanTime2;
    @BindView(R.id.light1)
    Button light1;
    @BindView(R.id.light2)
    Button light2;
    @BindView(R.id.light3)
    Button light3;
    @BindView(R.id.light4)
    Button light4;
    @BindView(R.id.window_open)
    Button windowOpen;
    @BindView(R.id.window_close)
    Button windowClose;
    @BindView(R.id.curtain_open)
    Button curtainOpen;
    @BindView(R.id.curtain_close)
    Button curtainClose;
    @BindView(R.id.model)
    ImageButton model;
    @BindView(R.id.prevent)
    ImageButton prevent;
    @BindView(R.id.temperature)
    ImageButton temperature;
    @BindView(R.id.control)
    ImageButton control;
    Unbinder unbinder;
    @BindView(R.id.fan1)
    ImageView fan1;
    @BindView(R.id.fan2)
    ImageView fan2;
    @BindView(R.id.curtain)
    ImageView curtain;
    @BindView(R.id.window)
    ImageView window;
    private AlertDialog saloonDialaog;

    private final static String[] LIGHT = new String[]{"1级", "2级",
            "3级", "4级", "5级", "6级", "7级"};
    private static int ZLIGHT;
    private static int CLIGHT;
    private Button mBtnZopen;
    private Button mBbtnZclose;
    private Button mBtnZlightj;
    private Button mBtnZlightjj;
    private Button mBtnZyopen;
    private Button mBtnZyclose;
    private Button mBtnZyes;
    private Button mBtnZtOpen;
    private Button mBtnZtClose;
    private Button mBtnZtNo;
    private boolean isZLight;
    private TextView mTvRank;
    private boolean isZTLight;
    private boolean isZYLight;
    private AlertDialog cookDialaog;
    private TextView mTvCrank;
    private Button mBtnCzopen;
    private Button mBtnCZClose;
    private Button mBtnClightj;
    private Button mBtnClightjj;
    private Button mBtnCyopen;
    private Button mBtnCyclose;
    private Button mBtnCyes;
    private Button mBtnCtOpen;
    private Button mBtnCtClose;
    private Button mBtnCtNo;
    private boolean isCLight;
    private boolean isCTLight;
    private boolean isCYLight;
    private Button mBtnWyopen;
    private Button mBtnWyclose;

    private Button mBtnWtOpen;
    private Button mBtnWtClose;

    private AlertDialog wcDialaog;
    private boolean isWTLight;
    private boolean isWYLight;
    private boolean fanState=true;
    private boolean fanState1=true;

    public ControlFargment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_control, null);
        unbinder = ButterKnife.bind(this, view);

        return view;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fan_open, R.id.fan_time, R.id.fan_open2, R.id.fan_time2, R.id.light1, R.id.light2, R.id.light3, R.id.light4, R.id.window_open, R.id.window_close, R.id.curtain_open, R.id.curtain_close, R.id.model, R.id.prevent, R.id.temperature, R.id.control})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fan_open:
                // 中央风机打开

                if (fanState) {
                    //打开
                    fanState = false;

                    fanOpen.setText("关闭");
                    PLCSerialPortUtil.sendData(1,"010607E100010200030606");
                    //Send_Controller.isClicked=3;
                    //Send_Controller.send_type=3;
                    Animation circle_anim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_round_rotate);
                    LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                    circle_anim.setInterpolator(interpolator);
                    fan1.startAnimation(circle_anim);  //开始动画
                } else {
                    fanState = true;
                   // Send_Controller.isClicked=3;
                   // Send_Controller.send_type=2;
                    fanOpen.setText("开启");
                    PLCSerialPortUtil.sendData(1,"010607E100010200004607");
                    fan1.clearAnimation(); //关闭动画
                }


                break;
            case R.id.fan_time:
                //开启定时
                Intent timing = new Intent(getActivity(), TimingActivity.class);
                startActivity(timing);

//                fan1.clearAnimation();
                break;
            case R.id.fan_open2:
                //厕所风机

                if (fanState1) {
                    //打开
                    fanState1 = false;
                    fanOpen2.setText("关闭");
                    Animation circle_anim1 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_round_rotate);
                    LinearInterpolator interpolator2 = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                    circle_anim1.setInterpolator(interpolator2);
                    fan2.startAnimation(circle_anim1);  //开始动画
                } else {
                    fanState1 = true;
                    fanOpen2.setText("开启");
                    fan2.clearAnimation();
                }





                break;
            case R.id.fan_time2:

                Toast.makeText(getActivity(), "开启小风机定时", Toast.LENGTH_LONG).show();

                // 点击定时设置。 弹出2个对话

                break;
            case R.id.light1:
                //客厅灯

                showSaloonDialog();


                break;
            case R.id.light2:
                //餐厅灯

                showCookDialog();
                break;
            case R.id.light3:
                //打开筒灯3
                ToastUtils.show(getActivity(), "公卫");

                showWcDialog();
                break;
            case R.id.light4:
                //打开筒灯4
                ToastUtils.show(getActivity(), "设置");
                break;
            case R.id.window_open:
                //窗户开启
                ToastUtils.show(getActivity(), "窗户开启");
                Send_Controller.isClicked=2;
                Send_Controller.send_type=11;
                window.setBackgroundResource(R.drawable.window1_anim);
                AnimationDrawable windowBackGround = (AnimationDrawable) window.getBackground();
                windowBackGround.start();
                break;
            case R.id.window_close:
                //窗户关闭
                ToastUtils.show(getActivity(), "窗户关闭");
                Send_Controller.isClicked=2;
                Send_Controller.send_type=12;
                window.setBackgroundResource(R.drawable.window_anim);
                AnimationDrawable windowBackGround1 = (AnimationDrawable) window.getBackground();
                windowBackGround1.start();
                break;
            case R.id.curtain_open:
                //窗帘开启
                ToastUtils.show(getActivity(), "窗帘开启");
                Send_Controller.isClicked=1;
                Send_Controller.send_type=19;
                curtain.setBackgroundResource(R.drawable.curtain1_anim);
                AnimationDrawable background1 = (AnimationDrawable) curtain.getBackground();
                background1.start();
                break;
            case R.id.curtain_close:
                Send_Controller.isClicked=1;
                Send_Controller.send_type=20;
                ToastUtils.show(getActivity(), "窗帘关闭");
                curtain.setBackgroundResource(R.drawable.curtain_anim);
                AnimationDrawable background = (AnimationDrawable) curtain.getBackground();
                background.start();


                break;
            case R.id.model:
                ToastUtils.show(getActivity(), "模式");
                break;
            case R.id.prevent:
                ToastUtils.show(getActivity(), "防打扰");
                break;
            case R.id.temperature:
                ToastUtils.show(getActivity(), "温度");
                break;
            case R.id.control:
                ToastUtils.show(getActivity(), "电器控制");
                break;
        }
    }

    private void showSaloonDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 生成dialog的view 注意布局的上下文传this
        View view = View.inflate(getContext(), R.layout.dialog_saloon_light, null);
        // 设置dialog的布局view
        builder.setView(view);
        saloonDialaog = builder.create();
        mTvRank = (TextView) view.findViewById(R.id.sl_tv_rank);
        mBtnZopen = (Button) view.findViewById(R.id.sl_btn_zuopen);
        mBbtnZclose = (Button) view.findViewById(R.id.sl_btn_zuclose);
        mBtnZlightj = (Button) view.findViewById(R.id.sl_btn_lightj);
        mBtnZlightjj = (Button) view.findViewById(R.id.sl_btn_lightjj);
        mBtnZyopen = (Button) view.findViewById(R.id.sl_btn_zyopen);
        mBtnZyclose = (Button) view.findViewById(R.id.sl_btn_zyclose);
        mBtnZtOpen = (Button) view.findViewById(R.id.sl_btn_ztopen);
        mBtnZtClose = (Button) view.findViewById(R.id.sl_btn_ztclose);

        mBtnZtClose.setOnClickListener(this);
        mBtnZtOpen.setOnClickListener(this);

        mBtnZyclose.setOnClickListener(this);
        mBtnZyopen.setOnClickListener(this);
        mBtnZlightjj.setOnClickListener(this);
        mBtnZlightj.setOnClickListener(this);
        mBbtnZclose.setOnClickListener(this);
        mBtnZopen.setOnClickListener(this);

        //主灯光
        isZLight = PreferenceUtils.getBoolean(getContext(), Constants.SALLOON_MAIN_LIGHT, false);
        isZTLight = PreferenceUtils.getBoolean(getContext(), Constants.SALLOON_TD_LIGHT, false);
        isZYLight = PreferenceUtils.getBoolean(getContext(), Constants.SALLOON_YT_LIGHT, false);
        ZLIGHT = PreferenceUtils.getInt(getActivity(), Constants.SALLOON_MAIN_LIGHTRANK, 0);
        mTvRank.setText(LIGHT[ZLIGHT]);
        //阳台的数据回显
        if (isZYLight) {
            mBtnZyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnZyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnZyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnZyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }
        //筒灯数据回显
        if (isZTLight) {
            mBtnZtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnZtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnZtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnZtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }

        //主灯回显数据
        if (isZLight) {
            mBtnZopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBbtnZclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnZopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBbtnZclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }


        // 显示dialog

        saloonDialaog.show();


    }

    private void showCookDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 生成dialog的view 注意布局的上下文传this
        View view = View.inflate(getContext(), R.layout.dialog_cook_light, null);
        // 设置dialog的布局view
        builder.setView(view);
        cookDialaog = builder.create();

        mTvCrank = (TextView) view.findViewById(R.id.cl_tv_crank);
        mBtnCzopen = (Button) view.findViewById(R.id.cl_btn_czopen);
        mBtnCZClose = (Button) view.findViewById(R.id.cl_btn_czclose);
        mBtnClightj = (Button) view.findViewById(R.id.cl_btn_clightj);
        mBtnClightjj = (Button) view.findViewById(R.id.cl_btn_clightjj);
        mBtnCyopen = (Button) view.findViewById(R.id.cl_btn_cyopen);
        mBtnCyclose = (Button) view.findViewById(R.id.cl_btn_cyclose);

        mBtnCtOpen = (Button) view.findViewById(R.id.cl_btn_ctopen);
        mBtnCtClose = (Button) view.findViewById(R.id.cl_btn_ctclose);


        mBtnCzopen.setOnClickListener(this);
        mBtnCZClose.setOnClickListener(this);
        mBtnClightj.setOnClickListener(this);
        mBtnClightjj.setOnClickListener(this);
        mBtnCyopen.setOnClickListener(this);
        mBtnCyclose.setOnClickListener(this);

        mBtnCtOpen.setOnClickListener(this);
        mBtnCtClose.setOnClickListener(this);


        //主灯光
        isCLight = PreferenceUtils.getBoolean(getContext(), Constants.COOK_MAIN_LIGHT, false);

        isCTLight = PreferenceUtils.getBoolean(getContext(), Constants.COOK_TD_LIGHT, false);
        isCYLight = PreferenceUtils.getBoolean(getContext(), Constants.COOK_YT_LIGHT, false);
        CLIGHT = PreferenceUtils.getInt(getActivity(), Constants.COOK_MAIN_LIGHTRANK, 0);
        mTvCrank.setText(LIGHT[CLIGHT]);

        //阳台的数据回显

        if (isCYLight) {
            mBtnCyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnCyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnCyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnCyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }
        //筒灯数据回显
        if (isCTLight) {
            mBtnCtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnCtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnCtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnCtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }

        //主灯回显数据
        if (isCLight) {
            mBtnCzopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnCZClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnCzopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnCZClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }


        // 显示dialog
        cookDialaog.show();
    }

    private void showWcDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 生成dialog的view 注意布局的上下文传this
        View view = View.inflate(getContext(), R.layout.dialog_wc_light, null);
        // 设置dialog的布局view
        builder.setView(view);
        wcDialaog = builder.create();

        mBtnWyopen = (Button) view.findViewById(R.id.wl_btn_yopen);
        mBtnWyclose = (Button) view.findViewById(R.id.wl_btn_yclose);

        mBtnWtOpen = (Button) view.findViewById(R.id.wl_btn_topen);
        mBtnWtClose = (Button) view.findViewById(R.id.wl_btn_tclose);


        mBtnWyopen.setOnClickListener(this);
        mBtnWyclose.setOnClickListener(this);
        mBtnWtOpen.setOnClickListener(this);
        mBtnWtClose.setOnClickListener(this);


        isWTLight = PreferenceUtils.getBoolean(getContext(), Constants.WC_TD_LIGHT, false);
        isWYLight = PreferenceUtils.getBoolean(getContext(), Constants.WC_YT_LIGHT, false);

        if (isWYLight) {
            mBtnWyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnWyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnWyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnWyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }
        //筒灯数据回显
        if (isWTLight) {
            mBtnWtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
            mBtnWtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
        } else {
            mBtnWtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
            mBtnWtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
        }

        // 显示dialog
        wcDialaog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sl_btn_zuopen:
                Logger.i(TAG, "主灯开");
                isZLight = true;
                Send_Controller.isClicked=2;
                Send_Controller.send_type=ZLIGHT;
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_MAIN_LIGHT, true);
                mBtnZopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBbtnZclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);


                break;
            case R.id.sl_btn_zuclose:
                isZLight = false;
                Logger.i(TAG, "主灯关");
                Send_Controller.isClicked=2;
                Send_Controller.send_type=0;
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_MAIN_LIGHT, false);
                mBtnZopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBbtnZclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);

                break;
            case R.id.sl_btn_lightj:

                ZLIGHT++;
                if (ZLIGHT == 7) {
                    ZLIGHT = 0;
                }
                Logger.i(TAG, "ZLIGHT" + ZLIGHT);

                mTvRank.setText(LIGHT[ZLIGHT]);

                if (isZLight) {
                    //灯光开启调节亮度
                    switch (ZLIGHT) {
                        case 0:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=2;
                            break;
                        case 1:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=3;
                            break;
                        case 2:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=1;
                            break;
                        case 3:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=4;
                            break;
                        case 4:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=5;
                            break;
                        case 5:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=6;
                            break;
                        case 6:
                            Logger.i(TAG, "主灯" + ZLIGHT);
                            Send_Controller.isClicked=2;
                            Send_Controller.send_type=7;
                            break;

                    }
                }
                PreferenceUtils.putInt(getContext(), Constants.SALLOON_MAIN_LIGHTRANK, ZLIGHT);
                break;
            case R.id.sl_btn_lightjj:

                if (ZLIGHT == 0) {
                    ZLIGHT = 7;
                }
                ZLIGHT--;

                Logger.i(TAG, "ZLIGHT" + ZLIGHT);
                mTvRank.setText(LIGHT[ZLIGHT]);

                if (isZLight) {
                    //灯光开启调节亮度
                    if (isZLight) {
                        //灯光开启调节亮度
                        switch (ZLIGHT) {
                            case 0:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=2;
                                break;
                            case 1:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=3;
                                break;
                            case 2:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=1;
                                break;
                            case 3:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=4;
                                break;
                            case 4:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=5;
                                break;
                            case 5:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=6;
                                break;
                            case 6:
                                Logger.i(TAG, "主灯" + ZLIGHT);
                                Send_Controller.isClicked=2;
                                Send_Controller.send_type=7;
                                break;

                        }
                    }
                }
                PreferenceUtils.putInt(getContext(), Constants.SALLOON_MAIN_LIGHTRANK, ZLIGHT);
                break;

            case R.id.sl_btn_ztopen:
                Logger.i(TAG, "筒灯开");
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_TD_LIGHT, true);
                mBtnZtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnZtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                //实现筒灯开
                Send_Controller.isClicked=2;
                Send_Controller.send_type=8;

                break;

            case R.id.sl_btn_ztclose:
                Logger.i(TAG, "筒灯关");
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_TD_LIGHT, false);
                mBtnZtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnZtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                Send_Controller.isClicked=2;
                Send_Controller.send_type=0;
                //实现筒灯关

                break;

            case R.id.sl_btn_zyopen:
                Logger.i(TAG, "阳台灯开");
                Send_Controller.isClicked=2;
                Send_Controller.send_type=9;
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_YT_LIGHT, true);
                mBtnZyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnZyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                break;

            case R.id.sl_btn_zyclose:
                Logger.i(TAG, "阳台灯关");
                Send_Controller.isClicked=2;
                Send_Controller.send_type=10;
                PreferenceUtils.putBoolean(getContext(), Constants.SALLOON_YT_LIGHT, false);
                mBtnZyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnZyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);

                break;


//餐厅灯光控制
            case R.id.cl_btn_czopen:
                Logger.i(TAG, "餐厅主灯开");
                Send_Controller.isClicked=1;
                Send_Controller.send_type=1;
                isCLight = true;
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_MAIN_LIGHT, true);
                mBtnCzopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnCZClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);


                break;
            case R.id.cl_btn_czclose:
                Send_Controller.isClicked=1;
                Send_Controller.send_type=0;
                isCLight = false;
                Logger.i(TAG, "餐厅主灯关+");
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_MAIN_LIGHT, false);
                mBtnCzopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnCZClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);

                break;
            case R.id.cl_btn_clightj:

                CLIGHT++;
                if (CLIGHT == 7) {
                    CLIGHT = 0;
                }
                Logger.i(TAG, "CLIGHT" + CLIGHT);
                mTvCrank.setText(LIGHT[CLIGHT]);

                if (isCLight) {
                    //灯光开启调节亮度
                    switch (CLIGHT) {
                        case 0:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=1;
                            break;
                        case 1:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=2;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            break;
                        case 2:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=3;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);

                            break;
                        case 3:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=4;
                            break;
                        case 4:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=5;
                            break;
                        case 5:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=6;
                            break;
                        case 6:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=7;
                            break;

                    }

                }
                PreferenceUtils.putInt(getContext(), Constants.COOK_MAIN_LIGHTRANK, CLIGHT);
                break;
            case R.id.cl_btn_clightjj:

                if (CLIGHT == 0) {
                    CLIGHT = 7;
                }
                CLIGHT--;
                Logger.i(TAG, "CLIGHT" + CLIGHT);
                PreferenceUtils.putInt(getContext(), Constants.COOK_MAIN_LIGHTRANK, CLIGHT);
                mTvCrank.setText(LIGHT[CLIGHT]);


                //灯光开启调节亮度
                if (isCLight) {
                    //灯光开启调节亮度
                    switch (CLIGHT) {
                        case 0:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=1;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            break;
                        case 1:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=2;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            break;
                        case 2:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=3;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            break;
                        case 3:
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=4;
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            break;
                        case 4:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=5;
                            break;
                        case 5:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=6;
                            break;
                        case 6:
                            Logger.i(TAG, "餐厅主灯" + CLIGHT);
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=7;
                            break;

                    }

                }
                break;

            case R.id.cl_btn_ctopen:
                Logger.i(TAG, "餐厅筒灯开");
                Send_Controller.isClicked=1;
                Send_Controller.send_type=20;
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_TD_LIGHT, true);
                mBtnCtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnCtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                //实现筒灯开
                break;

            case R.id.cl_btn_ctclose:
                Logger.i(TAG, "餐厅筒灯关");
                Send_Controller.isClicked=1;
                Send_Controller.send_type=21;
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_TD_LIGHT, false);
                mBtnCtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnCtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                //实现筒灯关
                break;

            case R.id.cl_btn_cyopen:
                Logger.i(TAG, "餐厅阳台灯开");
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_YT_LIGHT, true);
                mBtnCyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnCyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
              //  Send_Controller.isClicked=1;
               // Send_Controller.send_type=10;
                break;

            case R.id.cl_btn_cyclose:
                Logger.i(TAG, "餐厅阳台灯关");
                PreferenceUtils.putBoolean(getContext(), Constants.COOK_YT_LIGHT, false);
                mBtnCyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnCyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);

                break;



            //厕所设置
            case R.id.wl_btn_topen:
                Logger.i(TAG, "厕所筒灯灯开");

                PreferenceUtils.putBoolean(getContext(), Constants.WC_TD_LIGHT, true);
                mBtnWtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnWtClose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                //实现筒灯开
                break;

            case R.id.wl_btn_tclose:
                Logger.i(TAG, "厕所筒灯关");
                PreferenceUtils.putBoolean(getContext(), Constants.WC_TD_LIGHT, false);
                mBtnWtOpen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnWtClose.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                //实现筒灯关
                break;

            case R.id.wl_btn_yopen:
                Logger.i(TAG, "公卫灯开");
                PreferenceUtils.putBoolean(getContext(), Constants.WC_YT_LIGHT, true);
                mBtnWyopen.setBackgroundResource(R.drawable.dg_btn_confirm_select);
                mBtnWyclose.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                break;

            case R.id.wl_btn_yclose:
                Logger.i(TAG, "公卫灯关");
                PreferenceUtils.putBoolean(getContext(), Constants.WC_YT_LIGHT, false);
                mBtnWyopen.setBackgroundResource(R.drawable.dg_btn_confirm_normal);
                mBtnWyclose.setBackgroundResource(R.drawable.dg_btn_confirm_select);

                break;


        }
    }
}