package treasurehunt.com.treasurehunt.pageflow.menu;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

import treasurehunt.com.treasurehunt.util.JsonHandler;

/**
 * Created by zhongqinng on 30/12/14.
 */
public class ActionBarWebService {
    private String TAG = "ActionBarWebService";
    private ActionBarHandler actionBarHandler;
    private JsonHandler jsonHandler;

    public ActionBarWebService(ActionBarHandler actionBarHandler){
        this.actionBarHandler=actionBarHandler;
        this.jsonHandler=new JsonHandler();
    }

    public void landing_page(){
        Log.d(TAG, "landing_page");

        HashMap<String, Object> params = new HashMap<String, Object>();

        ParseCloud.callFunctionInBackground("cx_landing_page", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "landing_page: okay");
                    HashMap<String, Object> team = jsonHandler.getHashMapStringObject("team", result);
                    String team_name = jsonHandler.getString(team,"team_name");
                    actionBarHandler.setActionBarHandlerAfterWebServiceCall(team_name);

                } else {
                    Log.i(TAG, "landing_page: exception " + e.getMessage());
                }
            }
        });
    }
}
