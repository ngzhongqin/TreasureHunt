package treasurehunt.com.treasurehunt.pageflow.menu;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

/**
 * Created by zhongqinng on 30/12/14.
 */
public class ActionBarWebService {
    private String TAG = "ActionBarWebService";
    private ActionBarHandler actionBarHandler;

    public ActionBarWebService(ActionBarHandler actionBarHandler){
        this.actionBarHandler=actionBarHandler;
    }

    public void landing_page(){
        Log.d(TAG, "landing_page");

        HashMap<String, Object> params = new HashMap<String, Object>();

        ParseCloud.callFunctionInBackground("cx_landing_page", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "landing_page: okay");
                    if (result.get("user_id") != null) {
                        String user_id = result.get("user_id").toString();
                        actionBarHandler.setActionBarHandlerAfterWebServiceCall(user_id);
                    }
                } else {
                    Log.i(TAG, "landing_page: exception " + e.getMessage());
                }
            }
        });
    }
}
