package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by zhongqinng on 1/1/15.
 */
public class EditTextHandler {
    private String TAG = "EditTextHandler";

    public EditTextHandler(){};

    public EditText set(View v, int resId){
        EditText editText = null;
        try{
            editText = (EditText) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return editText;
    }

    public EditText set(Activity v, int resId){
        EditText editText = null;
        try{
            editText = (EditText) v.findViewById(resId);
        }catch (Exception e){
            Log.e(TAG,"set resId:"+resId+" EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
        return editText;
    }

    public void setVisible(EditText editText,boolean visible){
        if(visible){
            try {
                editText.setVisibility(View.VISIBLE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.VISIBLE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                editText.setVisibility(View.GONE);
            }catch (Exception e){
                Log.e(TAG, "setVisible View.GONE EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean checkVisible(EditText editText) {
        boolean returnBol = false;
        if (editText != null) {
            if (editText.getVisibility() == View.VISIBLE) {
                returnBol = true;
            }
        }
        return returnBol;
    }

    public ArrayList<String> getStringsFromEditText(EditText editText){
        ArrayList<String> returnStrings = new ArrayList<String>();
        if(editText!=null){
            Editable editable = editText.getText();
            if(editable!=null){
                String text = editText.getText().toString();
                if(text!=null){
                    if(!text.isEmpty()){
                        StringTokenizer stringTokenizer = new StringTokenizer(text," ");
                        while(stringTokenizer.hasMoreTokens()){
                            String word = stringTokenizer.nextToken();
                            returnStrings.add(word);
                        }
                    }
                }
            }
        }

        return returnStrings;
    }

    public void clearText(EditText editText){
        if(editText!=null){
            try {
                editText.setText("");
            }catch (Exception e){
                Log.e(TAG, "clearText EXCEPTION:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
