package treasurehunt.com.treasurehunt.newsfeed;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import treasurehunt.com.treasurehunt.util.JsonHandler;
import treasurehunt.com.treasurehunt.vo.NewsVO;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class NewsfeedWebService {

    private static final String TAG = "NewsfeedWebService";
    private NewsfeedFragment newsfeedFragment;
    private JsonHandler jsonHandler;

    public NewsfeedWebService(NewsfeedFragment newsfeedFragment){
        this.newsfeedFragment=newsfeedFragment;
        this.jsonHandler=new JsonHandler();
    }

    public void get_newsfeed(ParseGeoPoint geoPoint) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        JSONArray searchTermsJSON = new JSONArray();

        if(geoPoint!=null){
            params.put("pGeoPoint",geoPoint);
            Log.i(TAG,"set geoPoint: getLatitude"+geoPoint.getLatitude()+" Longitude: "+geoPoint.getLongitude());
        }
        final ArrayList<NewsVO> newsVOArrayList = new ArrayList<NewsVO>();
        ParseCloud.callFunctionInBackground("newsfeed_all", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "newsfeed_all: okay");
                    ArrayList<HashMap<String, Object>> objects = (ArrayList<HashMap<String, Object>>) result.get("news");
                    int i = 0;
                    int size = 0;
                    if (objects != null) {
                        size = objects.size();
                    }

                    while (i < size) {
                        String id = jsonHandler.getString(objects.get(i), "id");
                        String user_name = jsonHandler.getString(objects.get(i), "user_name");
                        String user_photo_url = jsonHandler.getString(objects.get(i), "user_photo_url");
                        String photo_url = jsonHandler.getString(objects.get(i), "photo_url");
                        Date createdAt = jsonHandler.getDate(objects.get(i),"createdAt");
                        String desc = jsonHandler.getString(objects.get(i),"desc");

                        NewsVO newsVO = new NewsVO(id,user_name,user_photo_url,createdAt,photo_url,desc);

                        newsVOArrayList.add(newsVO);
                        i++;
                    }

                    newsfeedFragment.createNewsList(newsVOArrayList);

                } else {
                    Log.i(TAG, "newsfeed_all: EXCEPTION: " + e.getMessage());
                }
            }
        });
    }
}
