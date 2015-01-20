package treasurehunt.com.treasurehunt.background;

import android.util.Log;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class UploadUser{
    private String TAG = "UploadUser";

    public void makeMeRequest() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {
                            // Create a JSON object to hold the profile info
                            JSONObject userProfile = new JSONObject();
                            try {
                                // Populate the JSON object
                                userProfile.put("facebookId", user.getId());
                                userProfile.put("name", user.getName());
                                if (user.getProperty("gender") != null) {
                                    userProfile.put("gender", user.getProperty("gender"));
                                }
                                if (user.getProperty("email") != null) {
                                    userProfile.put("email", user.getProperty("email"));
                                }

                                // Save the user profile info in a user property
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                currentUser.put("profile", userProfile);
                                Log.i(TAG, "userProfile.getString(\"name\"):" + userProfile.getString("name"));
                                currentUser.put("name", userProfile.getString("name"));
                                currentUser.put("email", userProfile.getString("email"));
                                currentUser.put("facebookId", userProfile.getString("facebookId"));
                                String  facebookPhotoURL = "https://graph.facebook.com/"+userProfile.getString("facebookId")+"/picture?type=large";
                                Log.i(TAG,"facebookPhotoURL:"+facebookPhotoURL);
                                currentUser.put("photo_url", facebookPhotoURL);
                                currentUser.saveInBackground();

                            } catch (JSONException e) {
                                Log.d(TAG, "Error parsing returned user data. " + e);
                            }

                        } else if (response.getError() != null) {
                            if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY) ||
                                    (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
                                Log.d(TAG, "The facebook session was invalidated." + response.getError());
                            } else {
                                Log.d(TAG,
                                        "Some other error: " + response.getError());
                            }
                        }
                    }
                }
        );
        request.executeAsync();
    }
}
