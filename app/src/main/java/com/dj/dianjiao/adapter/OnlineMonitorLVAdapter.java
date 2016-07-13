package com.dj.dianjiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.OnlineMonitor;
import com.dj.dianjiao.domain.OnlineMonitorViewHolder;
import com.dj.dianjiao.domain.PlanFileViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class OnlineMonitorLVAdapter extends BaseAdapter {
    private static final String TAG = "OnlineMonitorLVAdapter";
    private Context mContext;
    private List<OnlineMonitor> onlineMonitorList;

    public OnlineMonitorLVAdapter(Context context){
        mContext = context;
        onlineMonitorList = new ArrayList<OnlineMonitor>();
    }

    @Override
    public int getCount() {
        return onlineMonitorList.size();
    }

    @Override
    public Object getItem(int position) {
        return onlineMonitorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnlineMonitor onlineMonitor = onlineMonitorList.get(position);
        TextView idTV;
        TextView productNameTV;
        TextView stateTV;
        OnlineMonitorViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ss_online_monitor_lv_item, null, false);
            idTV = (TextView) convertView.findViewById(R.id.ss_online_monitor_lv_item_id_tv);
            productNameTV = (TextView)convertView.findViewById(R.id.ss_online_monitor_lv_item_product_name_tv);
            stateTV = (TextView)convertView.findViewById(R.id.ss_online_monitor_lv_item_state_tv);

            viewHolder = new OnlineMonitorViewHolder();
            viewHolder.idTV = idTV;
            viewHolder.productNameTV = productNameTV;
            viewHolder.stateTV = stateTV;

        } else { // Otherwise re-use the converted view
            viewHolder = (OnlineMonitorViewHolder)convertView.getTag();
            idTV = viewHolder.idTV;
            productNameTV = viewHolder.productNameTV;
            stateTV = viewHolder.stateTV;
        }

        idTV.setText(onlineMonitor.getId());
        productNameTV.setText(onlineMonitor.getProductName());
        stateTV.setText(onlineMonitor.getState());

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setOnlineMonitorList(List<OnlineMonitor> mOnlineMonitorList){
        if(onlineMonitorList.size()>0){
            onlineMonitorList.clear();
        }
        if(mOnlineMonitorList!=null&&mOnlineMonitorList.size()>0){
            onlineMonitorList.addAll(mOnlineMonitorList);
        }
        notifyDataSetChanged();
    }
}
