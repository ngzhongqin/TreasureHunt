package treasurehunt.com.treasurehunt.newsfeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.parse.ParseGeoPoint;

import java.util.ArrayList;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.util.location.LocationHandler;
import treasurehunt.com.treasurehunt.vo.NewsVO;


public class NewsfeedFragment extends Fragment {
    private String TAG = "NewsfeedFragment";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private View v;
    private AssetHandler assetHandler;
    private ViewHolder vh;

    class ViewHolder{
        LinearLayout news_list;
        LinearLayout spinner_placeholder;
    }

    public static final NewsfeedFragment newInstance(String message)
    {
        NewsfeedFragment f = new NewsfeedFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        v = inflater.inflate(R.layout.newsfeed_f, container, false);
        initialise();
        NewsfeedWebService newsfeedWebService = new NewsfeedWebService(this);
        newsfeedWebService.get_newsfeed(getCurrentLocation());
        return v;
    }

    private ParseGeoPoint getCurrentLocation(){
        LocationHandler locationHandler = new LocationHandler();
        ParseGeoPoint location = locationHandler.getLastKnownLocation(getActivity());
        return location;
    }

    private void initialise(){
        vh = new ViewHolder();
        assetHandler = new AssetHandler(getActivity());
        vh.news_list=assetHandler.linearLayoutHandler.set(v,R.id.news_list);
        vh.spinner_placeholder=assetHandler.linearLayoutHandler.set(v,R.id.spinner_placeholder);
    }

    public void createNewsList(ArrayList<NewsVO> newsVOArrayList) {
        assetHandler.linearLayoutHandler.removeAllViews(vh.news_list);
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(),getActivity(), R.id.newsfeed_i_news_panel_content);
        for (NewsVO item : newsVOArrayList) {
            newsAdapter.add(item);
        }

        int i = 0;
        int size = newsAdapter.getCount();
        while(i<size){
            View itemView = newsAdapter.getView(i,null,null);
            vh.news_list.addView(itemView);
            i++;
        }

        assetHandler.linearLayoutHandler.setVisible(vh.spinner_placeholder, false);
    }
}

