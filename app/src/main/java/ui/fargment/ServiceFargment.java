package ui.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.administrator.demo.R;

/**
 * Created by Lenovo on 2018/1/4.
 *
 * 服务界面
 */

public class ServiceFargment extends BaseFragment{

    public ServiceFargment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_service, null);
        WebView webView = (WebView) view.findViewById(R.id.web);
        webView.loadUrl("file:///android_asset/index.html");


        return view;


    }

}
