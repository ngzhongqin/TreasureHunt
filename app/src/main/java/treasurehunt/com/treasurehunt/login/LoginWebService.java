package treasurehunt.com.treasurehunt.login;

import android.util.Log;

import com.parse.FunctionCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.HashMap;

import treasurehunt.com.treasurehunt.util.JsonHandler;


/**
 * Created by zhongqinng on 8/1/15.
 */
public class LoginWebService {
    private static final String TAG = "LoginWebService";
    private LoginActivity mActivity;
    private JsonHandler jsonHandler;

    public LoginWebService(LoginActivity mActivity){
        this.mActivity=mActivity;
        this.jsonHandler=new JsonHandler();
    }

    public LoginWebService(){
        this.jsonHandler=new JsonHandler();
    }

    public void sign_up_team(String team_code) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("team_code",team_code);
        final ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser==null){
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e != null) {
                        Log.i(TAG, "Anonymous login failed.");
                    } else {
                        Log.i(TAG, "Anonymous user logged in.");
                    }
                }
            });
        }


        ParseCloud.callFunctionInBackground("sign_up_team", params, new FunctionCallback<HashMap<String, Object>>() {
            public void done(HashMap<String, Object> result, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "sign_up_team: okay");
                    HashMap<String, Object> team = jsonHandler.getHashMapStringObject("team", result);

                    String team_code = jsonHandler.getString(team,"team_code");

                    if(team_code!=null){
                        if(!team_code.equals("null")){
                            mActivity.sign_up_okay();
                        }else{
                            mActivity.sign_up_not_okay();
                        }
                    }

//                    String id = jsonHandler.getString(treasure, "id");
//                    String name = jsonHandler.getString(treasure, "name");
//                    String clue = jsonHandler.getString(treasure, "clue");
//                    String photo_url = jsonHandler.getString(treasure, "photo_url");
//                    Number points = jsonHandler.getNumber(treasure, "points");
//                    ParseGeoPoint location = jsonHandler.getGeoPoint(treasure, "location");
//                    TreasureVO treasureVO = new TreasureVO(id,name,clue,points,location,photo_url);
//
//                    ArrayList<HashMap<String,Object>> submission = jsonHandler.getArrayList("submission", result);
//                    if(submission!=null){
//                        ArrayList<WordVO> word_list =  new ArrayList<WordVO>();
//                        int i = 0;
//                        int size = submission.size();
//                        while(i<size){
//                            String word = jsonHandler.getString(submission.get(i), "word");
//                            WordVO wordVO = new WordVO(word);
//                            word_list.add(wordVO);
//                            i++;
//                        }
//                        treasureVO.setWord_list(word_list);
//                    }

                } else {
                    Log.i(TAG, "sign_up_team: EXCEPTION: " + e.getMessage());
                }
            }
        });
    }

}
