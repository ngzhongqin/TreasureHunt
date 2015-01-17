package treasurehunt.com.treasurehunt.map;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import treasurehunt.com.treasurehunt.util.JsonHandler;
import treasurehunt.com.treasurehunt.vo.TreasureVO;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class MapWebService {

    private static final String TAG = "MapWebService";
    private MapFragment mapFragment;
    private JsonHandler jsonHandler;

    public MapWebService(MapFragment mapFragment){
        this.mapFragment=mapFragment;
        this.jsonHandler=new JsonHandler();
    }

    public void get_all_treasure(ParseGeoPoint geoPoint) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        JSONArray searchTermsJSON = new JSONArray();

        if(geoPoint!=null){
            params.put("pGeoPoint",geoPoint);
            Log.i(TAG,"set geoPoint: getLatitude"+geoPoint.getLatitude()+" Longitude: "+geoPoint.getLongitude());
        }
        final ArrayList<TreasureVO> treasureVOArrayList = new ArrayList<TreasureVO>();
        ParseCloud.callFunctionInBackground("treasure_all", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "treasure_all: okay");
                    ArrayList<HashMap<String, Object>> objects = (ArrayList<HashMap<String, Object>>) result.get("treasures");
                    int i = 0;
                    int size = 0;
                    if (objects != null) {
                        size = objects.size();
                    }

                    while (i < size) {
                        String id = jsonHandler.getString(objects.get(i), "id");
                        String name = jsonHandler.getString(objects.get(i), "name");
                        String clue = jsonHandler.getString(objects.get(i), "clue");
                        String photo_url = jsonHandler.getString(objects.get(i), "photo_url");
                        ParseGeoPoint location = jsonHandler.getGeoPoint(objects.get(i), "location");
                        Number points = jsonHandler.getNumber(objects.get(i), "points");
                        Number distance = jsonHandler.getNumber(objects.get(i), "distance");
                        TreasureVO treasureVO = new TreasureVO(id,name,clue,points,distance,location,photo_url);

                        treasureVOArrayList.add(treasureVO);
                        i++;
                    }

                    mapFragment.createMarkers(treasureVOArrayList);

                } else {
                    Log.i(TAG, "treasure_all: EXCEPTION: " + e.getMessage());
                }
            }
        });
    }
}
