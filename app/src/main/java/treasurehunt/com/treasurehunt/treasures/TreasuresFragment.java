package treasurehunt.com.treasurehunt.treasures;

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
import treasurehunt.com.treasurehunt.vo.TreasureVO;


public class TreasuresFragment extends Fragment {
    private String TAG = "TreasureFragment";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private View v;
    private AssetHandler assetHandler;
    private ViewHolder vh;

    class ViewHolder{
        LinearLayout treasure_list;
        LinearLayout spinner_placeholder;
    }

    public static final TreasuresFragment newInstance(String message)
    {
        TreasuresFragment f = new TreasuresFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        v = inflater.inflate(R.layout.treasures_f, container, false);
        initialise();
        TreasuresWebService treasureWebService = new TreasuresWebService(this);
        treasureWebService.get_all_treasure(getCurrentLocation());
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
        vh.treasure_list=assetHandler.linearLayoutHandler.set(v,R.id.treasure_list);
        vh.spinner_placeholder=assetHandler.linearLayoutHandler.set(v,R.id.spinner_placeholder);
    }

    public void createTreasureList(ArrayList<TreasureVO> treasureVOArrayList) {
        assetHandler.linearLayoutHandler.removeAllViews(vh.treasure_list);
        TreasureAdapter treasureAdapter = new TreasureAdapter(getActivity(),getActivity(), R.id.treasure_i_treasure_panel_content);
        for (TreasureVO item : treasureVOArrayList) {
            treasureAdapter.add(item);
        }

        int i = 0;
        int size = treasureAdapter.getCount();
        while(i<size){
            View itemView = treasureAdapter.getView(i,null,null);
            vh.treasure_list.addView(itemView);
            i++;
        }

        assetHandler.linearLayoutHandler.setVisible(vh.spinner_placeholder, false);
    }
}

