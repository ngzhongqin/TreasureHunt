package treasurehunt.com.treasurehunt.newsfeed;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.vo.NewsVO;


/***
 * ADAPTER
 */

public class NewsAdapter extends ArrayAdapter<NewsVO> {

    private static final String TAG = "NewsAdapter";
    private ViewHolder vh;
    private Context context;
    private FragmentActivity mActivity;
    private AssetHandler assetHandler;
    static class ViewHolder {
        TextView user_name;
        ImageView user_photo;
        TextView desc;
        SmartImageView photo;
        Button news_btn;
    }

    private final LayoutInflater mLayoutInflater;

    public NewsAdapter(FragmentActivity mActivity, final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
        this.mActivity=mActivity;
        mLayoutInflater = LayoutInflater.from(context);
        assetHandler = new AssetHandler(mActivity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.newsfeed_i_news, parent, false);
            vh = new ViewHolder();

            vh.user_name = assetHandler.textViewHandler.set(convertView,R.id.user_name);
            vh.desc = assetHandler.textViewHandler.set(convertView,R.id.desc);
            vh.photo = assetHandler.smartImageViewHandler.set(convertView,R.id.photo);
            vh.user_photo = assetHandler.imageViewHandler.set(convertView,R.id.user_photo);
            vh.news_btn = assetHandler.buttonHandler.set(convertView,R.id.news_btn);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(getItem(position)!=null) {
            assetHandler.textViewHandler.setText(vh.user_name, getItem(position).getUser_name());
            assetHandler.textViewHandler.setText(vh.desc, getItem(position).getDesc());
            assetHandler.smartImageViewHandler.setImageURL(vh.photo,getItem(position).getPhoto_url());
            assetHandler.imageViewHandler.setRoundImage(vh.user_photo, getItem(position).getUser_photo_url());

            vh.news_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (getItem(position) != null) {
                        navigator_to_treasure(getItem(position).getId());
                    }
                }
            });
        }
        return convertView;
    }

    private void navigator_to_treasure(String shop_id){
//        Intent myIntent = new Intent(mActivity, StoreFrontGuestActivity.class);
//        myIntent.putExtra("shop_id", shop_id); //Optional parameters
//        mActivity.startActivity(myIntent);
    }
}