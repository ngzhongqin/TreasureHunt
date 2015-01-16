package treasurehunt.com.treasurehunt.pageflow.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;


/**
 * Created by zhongqinng on 30/12/14.
 */
public class ActionBarHandler {
    public String TAG = "ActionBarHandler";
    public Activity mActivity;
    private View cView;
    private ActionBarViewHolder actionBarViewHolder;
    private ActionBar actionBar;
    private ViewPager pager;
    private AssetHandler assetHandler;

    public void setPager(ViewPager pager) {
        this.pager = pager;
        setMenuButtonsWithViewPager();
    }

    class ActionBarViewHolder{
        ImageButton home_btn;
        ImageButton friends_btn;
        ImageButton points_btn;
        TextView user_id;
        LinearLayout spinner_placeholder;

    }

    public ActionBarHandler(FragmentActivity mActivity, ViewPager pager){
        this.pager=pager;
        this.mActivity=mActivity;
        actionBarViewHolder = new ActionBarViewHolder();
        assetHandler = new AssetHandler(mActivity);

    }

    public void restoreActionBar() {


        setActionBar();
        ActionBarWebService actionBarWebService = new ActionBarWebService(this);
        actionBarWebService.landing_page();



    }



    private void set_home_btn_selected(boolean selected){
        if(selected){
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.home_btn, R.drawable.home_u);
        }else{
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.home_btn,R.drawable.home_grey);
        }
    }

    private void set_friends_btn_selected(boolean selected){
        if(selected){
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.friends_btn,R.drawable.friends_u);
        }else{
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.friends_btn,R.drawable.friends_grey);
        }
    }

    private void set_points_btn_selected(boolean selected){
        if(selected){
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.points_btn,R.drawable.points_u);
        }else{
            assetHandler.imageButtonHandler.setImageButtonImageResource(actionBarViewHolder.points_btn,R.drawable.points_grey);
        }
    }

    public void setActionBar(){
        actionBar = mActivity.getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM);
        cView = mActivity.getLayoutInflater().inflate(R.layout.actionbar, null);

        actionBarViewHolder.home_btn = assetHandler.imageButtonHandler.set(cView,R.id.home_btn);
        actionBarViewHolder.friends_btn = assetHandler.imageButtonHandler.set(cView,R.id.friends_btn);
        actionBarViewHolder.points_btn = assetHandler.imageButtonHandler.set(cView,R.id.points_btn);
        actionBarViewHolder.user_id = assetHandler.textViewHandler.set(cView,R.id.user_id);
        actionBarViewHolder.spinner_placeholder = assetHandler.linearLayoutHandler.set(cView,R.id.spinner_placeholder);

        setMenuButtonsWithViewPager();

        actionBar.setCustomView(cView);
    }

    public void setMenuButtonsWithViewPager(){
        actionBarViewHolder.home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "actionBarViewHolder.home_btn.setOnClickListener");
//                Toast.makeText(mActivity.getApplicationContext(), "Home Btn", Toast.LENGTH_SHORT).show();
                set_home_btn_active();
                pager.setCurrentItem(0);

            }
        });


        actionBarViewHolder.friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "actionBarViewHolder.friends_btn.setOnClickListener");
//                Toast.makeText(mActivity.getApplicationContext(), "Friends Btn", Toast.LENGTH_SHORT).show();
                set_friends_btn_active();
                pager.setCurrentItem(1);

            }
        });


        actionBarViewHolder.points_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "actionBarViewHolder.points_btn.setOnClickListener");
//                Toast.makeText(mActivity.getApplicationContext(), "Points Btn", Toast.LENGTH_SHORT).show();
                set_points_btn_active();
                pager.setCurrentItem(2);

            }
        });
    }

    public void set_home_btn_active(){
        set_home_btn_selected(true);
        set_friends_btn_selected(false);
        set_points_btn_selected(false);
    }

    public void set_friends_btn_active(){
        set_home_btn_selected(false);
        set_friends_btn_selected(true);
        set_points_btn_selected(false);
    }

    public void set_points_btn_active(){
        set_home_btn_selected(false);
        set_friends_btn_selected(false);
        set_points_btn_selected(true);
    }

    public void setActionBarHandlerAfterWebServiceCall(String user_id) {
        setActionBar();
        assetHandler.linearLayoutHandler.setVisible(actionBarViewHolder.spinner_placeholder, false);
        assetHandler.textViewHandler.setText(actionBarViewHolder.user_id,"ID: "+user_id);

        actionBar.setCustomView(cView);
    }

}
