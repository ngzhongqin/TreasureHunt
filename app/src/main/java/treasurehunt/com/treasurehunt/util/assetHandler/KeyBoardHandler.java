package treasurehunt.com.treasurehunt.util.assetHandler;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhongqinng on 8/12/14.
 */
public class KeyBoardHandler {
    private static final String TAG = "KeyBoardHandler" ;
    private Activity mActivity;

    public KeyBoardHandler(Activity mActivity){
        this.mActivity = mActivity;
    }

    public void hide_keyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = mActivity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(mActivity);
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch (Exception e){
            Log.e(TAG,"hide_keyboard: EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
