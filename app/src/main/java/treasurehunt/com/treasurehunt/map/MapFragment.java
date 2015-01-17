package treasurehunt.com.treasurehunt.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.parse.ParseGeoPoint;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.util.location.LocationHandler;


public class MapFragment extends Fragment {
    private String TAG = "MapFragment";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private View v;
    private AssetHandler assetHandler;
    private ViewHolder vh;
    private GoogleMap map;

    class ViewHolder{
        LinearLayout treasure_list;
        LinearLayout spinner_placeholder;
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
        assetHandler = new AssetHandler(getActivity());
        vh.mapView=assetHandler.mapViewHandler.set(v,R.id.mapview);
        vh.mapView.onCreate(savedInstanceState);

        vh.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.setMyLocationEnabled(true);
                // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
                try {
                    MapsInitializer.initialize(getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

