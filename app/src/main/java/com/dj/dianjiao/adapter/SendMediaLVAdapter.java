package com.dj.dianjiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.SendMedia;
import com.dj.dianjiao.domain.SendMediaItemClickEvent;
import com.dj.dianjiao.domain.SendMediaViewHolder;
import com.dj.dianjiao.domain.SendPicture;
import com.dj.dianjiao.domain.SendPictureItemClickEvent;
import com.dj.dianjiao.domain.SendPictureViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SendMediaLVAdapter extends BaseAdapter {
    private static final String TAG = "SendMediaLVAdapter";
    private Context mContext;
    private List<SendMedia> sendMediaList;

    public SendMediaLVAdapter(Context context){
        mContext = context;
        sendMediaList = new ArrayList<SendMedia>();
    }

    @Override
    public int getCount() {
        return sendMediaList.size();
    }

    @Override
    public Object getItem(int position) {
        return sendMediaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SendMedia sendMedia = sendMediaList.get(position);
        ImageView pictureIV;
        TextView nameTV;
        TextView countryTV;
        TextView typeTV;
        TextView timeTV;
        CheckBox isCheckCB;
        SendMediaViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.send_media_lv_item, null, false);
            pictureIV = (ImageView)convertView.findViewById(R.id.send_media_lv_item_iv);
            nameTV = (TextView)convertView.findViewById(R.id.send_media_lv_item_name_tv);
            countryTV = (TextView)convertView.findViewById(R.id.send_media_lv_item_country_tv);
            typeTV = (TextView)convertView.findViewById(R.id.send_media_lv_item_type_tv);
            timeTV = (TextView)convertView.findViewById(R.id.send_media_lv_item_time_tv);
            isCheckCB = (CheckBox) convertView.findViewById(R.id.send_media_lv_item_cb);

            viewHolder = new SendMediaViewHolder();
            viewHolder.pictureIV = pictureIV;
            viewHolder.nameTV = nameTV;
            viewHolder.countryTV = countryTV;
            viewHolder.typeTV = typeTV;
            viewHolder.timeTV = timeTV;
            viewHolder.isCheckCB = isCheckCB;

        } else { // Otherwise re-use the converted view
            viewHolder = (SendMediaViewHolder)convertView.getTag();
            pictureIV = viewHolder.pictureIV;
            nameTV = viewHolder.nameTV;
            countryTV = viewHolder.countryTV;
            typeTV = viewHolder.typeTV;
            timeTV = viewHolder.timeTV;
            isCheckCB = viewHolder.isCheckCB;

        }

        pictureIV.setImageResource(R.mipmap.media1);
        nameTV.setText(sendMedia.getName());
        countryTV.setText(sendMedia.getCountry());
        typeTV.setText(sendMedia.getType());
        timeTV.setText(sendMedia.getTime());
        isCheckCB.setChecked(sendMedia.isChecked());

        /*isCheckCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), isChecked));
            }
        });*/

        isCheckCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof CheckBox){
                    EventBus.getDefault().post(new SendMediaItemClickEvent(((CheckBox) v).isChecked()));
                    sendMedia.setIsChecked(((CheckBox) v).isChecked());
                }
            }
        });

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setSendMediaList(List<SendMedia> mSendMediaList){
        if(sendMediaList.size()>0){
            sendMediaList.clear();
        }
        if(mSendMediaList!=null&&mSendMediaList.size()>0){
            sendMediaList.addAll(mSendMediaList);
        }
        notifyDataSetChanged();
    }
}
