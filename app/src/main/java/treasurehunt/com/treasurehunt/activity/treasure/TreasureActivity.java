package treasurehunt.com.treasurehunt.activity.treasure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import treasurehunt.com.treasurehunt.R;
import treasurehunt.com.treasurehunt.util.assetHandler.AssetHandler;
import treasurehunt.com.treasurehunt.vo.TreasureVO;
import treasurehunt.com.treasurehunt.vo.WordVO;


public class TreasureActivity extends FragmentActivity {
    private static final String TAG = "TreasureActivity";
    private AssetHandler ah;
    private ViewHolder vh;
    private TreasureWebService treasureWebService;
    private TreasureVO treasureVO;

    public void setTreasureVO(final TreasureVO treasureVO) {
        this.treasureVO=treasureVO;

        ah.linearLayoutHandler.setVisible(vh.spinner_placeholder,false);
        ah.textViewHandler.setText(vh.name, treasureVO.getName());
        ah.textViewHandler.setText(vh.clue, treasureVO.getClue());
        ah.imageViewHandler.setRoundImage(vh.photo, treasureVO.getPhoto_url());
        ah.textViewHandler.setText(vh.points, treasureVO.getPoints_string());

        vh.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.setMyLocationEnabled(true);
                set_marker(googleMap,treasureVO);
            }
        });

        createWordList(treasureVO.getWord_list());

        vh.submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    String word = ah.editTextHandler.getStringFromEditText(vh.answer);
                    treasureWebService.submit_word(treasureVO.getId(),word);
                    ah.keyBoardHandler.hide_keyboard();
                    ah.editTextHandler.clearText(vh.answer);
                }

        });
    }

    class ViewHolder{
        LinearLayout spinner_placeholder;
        MapView mapView;
        TextView name;
        TextView clue;
        ImageView photo;
        TextView points;
        LinearLayout word_list;
        EditText answer;
        ImageButton submit_btn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.treasure_a);
        String treasure_id = intent.getStringExtra("treasure_id");
        ah = new AssetHandler(this);
        initialise_assets(savedInstanceState);
        treasureWebService = new TreasureWebService(this);
        treasureWebService.get_treasure(treasure_id);
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialise_assets(Bundle savedInstanceState){
        vh = new ViewHolder();
        vh.mapView = ah.mapViewHandler.set(this,R.id.mapview);
        vh.spinner_placeholder = ah.linearLayoutHandler.set(this,R.id.spinner_placeholder);
        vh.name = ah.textViewHandler.set(this,R.id.name);
        vh.clue = ah.textViewHandler.set(this,R.id.clue);
        vh.photo = ah.imageViewHandler.set(this,R.id.photo);
        vh.points = ah.textViewHandler.set(this,R.id.points);
        vh.word_list = ah.linearLayoutHandler.set(this,R.id.word_list);
        vh.answer = ah.editTextHandler.set(this,R.id.answer);
        vh.submit_btn = ah.imageButtonHandler.set(this,R.id.submit_btn);

        vh.mapView.onCreate(savedInstanceState);
        vh.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.setMyLocationEnabled(true);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        vh.mapView.onResume();


    }

    @Override
    public void onPause() {
        vh.mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        vh.mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        vh.mapView.onLowMemory();
    }


    private void set_marker(GoogleMap googleMap, TreasureVO treasureVO) {

        if(treasureVO!=null){
            if(treasureVO.getName()!=null){
                if(treasureVO.getLocation()!=null){
                    String name = treasureVO.getName();
                    Double lat = treasureVO.getLocation().getLatitude();
                    Double logi = treasureVO.getLocation().getLongitude();

                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, logi))
                            .title(name));
                    moveToCurrentLocation(googleMap,new LatLng(lat, logi));
                }
            }
        }

    }


    public void createWordList(ArrayList<WordVO> arrayList) {
        ah.linearLayoutHandler.removeAllViews(vh.word_list);
        WordAdapter adapter = new WordAdapter(this,this, R.id.treasure_i_word_panel_content,this.treasureVO.getId());
        for (WordVO item : arrayList) {
            adapter.add(item);
        }

        int i = 0;
        int size = adapter.getCount();
        while(i<size){
            View itemView = adapter.getView(i,null,null);
            vh.word_list.addView(itemView);
            i++;
        }

    }

    private void moveToCurrentLocation(GoogleMap googleMap, LatLng currentLocation)
    {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


    }
}
