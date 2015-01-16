package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class ButtonHandler {
    private String TAG = "ButtonHandler";

    public ButtonHandler(){};

    public Button set(View v, int resId){
        Button button = null;
        try{
            button = (Button) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return button;
    }

    public Button set(Activity v, int resId){
        Button button = null;
        try{
            button = (Button) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return button;
    }

    public void setText(Button button, String text){
        if(button!=null&&text!=null) {
            try {
                button.setText(text);
            } catch (Exception e) {
                Log.e(TAG, "setText text: EXCEPTION: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void setVisible(Button button,boolean visible){
        if(visible){
            try {
                button.setVisibility(View.VISIBLE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.VISIBLE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                button.setVisibility(View.GONE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.GONE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean checkTextViewVisible(Button button) {
        boolean returnBol = false;
        if (button != null) {
            if (button.getVisibility() == View.VISIBLE) {
                returnBol = true;
            }
        }
        return returnBol;
    }

    public void setTextBold(Button button, boolean bold){
        if(bold){
            try{
                button.setTypeface(Typeface.DEFAULT_BOLD);
            }catch (Exception e){
                Log.e(TAG,"setTextBold EXCEPTION: "+e.getMessage());
                e.printStackTrace();
            }
        }else {
            try{
                button.setTypeface(Typeface.DEFAULT);
            }catch (Exception e){
                Log.e(TAG,"setTextBold EXCEPTION: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
