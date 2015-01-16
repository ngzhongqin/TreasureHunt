package treasurehunt.com.treasurehunt.util.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.parse.ParseGeoPoint;

import java.util.List;

/**
 * Created by zhongqinng on 2/1/15.
 */
public class LocationHandler {
    private String TAG = "LocationHandler";
    public LocationHandler(){

    }

    public ParseGeoPoint getLastKnownLocation(FragmentActivity fragmentActivity){
        ParseGeoPoint returnGeoPoint = null;
        try {
            LocationListener locationListener = new MyLocationListener();
            LocationManager locationManager = (LocationManager) fragmentActivity.getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 35000, 10, locationListener);
            List<String> providers = locationManager.getProviders(true);
            Location location = null;
            int i = 0;
            int size = providers.size();
            while (i < size) {
                location = locationManager.getLastKnownLocation(providers.get(i));
                if (location != null) {
                    break;
                }
                i++;
            }
            returnGeoPoint = new ParseGeoPoint(location.getLatitude(),location.getLongitude());

        }catch (Exception e){
            Log.e(TAG, "getLastKnownLocation EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }

        return returnGeoPoint;
    }

}
