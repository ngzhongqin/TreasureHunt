package treasurehunt.com.treasurehunt.util.assetHandler;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import treasurehunt.com.treasurehunt.R;

/**
 * Created by zhongqinng on 31/1/15.
 */
public class ToastHandler {
    private FragmentActivity fragmentActivity;
    private String TAG = "ToastHandler";

    public ToastHandler(FragmentActivity fragmentActivity){
        this.fragmentActivity=fragmentActivity;
    }

    public void show_text(String text){
        try {
            LayoutInflater inflater = fragmentActivity.getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout,
                    (ViewGroup) fragmentActivity.findViewById(R.id.toast_layout_root));

            TextView textView = (TextView) layout.findViewById(R.id.text);
            textView.setText(text);

            Toast toast = new Toast(fragmentActivity);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }catch (Exception e){
            Log.i(TAG,"Exception e:"+e.getMessage());
        }
    }
}
