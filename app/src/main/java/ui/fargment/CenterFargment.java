package ui.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ui.activity.UserActivity;

/**
 * Created by Lenovo on 2018/1/4.
 * 个人中心
 */

public class CenterFargment extends BaseFragment {


    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.button_user)
    Button buttonUser;
    @BindView(R.id.button_lock)
    Button buttonLock;
    @BindView(R.id.button_view)
    Button buttonView;
    @BindView(R.id.button_wifi)
    Button buttonWifi;
    @BindView(R.id.button_system)
    Button buttonSystem;
    @BindView(R.id.button_bell)
    Button buttonBell;
    @BindView(R.id.button_lamp)
    Button buttonLamp;
    @BindView(R.id.button_wiring)
    Button buttonWiring;
    Unbinder unbinder;

    public CenterFargment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {






         //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_center, null);
        unbinder = ButterKnife.bind(this, view);
        return view;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title, R.id.button_user, R.id.button_lock, R.id.button_view, R.id.button_wifi, R.id.button_system, R.id.button_bell, R.id.button_lamp, R.id.button_wiring})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                break;
            case R.id.button_user:
                //用户
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_lock:
                //门锁
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_view:
                //视频
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_wifi:
                //wifi
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_system:
                //系统
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_bell:
                //门铃(要更改的不部分)
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_lamp:
                //灯光
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
            case R.id.button_wiring:
                //电器设置
                startActivity(new Intent(getActivity(),UserActivity.class));
                break;
        }
    }
}
