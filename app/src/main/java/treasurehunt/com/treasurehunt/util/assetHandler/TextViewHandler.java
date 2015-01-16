package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class TextViewHandler {
    private String TAG = "TextViewSetter";

    public TextViewHandler(){};

    public TextView set(View v, int resId){
        TextView textView = null;
        try{
            textView = (TextView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return textView;
    }

    public TextView set(Activity v, int resId){
        TextView textView = null;
        try{
            textView = (TextView) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return textView;
    }

    public void setText(TextView v, String text){
        if(v!=null&&text!=null) {
            try {
                v.setText(text);
            } catch (Exception e) {
                Log.e(TAG, "setText text: EXCEPTION: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void setTextViewVisible(TextView textView,boolean visible){
        if(visible){
            try {
                textView.setVisibility(View.VISIBLE);
            }catch (Exception e){
                Log.e(TAG, "setTextViewVisible View.VISIBLE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                textView.setVisibility(View.GONE);
            }catch (Exception e){
                Log.e(TAG, "setTextViewVisible View.GONE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean checkVisible(TextView textView) {
        boolean returnBol = false;
        if (textView != null) {
            if (textView.getVisibility() == View.VISIBLE) {
                returnBol = true;
            }
        }
        return returnBol;
    }
}
