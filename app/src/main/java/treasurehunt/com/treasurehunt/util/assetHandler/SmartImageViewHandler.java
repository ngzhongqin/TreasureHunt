package treasurehunt.com.treasurehunt.util.assetHandler;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.loopj.android.image.SmartImageView;

/**
 * Created by zhongqinng on 5/1/15.
 */
public class SmartImageViewHandler {
    private String TAG ="SmartImageViewHandler";
    public SmartImageViewHandler(){

    }
    public SmartImageView set(View view, int resId){
        SmartImageView smartImageView = null;
        try{
            smartImageView = (SmartImageView) view.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG, "set resId:" + resId + " EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
        return smartImageView;
    }

    public SmartImageView set(FragmentActivity view, int resId){
        SmartImageView smartImageView = null;
        try{
            smartImageView = (SmartImageView) view.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG, "set resId:" + resId + " EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
        return smartImageView;
    }

    public void setImageURL(SmartImageView smartImageView, String imageURL) {
        try{
            smartImageView.setImageUrl(imageURL);
        }catch (Exception e){
            Log.e(TAG, "set setImageURL EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
