package treasurehunt.com.treasurehunt.activity.treasure;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;

import java.util.HashMap;

import treasurehunt.com.treasurehunt.util.JsonHandler;
import treasurehunt.com.treasurehunt.vo.TreasureVO;


/**
 * Created by zhongqinng on 8/1/15.
 */
public class TreasureWebService {
    private static final String TAG = "TreasureWebService";
    private TreasureActivity mActivity;
    private JsonHandler jsonHandler;

    public TreasureWebService(TreasureActivity mActivity){
        this.mActivity=mActivity;
        this.jsonHandler=new JsonHandler();
    }

    public void get_treasure(String treasure_id) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("treasure_id",treasure_id);

        ParseCloud.callFunctionInBackground("get_treasure", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "get_treasure: okay");
                    HashMap<String, Object> treasure = jsonHandler.getHashMapStringObject("treasure", result);

                    String id = jsonHandler.getString(treasure, "id");
                    String name = jsonHandler.getString(treasure, "name");
                    String clue = jsonHandler.getString(treasure, "clue");
                    String photo_url = jsonHandler.getString(treasure, "photo_url");
                    Number points = jsonHandler.getNumber(treasure, "points");
                    ParseGeoPoint location = jsonHandler.getGeoPoint(treasure, "location");
                    TreasureVO treasureVO = new TreasureVO(id,name,clue,points,location,photo_url);


                    mActivity.setTreasureVO(treasureVO);

                } else {
                    Log.i(TAG, "get_treasure: EXCEPTION: " + e.getMessage());
                }
            }
        });
    }

//    public void join_as_member(String shop_id) {
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        params.put("shop_id",shop_id);
//
//        ParseCloud.callFunctionInBackground("cx_join_as_member", params, new FunctionCallback<HashMap<String, Object>>() {
//            public void done(HashMap<String, Object> result, ParseException e) {
//                if (e == null) {
//                    Log.i(TAG, "cx_join_as_member: okay");
//
//                } else {
//                    Log.i(TAG, "cx_join_as_member: EXCEPTION: " + e.getMessage());
//                }
//            }
//        });
//    }
}
