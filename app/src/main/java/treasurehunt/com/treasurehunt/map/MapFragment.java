package treasurehunt.com.treasurehunt.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseGeoPoint;

import java.util.ArrayList;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.util.location.LocationHandler;
import treasurehunt.com.treasurehunt.vo.TreasureVO;


public class MapFragment extends Fragment {
    private String TAG = "MapFragment";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private View v;
    private AssetHandler assetHandler;
    private ViewHolder vh;

    public void createMarkers(final ArrayList<TreasureVO> treasureVOArrayList) {


        vh.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.setMyLocationEnabled(true);

                set_markers(googleMap, treasureVOArrayList);
            }
        });
    }

    private void set_markers(GoogleMap googleMap, ArrayList<TreasureVO> treasureVOArrayList) {
        if(treasureVOArrayList!=null){
            int i = 0;
            int size_i = treasureVOArrayList.size();

            while(i<size_i){
                if(treasureVOArrayList.get(i)!=null){
                    if(treasureVOArrayList.get(i).getName()!=null){
                        if(treasureVOArrayList.get(i).getLocation()!=null){
                            String name = treasureVOArrayList.get(i).getName();
                            Double lat = treasureVOArrayList.get(i).getLocation().getLatitude();
                            Double logi = treasureVOArrayList.get(i).getLocation().getLongitude();

                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lat, logi))
                                    .title(name));

                        }
                    }
                }
                i++;
            }
        }
    }

    class ViewHolder{
        MapView mapView;
    }

    public static final MapFragment newInstance(String message)
    {
        MapFragment f = new MapFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        v = inflater.inflate(R.layout.map_f, container, false);
        vh = new ViewHolder();
        MapWebService mapWebService = new MapWebService(this);
        mapWebService.get_all_treasure(getCurrentLocation());
        assetHandler = new AssetHandler(getActivity());
        vh.mapView=assetHandler.mapViewHandler.set(v,R.id.mapview);
        vh.mapView.onCreate(savedInstanceState);

        return v;
    }

    private ParseGeoPoint getCurrentLocation(){
        LocationHandler locationHandler = new LocationHandler();
        ParseGeoPoint location = locationHandler.getLastKnownLocation(getActivity());
        return location;
    }

    @Override
    public void onResume() {
        super.onResume();
        vh.mapView.onResume();


    }

    @Override
    public void onPause() {
        vh.mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        vh.mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        vh.mapView.onLowMemory();
    }

}

