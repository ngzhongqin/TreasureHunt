package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.MapView;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class MapViewHandler {
    private String TAG = "MapViewHandler";

    public MapViewHandler(){};

    public MapView set(View v, int resId){
        MapView mapView = null;
        try{
            mapView = (MapView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return mapView;
    }

    public MapView set(Activity v, int resId){
        MapView mapView = null;
        try{
            mapView = (MapView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return mapView;
    }

    public void setVisible(MapView mapView, boolean visible){
        if(visible){
            try {
                mapView.setVisibility(View.VISIBLE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.VISIBLE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                mapView.setVisibility(View.GONE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.GONE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
