package treasurehunt.com.treasurehunt.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import treasurehunt.com.treasurehunt.MainActivity;
import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.UserDetailsActivity;
import treasurehunt.com.treasurehunt.background.UploadUser;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;


public class LoginActivity extends FragmentActivity {

    private String TAG = "LoginActivity";
    private Button loginButton;
    private Dialog progressDialog;
    private AssetHandler ah;
    private ViewHolder vh;

    public void sign_up_okay() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null)) {
            showMainActivity();
        }
    }

    public void sign_up_not_okay() {
        ah.toastHandler.show_text("Invalid Access Code");
    }

    class ViewHolder {
        EditText team_code;
        ImageButton submit_btn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prints_key_hash_needed_for_facebook();
        setContentView(R.layout.activity_login);
        vh = new ViewHolder();
        ah = new AssetHandler(this);
        final LoginWebService loginWebService = new LoginWebService(this);

        vh.team_code = ah.editTextHandler.set(this,R.id.team_code);
        vh.submit_btn=ah.imageButtonHandler.set(this,R.id.submit_btn);

        vh.submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team_code = ah.editTextHandler.getStringFromEditText(vh.team_code);
                loginWebService.sign_up_team(team_code);
                ah.keyBoardHandler.hide_keyboard();
                ah.editTextHandler.clearText(vh.team_code);
            }
        });

//        loginButton = (Button) findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onLoginButtonClicked();
//            }
//        });

        // Check if there is a currently logged in user
        // and it's linked to a Facebook account.
        ParseUser currentUser = ParseUser.getCurrentUser();
//        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
//            showMainActivity();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);

    }

    private void onLoginButtonClicked() {
        LoginActivity.this.progressDialog =
                ProgressDialog.show(LoginActivity.this, "", "Logging in...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");
        // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
        // (https://developers.facebook.com/docs/facebook-treasurehunt.com.treasurehunt.login/permissions/)

        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                LoginActivity.this.progressDialog.dismiss();
                if (user == null) {
                    Log.d(TAG, "Uh oh. The user cancelled the Facebook treasurehunt.com.treasurehunt.login.");
                } else if (user.isNew()) {
                    Log.d(TAG, "User signed up and logged in through Facebook!");
//                    showUserDetailsActivity();
                    showMainActivity();
                } else {
                    Log.d(TAG, "User logged in through Facebook!");
//                    showUserDetailsActivity();
                    showMainActivity();
                }
            }
        });
    }

    private void showUserDetailsActivity() {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        startActivity(intent);
    }

    private void showMainActivity() {
        UploadUser uploadUser = new UploadUser();
        uploadUser.makeMeRequest();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void prints_key_hash_needed_for_facebook(){
        Log.i(TAG,"prints_key_hash_needed_for_facebook");
        //This piece of code prints the key hash for upload to facebook app
        try {
            PackageInfo info = getPackageManager().getPackageInfo("treasurehunt.com.treasurehunt", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
               e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
