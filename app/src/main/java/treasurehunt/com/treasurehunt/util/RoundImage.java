package treasurehunt.com.treasurehunt.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;

public class RoundImage{

    String TAG = "RoundImage";

    public RoundImage() {
    }


    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {

        int targetWidth = 125;
        int targetHeight = 125;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        try {
            Log.i(TAG, "getRoundedShape-> inputBitmapHeight: " + scaleBitmapImage.getHeight() +
                    " inputBitmapWidth: " + scaleBitmapImage.getWidth());

            Canvas canvas = new Canvas(targetBitmap);
            Path path = new Path();
            path.addCircle(((float) targetWidth - 1) / 2,
                    ((float) targetHeight - 1) / 2,
                    (Math.min(((float) targetWidth),
                            ((float) targetHeight)) / 2),
                    Path.Direction.CCW);

            canvas.clipPath(path);
            Bitmap sourceBitmap = scaleBitmapImage;
            canvas.drawBitmap(sourceBitmap,
                    new Rect(0, 0, sourceBitmap.getWidth(),
                            sourceBitmap.getHeight()),
                    new Rect(0, 0, targetWidth,
                            targetHeight), null);

        }catch (Exception e){
            e.printStackTrace();
        }
        return targetBitmap;
    }

}