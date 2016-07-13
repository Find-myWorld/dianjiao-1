package com.dj.dianjiao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiItemClickEvent;
import com.dj.dianjiao.domain.JianshiViewHolder;
import com.dj.dianjiao.domain.SendPicture;
import com.dj.dianjiao.domain.SendPictureItemClickEvent;
import com.dj.dianjiao.domain.SendPictureViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SendPictureGVAdapter extends BaseAdapter {
    private static final String TAG = "SendPictureGVAdapter";
    private Context mContext;
    private List<SendPicture> sendPictureList;

    public SendPictureGVAdapter(Context context){
        mContext = context;
        sendPictureList = new ArrayList<SendPicture>();
    }

    @Override
    public int getCount() {
        return sendPictureList.size();
    }

    @Override
    public Object getItem(int position) {
        return sendPictureList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SendPicture sendPicture = sendPictureList.get(position);
        ImageView pictureIV;
        TextView nameTV;
        CheckBox isCheckCB;
        SendPictureViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.send_picture_gv_item, null, false);
            pictureIV = (ImageView)convertView.findViewById(R.id.send_picture_item_iv);
            nameTV = (TextView)convertView.findViewById(R.id.send_picture_item_tv);
            isCheckCB = (CheckBox) convertView.findViewById(R.id.send_picture_item_cb);

            viewHolder = new SendPictureViewHolder();
            viewHolder.typeIV = pictureIV;
            viewHolder.nameTV = nameTV;
            viewHolder.isCheckCB = isCheckCB;

        } else { // Otherwise re-use the converted view
            viewHolder = (SendPictureViewHolder)convertView.getTag();
            pictureIV = viewHolder.typeIV;
            nameTV = viewHolder.nameTV;
            isCheckCB = viewHolder.isCheckCB;

        }

        pictureIV.setImageResource(R.mipmap.picture1);
        nameTV.setText(sendPicture.getName());
        isCheckCB.setChecked(sendPicture.isCheck());

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
                    EventBus.getDefault().post(new SendPictureItemClickEvent(((CheckBox) v).isChecked()));
                    sendPicture.setIsCheck(((CheckBox) v).isChecked());
                }
            }
        });

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setSendPictureList(List<SendPicture> mSendPictureList){
        if(sendPictureList.size()>0){
            sendPictureList.clear();
        }
        if(mSendPictureList!=null&&mSendPictureList.size()>0){
            sendPictureList.addAll(mSendPictureList);
        }
        notifyDataSetChanged();
    }
}
