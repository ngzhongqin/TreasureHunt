package treasurehunt.com.treasurehunt.vo;

import android.util.Log;

import java.util.Date;

/**
 * Created by zhongqinng on 18/1/15.
 */
public class NewsVO {
    private String TAG ="NewsVO";
    private String id;
    private String user_name;
    private String user_photo_url;
    private Date created_dt;
    private String photo_url;
    private String desc;

    public NewsVO(String id,
                  String user_name,
                  String user_photo_url,
                  Date created_dt,
                  String photo_url,
                  String desc){
        this.id=id;
        this.user_name=user_name;
        this.user_photo_url=user_photo_url;
        this.created_dt=created_dt;
        this.photo_url=photo_url;
        this.desc=desc;

        Log.i(TAG,"NewsVO created: user_name: "+ user_name
                    +" id: "+id
                    +" user_photo_url:"+user_photo_url
                    +" creatd_dt: "+created_dt
                    +" photo_url: "+photo_url
                    +" desc: "+desc);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_photo_url() {
        return user_photo_url;
    }

    public void setUser_photo_url(String user_photo_url) {
        this.user_photo_url = user_photo_url;
    }

    public Date getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(Date created_dt) {
        this.created_dt = created_dt;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
