package com.dj.dianjiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.SendPicture;
import com.dj.dianjiao.domain.SendPictureItemClickEvent;
import com.dj.dianjiao.domain.SendPictureViewHolder;
import com.dj.dianjiao.domain.ServerAddress;
import com.dj.dianjiao.domain.ServerAddressEvent;
import com.dj.dianjiao.domain.ServerAddressViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class ServerAddressGVAdapter extends BaseAdapter {
    private static final String TAG = "ServerAddressGVAdapter";
    private Context mContext;
    private List<ServerAddress> serverAddressList;

    public ServerAddressGVAdapter(Context context){
        mContext = context;
        serverAddressList = new ArrayList<ServerAddress>();
    }

    @Override
    public int getCount() {
        return serverAddressList.size();
    }

    @Override
    public Object getItem(int position) {
        return serverAddressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ServerAddress serverAddress = serverAddressList.get(position);
        TextView nameTV;
        EditText addressET;
        CheckBox addressCB;
        ServerAddressViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ss_server_address_gv_item, null, false);
            addressET = (EditText) convertView.findViewById(R.id.ss_server_address_gv_item_et);
            nameTV = (TextView)convertView.findViewById(R.id.ss_server_address_gv_item_tv);
            addressCB =(CheckBox)convertView.findViewById(R.id.ss_server_address_selete_cb);

            viewHolder = new ServerAddressViewHolder();
            viewHolder.addressET = addressET;
            viewHolder.nameTV = nameTV;
            viewHolder.addressCB = addressCB;

        } else { // Otherwise re-use the converted view
            viewHolder = (ServerAddressViewHolder)convertView.getTag();
            addressET = viewHolder.addressET;
            nameTV = viewHolder.nameTV;
            addressCB = viewHolder.addressCB;
        }

        addressET.setText(serverAddress.getAddress());
        nameTV.setText(serverAddress.getName());
        addressCB.setChecked(false);

        convertView.setTag(viewHolder);
        addressCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new ServerAddressEvent(isChecked,position+""));

            }
        });
        return convertView;
    }

    public void setServerAddressList(List<ServerAddress> mServerAddressList){
        if(serverAddressList.size()>0){
            serverAddressList.clear();
        }
        if(mServerAddressList!=null&&mServerAddressList.size()>0){
            serverAddressList.addAll(mServerAddressList);
        }
        notifyDataSetChanged();
    }
}
