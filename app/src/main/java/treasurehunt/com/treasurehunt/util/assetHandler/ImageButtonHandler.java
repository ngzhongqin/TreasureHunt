package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class ImageButtonHandler {
    private String TAG = "ImageButtonSetter";

    public ImageButtonHandler(){

    }

    public ImageButton set(View v, int resId){
        ImageButton imageButton = null;
        try{
            imageButton = (ImageButton) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return imageButton;
    }

    public ImageButton set(Activity v, int resId){
        ImageButton imageButton = null;
        try{
            imageButton = (ImageButton) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return imageButton;
    }

    public void setImageButtonImageResource(ImageButton imageButton, int resId){
        if(imageButton!=null){
            try{
                imageButton.setImageResource(resId);
            }catch (Exception e){
                Log.e(TAG,"setImageButtonImageResource :imageButton: "+imageButton+" EXCEPTION: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
