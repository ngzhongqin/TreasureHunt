package treasurehunt.com.treasurehunt.activity.treasure;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.vo.WordVO;


/***
 * ADAPTER
 */

public class WordAdapter extends ArrayAdapter<WordVO> {

    private static final String TAG = "WordAdapter";
    private final String treasure_id;
    private ViewHolder vh;
    private Context context;
    private FragmentActivity mActivity;
    private AssetHandler assetHandler;

    class ViewHolder {
        EditText word;
        ImageButton submit_btn;
    }

    private final LayoutInflater mLayoutInflater;

    public WordAdapter(FragmentActivity mActivity, final Context context, final int textViewResourceId, final String treasure_id) {
        super(context, textViewResourceId);
        this.context = context;
        this.mActivity=mActivity;
        mLayoutInflater = LayoutInflater.from(context);
        assetHandler = new AssetHandler(mActivity);
        this.treasure_id=treasure_id;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.treasure_i_word, parent, false);
            vh = new ViewHolder();

            vh.word = assetHandler.editTextHandler.set(convertView,R.id.word);
            vh.submit_btn = assetHandler.imageButtonHandler.set(convertView,R.id.submit_btn);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(getItem(position)!=null) {
            assetHandler.editTextHandler.setEnable(vh.word, false);
            if(getItem(position).getWord()!=null){
                if(!getItem(position).getWord().equals("null")){
                    assetHandler.textViewHandler.setText(vh.word, getItem(position).getWord());
                    assetHandler.imageButtonHandler.setImageButtonImageResource(vh.submit_btn,R.drawable.tick);
                }else{
                    set_btn(position);
                }
            }else{
                set_btn(position);
            }
        }
        return convertView;
    }


    private void set_btn(final int position){
        vh.submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (getItem(position) != null) {
                    TreasureWebService treasureWebService = new TreasureWebService();

                    String word = assetHandler.editTextHandler.getStringFromEditText(vh.word);
                    treasureWebService.submit_word(treasure_id,word);
                }
            }
        });
    }

}