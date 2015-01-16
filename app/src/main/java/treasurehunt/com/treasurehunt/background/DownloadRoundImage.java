package treasurehunt.com.treasurehunt.background;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import treasurehunt.com.treasurehunt.util.RoundImage;


public class DownloadRoundImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String TAG = "Downloadmage";

    public DownloadRoundImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Log.i(TAG,"doInBackground->URL: "+urldisplay);
        Bitmap mIcon11 = null;
        RoundImage roundedImage = new RoundImage();
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(TAG, "Error: "+e.getMessage());
            e.printStackTrace();
        }
        return roundedImage.getRoundedShape(mIcon11);
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}