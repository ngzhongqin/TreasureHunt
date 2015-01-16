package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import treasurehunt.com.treasurehunt.background.DownloadRoundImage;


/**
 * Created by zhongqinng on 1/1/15.
 */
public class ImageViewHandler {
    private String TAG = "ImageViewHandler";

    public ImageViewHandler(){};

    public ImageView set(View v, int resId){
        ImageView imageView = null;
        try{
            imageView = (ImageView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return imageView;
    }

    public ImageView set(Activity v, int resId){
        ImageView imageView = null;
        try{
            imageView = (ImageView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return imageView;
    }

    public void setVisible(ImageView imageView,boolean visible){
        if(visible){
            try {
                imageView.setVisibility(View.VISIBLE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.VISIBLE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                imageView.setVisibility(View.GONE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.GONE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean checkVisible(ImageView imageView) {
        boolean returnBol = false;
        if (imageView != null) {
            if (imageView.getVisibility() == View.VISIBLE) {
                returnBol = true;
            }
        }
        return returnBol;
    }

    public void setRoundImage(ImageView imageView, String small_pict_url) {
        if(imageView!=null){
            if(small_pict_url!=null){
                if(!small_pict_url.isEmpty()){
                    try {
                        new DownloadRoundImage(imageView).execute(small_pict_url);
                    }catch (Exception e){
                        Log.e(TAG, "setRoundImage EXCEPTION:" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
