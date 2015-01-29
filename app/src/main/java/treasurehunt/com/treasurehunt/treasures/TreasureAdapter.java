package treasurehunt.com.treasurehunt.treasures;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.activity.treasure.TreasureActivity;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.vo.TreasureVO;


/***
 * ADAPTER
 */

public class TreasureAdapter extends ArrayAdapter<TreasureVO> {

    private static final String TAG = "TreasureAdapter";
    private ViewHolder vh;
    private Context context;
    private FragmentActivity mActivity;
    private AssetHandler assetHandler;
    static class ViewHolder {
        TextView name;
        TextView clue;
        ImageView photo;
        TextView points;
        TextView distance;
        Button treasure_btn;
    }

    private final LayoutInflater mLayoutInflater;

    public TreasureAdapter(FragmentActivity mActivity, final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
        this.mActivity=mActivity;
        mLayoutInflater = LayoutInflater.from(context);
        assetHandler = new AssetHandler(mActivity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.treasures_i_treasure, parent, false);
            vh = new ViewHolder();

            vh.name = assetHandler.textViewHandler.set(convertView,R.id.name);
            vh.clue = assetHandler.textViewHandler.set(convertView,R.id.clue);
            vh.photo = assetHandler.imageViewHandler.set(convertView,R.id.photo);
            vh.points = assetHandler.textViewHandler.set(convertView,R.id.points);
            vh.distance = assetHandler.textViewHandler.set(convertView,R.id.distance);
            vh.treasure_btn = assetHandler.buttonHandler.set(convertView,R.id.treasure_btn);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(getItem(position)!=null) {
            assetHandler.textViewHandler.setText(vh.name, getItem(position).getName());
            assetHandler.textViewHandler.setText(vh.clue, getItem(position).getClue());
            assetHandler.imageViewHandler.setRoundImage(vh.photo,getItem(position).getPhoto_url());
            assetHandler.textViewHandler.setText(vh.points,getItem(position).getPoints_string());
            assetHandler.textViewHandler.setText(vh.distance,getItem(position).getDistance_string());

            vh.treasure_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if(getItem(position)!=null) {
                        navigator_to_treasure(getItem(position).getId());
                    }
                }
            });
        }
        return convertView;
    }

    private void navigator_to_treasure(String treasure_id){
        Intent myIntent = new Intent(mActivity, TreasureActivity.class);
        myIntent.putExtra("treasure_id", treasure_id); //Optional parameters
        mActivity.startActivity(myIntent);
    }
}