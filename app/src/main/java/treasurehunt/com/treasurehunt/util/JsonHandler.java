package treasurehunt.com.treasurehunt.util;

import android.util.Log;

import com.parse.ParseGeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhongqinng on 2/1/15.
 */
public class JsonHandler {
    private String TAG = "JsonHandler";

    public JsonHandler(){};

    public String getString(HashMap<String,Object> hashMap, String key){
        String returnString = null;
        Object object = hashMap.get(key);
        if(object!=null){
            returnString= object.toString();
        }
        return returnString;
    }

    public Number getNumber(HashMap<String,Object> hashMap, String key){
        Number returnNumber = null;
        Object object = hashMap.get(key);
        if(object!=null){
           try{
               returnNumber=Double.parseDouble(object.toString());
           }catch (Exception e){
               Log.e(TAG,"getNumber EXCEPTION: "+e.getMessage());
               e.printStackTrace();
           }
        }
        return returnNumber;
    }

    public Date getDate(HashMap<String,Object> hashMap, String key){
        Date returnDate = null;
        Object object = hashMap.get(key);
        if(object!=null){
            try{
                returnDate= (Date) object;
            }catch (Exception e){
                Log.e(TAG,"getDate EXCEPTION: "+e.getMessage());
                e.printStackTrace();
            }
        }

        return returnDate;

    }

    public ParseGeoPoint getGeoPoint(HashMap<String,Object> hashMap, String key){
        ParseGeoPoint returnGeoPoint = null;
        if(hashMap!=null){
            Object object = hashMap.get(key);
            try{
                returnGeoPoint= (ParseGeoPoint) object;
            }catch (Exception e){
                Log.e(TAG,"getGeoPoint EXCEPTION: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return returnGeoPoint;
    }


    public HashMap<String, Object> getHashMapStringObject(String text, HashMap<String, Object> result) {
        HashMap<String,Object> returnHM = null;
        if(result!=null){
            if(text!=null){
                if(!text.isEmpty()){
                    returnHM = (HashMap<String, Object>) result.get(text);
                }
            }
        }
        return returnHM;
    }

    public ArrayList<HashMap<String, Object>> getArrayList(String text, HashMap<String, Object> hm){
        Log.i(TAG,"getArrayList: text:"+text);
        ArrayList<HashMap<String, Object>> objects = null;
        try {
            objects = (ArrayList<HashMap<String, Object>>) hm.get(text);
        }catch (Exception e){
            Log.e(TAG,"getArrayList: EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }

        return objects;
    }
}

