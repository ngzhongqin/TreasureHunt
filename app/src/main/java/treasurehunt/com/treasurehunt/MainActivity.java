package treasurehunt.com.treasurehunt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import treasurehunt.com.treasurehunt.pageflow.MyPageAdapter;
import treasurehunt.com.treasurehunt.pageflow.ViewPagerHandler;
import treasurehunt.com.treasurehunt.pageflow.menu.ActionBarHandler;

public class MainActivity extends FragmentActivity {
    private String TAG = "MainActivity";
    private MyPageAdapter pageAdapter;
    private ActionBarHandler actionBarHandler;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        actionBarHandler = new ActionBarHandler(this, pager);
        actionBarHandler.restoreActionBar();
        pager = (ViewPager)findViewById(R.id.viewpager);
        ViewPagerHandler viewPagerHandler = new ViewPagerHandler();
        viewPagerHandler.setPagerOnPageChangeListener(pager,actionBarHandler);
        pager.setAdapter(pageAdapter);
        actionBarHandler.setPager(pager);
    }



    public List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();

//        fList.add(HomeFragment.newInstance("1"));
//        fList.add(MyFragment.newInstance("2"));
//        fList.add(MyFragment.newInstance("3"));

        return fList;
    }



}
