package treasurehunt.com.treasurehunt.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import treasurehunt.com.treasurehunt.R;


/**
 * Created by zhongqinng on 2/12/14.
 */
public class FX {

/**
 * @param ctx
 * @param v
 */
    public static void slide_down(Context ctx, View v){
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();

                v.startAnimation(a);
            }
        }
    }
    public static void slide_up(Context ctx, View v){
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();

                v.startAnimation(a);
            }
        }
    }

    public static void slide_left(Context ctx, View v){
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_left);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();

                v.startAnimation(a);
            }
        }
    }

    public static void slide_right(Context ctx, View v){
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_right);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();

                v.startAnimation(a);
            }
        }
    }

}
