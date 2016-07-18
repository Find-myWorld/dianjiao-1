package com.dj.dianjiao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiItemClickEvent;
import com.dj.dianjiao.domain.JianshiViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class JianshiGVAdapter extends BaseAdapter {
    private static final String TAG = "JianshiGVAdapter";
    private Context mContext;
    private List<Jianshi> jianshiList;

    public JianshiGVAdapter(Context context){
        mContext = context;
        jianshiList = new ArrayList<Jianshi>();
    }

    @Override
    public int getCount() {
        return jianshiList.size();
    }

    @Override
    public Object getItem(int position) {
        return jianshiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Jianshi jianshi = jianshiList.get(position);
        ImageView typeIV;
        TextView nameTV;
        CheckBox isCheckCB;
        JianshiViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.jianshi_gv_item, null, false);
            typeIV = (ImageView)convertView.findViewById(R.id.jianshi_gv_item_iv);
            nameTV = (TextView)convertView.findViewById(R.id.jianshi_gv_item_tv);
            isCheckCB = (CheckBox) convertView.findViewById(R.id.jianshi_gv_item_cb);

            viewHolder = new JianshiViewHolder();
            viewHolder.typeIV = typeIV;
            viewHolder.nameTV = nameTV;
            viewHolder.isCheckCB = isCheckCB;

        } else { // Otherwise re-use the converted view
            viewHolder = (JianshiViewHolder)convertView.getTag();
            typeIV = viewHolder.typeIV;
            nameTV = viewHolder.nameTV;
            isCheckCB = viewHolder.isCheckCB;

        }

        switch (jianshi.getType()){
            case Jianshi.TYPE_01:
                typeIV.setImageResource(R.mipmap.jianshi_item_type_01);
                break;
            case Jianshi.TYPE_02:
                typeIV.setImageResource(R.mipmap.jianshi_item_type_02);
                break;
            case Jianshi.TYPE_03:
                typeIV.setImageResource(R.mipmap.jianshi_item_type_03);
                break;
        }
        nameTV.setText(jianshi.getName());
        isCheckCB.setChecked(jianshi.getIsChecked());

        if(position==0){
            Log.d(TAG,""+jianshi.getJianquName()+jianshi.getIsChecked());
        }

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
                    EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), ((CheckBox) v).isChecked()));
                    jianshi.setIsChecked(((CheckBox) v).isChecked());
                }
            }
        });

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setJianshiList(List<Jianshi> mJianshiList){
        if(jianshiList.size()>0){
            jianshiList.clear();
        }
        if(mJianshiList!=null&&mJianshiList.size()>0){
            jianshiList.addAll(mJianshiList);
        }
        notifyDataSetChanged();
    }

    public void selectAll(boolean isSelectAll){
        if(jianshiList.size()>0){
            for (Jianshi jianshi:jianshiList) {
                if(jianshi.getIsChecked()!=isSelectAll){
                    EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), isSelectAll));
                }
                jianshi.setIsChecked(isSelectAll);//更改状态
            }
        }
        notifyDataSetChanged();
    }
}
