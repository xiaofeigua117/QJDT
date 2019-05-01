package ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;

/**
 * Created by Lenovo on 2019/3/6.
 */

public class StateButton  extends LinearLayout {

    private TextView tvHumidity;
    private TextView tvTemperature;
    private TextView tvMethanal;
    private TextView tvTitle;
    private ImageView mIvIcon;

    public StateButton(Context context) {
        this(context,null);
    }

    public StateButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public StateButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化布局
        initView();
    }
    /**
     * 初始化布局
     */
    private void initView() {

         View child = View.inflate(getContext(), R.layout.view_state_button,null);

        //温度
        tvTemperature = (TextView) child.findViewById(R.id.tv_sb_temperature);
        tvTitle = (TextView) child.findViewById(R.id.tv_sd_title);
        //湿度
        tvHumidity = (TextView) child.findViewById(R.id.tv_sb_humidity);
        mIvIcon = (ImageView) child.findViewById(R.id.sb_iv_icon);
        //甲醛
        tvMethanal = (TextView) child.findViewById(R.id.tv_sb_methanal);
        addView(child);
    }
    /**
     * 图片是否显示
     *
     * @param icon
     */
    public void setIcon(boolean icon) {

        if (icon) {
            mIvIcon.setVisibility(View.INVISIBLE);
        } else {
            mIvIcon.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 设置小标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置温度
     * @param temperature
     */
    public void setTemperature(String temperature) {
        tvTemperature.setText(temperature);
    }

    /**
     * 设置湿度
     * @param humidity
     */
    public void setHumidity(String humidity) {
        tvHumidity.setText(humidity);
    }
    /**
     * 设置甲醛
     *
     * @param methanal
     */
    public void setMethanal(int methanal) {
        tvMethanal.setText(methanal);
    }


}
