package com.dj.dianjiao.domain;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SendMediaViewHolder implements Serializable{
    public ImageView pictureIV;
    public TextView nameTV;
    public TextView typeTV;
    public TextView countryTV;
    public TextView timeTV;
    public CheckBox isCheckCB;
}
