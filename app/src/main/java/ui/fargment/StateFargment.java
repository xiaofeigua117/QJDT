package ui.fargment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo.R;

import ui.view.StateButton;

/**
 * Created by Lenovo on 2018/1/4
 * 显示界面
 */

public class StateFargment extends BaseFragment implements View.OnClickListener {


    private View view;
    private ImageView mIvSingnal;
    private StateButton mSbSanlonn;
    private StateButton mSbDining;
    private StateButton mSbWc;
    private TextView mTvshudu;
    private TextView mTvState;
    private TextView mTvwendu;
    private TextView mTvjiaquan;
    private TextView mTvpm10;
    private TextView mTvpm2;
    private TextView mTvey;
    private TextView mTvkongqi;
    private ImageView mIvSingna2;
    private ImageView mIvSingna3;

    public StateFargment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载界面
        view = View.inflate(getActivity(), R.layout.fargment_state, null);

        initView();
        return view;


    }

    private void initView() {
        mIvSingnal = (ImageView) view.findViewById(R.id.iv_sf_signal);
        mIvSingna2 = (ImageView) view.findViewById(R.id.iv_sf_signa2);
        mIvSingna3 = (ImageView) view.findViewById(R.id.iv_sf_signa3);
        mTvState = (TextView) view.findViewById(R.id.fs_tv_state);
        mTvshudu = (TextView) view.findViewById(R.id.fs_tv_shudu);
        mTvwendu = (TextView) view.findViewById(R.id.fs_tv_wendu);
        mTvjiaquan = (TextView) view.findViewById(R.id.fs_tv_jiaquan);
        mTvpm10 = (TextView) view.findViewById(R.id.fs_tv_pm10);
        mTvpm2 = (TextView) view.findViewById(R.id.fs_tv_pmm2);
        mTvey = (TextView) view.findViewById(R.id.fs_tv_ey);

        mTvkongqi = (TextView) view.findViewById(R.id.fs_tv_kongqizhil);
        mSbSanlonn = (StateButton) view.findViewById(R.id.fc_sb_saloon);
        mSbDining = (StateButton) view.findViewById(R.id.fc_sb_dining);
        mSbWc = (StateButton) view.findViewById(R.id.fc_sb_wc);

        mSbSanlonn.setOnClickListener(this);
        mSbDining.setOnClickListener(this);
        mSbWc.setOnClickListener(this);
        mSbSanlonn.setIcon(true);
        AnimationDrawable anim = (AnimationDrawable) mIvSingnal.getBackground();
        anim.start();
        AnimationDrawable anim1 = (AnimationDrawable) mIvSingna2.getBackground();
        anim1.start();

        AnimationDrawable anim2 = (AnimationDrawable) mIvSingna3.getBackground();
        anim2.start();



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fc_sb_saloon:
                mSbSanlonn.setIcon(true);
                mSbDining.setIcon(false);
                mSbWc.setIcon(false);
                mTvState.setText("客厅状态");
                mIvSingnal.setVisibility(View.VISIBLE);
                mIvSingna2.setVisibility(View.INVISIBLE);
                mIvSingna3.setVisibility(View.INVISIBLE);
                break;
            case R.id.fc_sb_dining:
                mSbSanlonn.setIcon(false);
                mSbDining.setIcon(true);
                mSbWc.setIcon(false);
                mIvSingnal.setVisibility(View.INVISIBLE);
                mIvSingna2.setVisibility(View.VISIBLE);
                mIvSingna3.setVisibility(View.INVISIBLE);
                mTvState.setText("餐厅状态");
                break;
            case R.id.fc_sb_wc:
                mSbSanlonn.setIcon(false);
                mSbDining.setIcon(false);
                mSbWc.setIcon(true);
                mIvSingnal.setVisibility(View.INVISIBLE);
                mIvSingna2.setVisibility(View.INVISIBLE);
                mIvSingna3.setVisibility(View.VISIBLE);
                mTvState.setText("公卫状态");
                break;
        }
    }
}
