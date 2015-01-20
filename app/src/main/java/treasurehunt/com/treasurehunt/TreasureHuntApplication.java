package treasurehunt.com.treasurehunt;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by zhongqinng on 16/11/14.
 */
public class TreasureHuntApplication extends Application{
    static final String TAG = "TreasureHuntApplication";

    @Override
    public void onCreate() {
        super.onCreate();


        //DEV
        String PARSE_APPLICATION_ID = "rk1v3RouP6gF5CqF2QzuUej7fc4rucqKtP9pwhBA";
        String PARSE_CLIENT_ID ="6kIjkIJ1hVH3s9SZpTlILKn94XPGTB9sqDz5gxcA";
        String FACEBOOK_APP_ID = "327369334135631";

//        //PROD
//        String PARSE_APPLICATION_ID = "69biQbV97YaBt7vLNVF68a0GXg8KqVX64xkez3g3";
//        String PARSE_CLIENT_ID ="xBneYs1YBRGPZbIKVo0BNiBpRbRnLOg2bPPTBu8Y";
//        String FACEBOOK_APP_ID = "341101996092176";



        Parse.initialize(this,
                PARSE_APPLICATION_ID,
                PARSE_CLIENT_ID
        );

        // Set your Facebook App Id in strings.xml
        Log.i(TAG,"GetMoreApplication: FACEBOOK_APP_ID:"+FACEBOOK_APP_ID);
        ParseFacebookUtils.initialize(FACEBOOK_APP_ID);
    }
}
