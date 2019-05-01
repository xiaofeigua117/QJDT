package ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import ui.activity.MainActivity;
import ui.fargment.CenterFargment;
import ui.fargment.ControlFargment;
import ui.fargment.ServiceFargment;
import ui.fargment.StateFargment;

/**
 * Created by Jay on 2015/8/31 0031.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private StateFargment myState = null;
    private ControlFargment myControl = null;
    private CenterFargment myCenter = null;
    private ServiceFargment myService = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myState = new StateFargment();
        myControl = new ControlFargment();
        myCenter = new CenterFargment();
        myService = new ServiceFargment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myState;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myControl;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myCenter;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myService;
                break;
        }
        return fragment;
    }


}

