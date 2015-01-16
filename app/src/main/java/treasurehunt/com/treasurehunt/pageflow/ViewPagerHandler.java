package treasurehunt.com.treasurehunt.pageflow;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import treasurehunt.com.treasurehunt.pageflow.menu.ActionBarHandler;


/**
 * Created by zhongqinng on 30/12/14.
 */
public class ViewPagerHandler {
    private String TAG = "ViewPagerHandler";
    private FragmentActivity mActivity;

    public ViewPagerHandler(){
    }

    public ViewPager setPagerOnPageChangeListener(ViewPager viewPager, final ActionBarHandler actionBarHandler){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                Log.d(TAG, "page: " + index);
                switch (index){
                    case 0:
                        actionBarHandler.set_home_btn_active();
                        break;
                    case 1:
                        actionBarHandler.set_friends_btn_active();
                        break;
                    case 2:
                        actionBarHandler.set_points_btn_active();
                        break;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        return viewPager;

    }
}
