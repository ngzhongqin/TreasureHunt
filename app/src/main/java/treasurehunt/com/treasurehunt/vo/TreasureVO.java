package treasurehunt.com.treasurehunt.vo;

import android.util.Log;

import com.parse.ParseGeoPoint;

import java.text.DecimalFormat;

/**
 * Created by zhongqinng on 17/1/15.
 */
public class TreasureVO {
    private String TAG = "TreasureVO";
    private String id;
    private String name;
    private String clue;
    private Number points;
    private Number distance;
    private ParseGeoPoint location;
    private String photo_url;
    private String points_string;
    private String distance_string;

    public TreasureVO (String id,
                       String name,
                       String clue,
                       Number points,
                       Number distance,
                       ParseGeoPoint location,
                       String photo_url){
        this.id=id;
        this.name=name;
        this.clue=clue;
        this.points=points;
        this.distance=distance;
        this.location=location;
        this.photo_url=photo_url;
    }

    public TreasureVO (String id,
                       String name,
                       String clue,
                       Number points,
                       ParseGeoPoint location,
                       String photo_url){
        this.id=id;
        this.name=name;
        this.clue=clue;
        this.points=points;
        this.location=location;
        this.photo_url=photo_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public Number getPoints() {
        return points;
    }

    public void setPoints(Number points) {
        this.points = points;
    }

    public Number getDistance() {
        return distance;
    }

    public void setDistance(Number distance) {
        this.distance = distance;
    }

    public ParseGeoPoint getLocation() {
        return location;
    }

    public void setLocation(ParseGeoPoint location) {
        this.location = location;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getPoints_string() {
        String rString = "";
        try{
            rString = points.intValue()+ " points";
        }catch (Exception e){
            Log.e(TAG,"getPoints_string EXCEPTION:"+e.getMessage());
        }
        return rString;
    }

    public String getDistance_string() {
        String returnString = "";

        if(distance!=null){
            if(distance.doubleValue()<1){
                try{
                    DecimalFormat format=new DecimalFormat("###");
                    Double mDistance = ((Double)distance)*1000;
                    returnString = format.format(mDistance);
                    returnString = "~ "+ returnString+"m away";
                }catch (Exception e){
                    Log.e(TAG,"getDistanceString: Less than 1 km EXCEPTION: "+e.getMessage());
                    e.printStackTrace();
                }
            }else{
                try{
                    DecimalFormat format=new DecimalFormat("###,###,###.##");
                    returnString = format.format(distance);
                    returnString = "~ "+ returnString+"km away";
                }catch (Exception e){
                    Log.e(TAG,"getDistanceString: more than 1 km EXCEPTION: "+e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return returnString;
    }
}
