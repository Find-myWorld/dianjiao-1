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
import com.dj.dianjiao.domain.PlanFileViewHolder;
import com.dj.dianjiao.domain.SendMedia;
import com.dj.dianjiao.domain.SendMediaItemClickEvent;
import com.dj.dianjiao.domain.SendMediaViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.1
 */
public class FileLVAdapter extends BaseAdapter {
    private static final String TAG = "FileLVAdapter";
    private Context mContext;
    private List<String> fileList;
    private int i;

    public FileLVAdapter(Context context){
        mContext = context;
        fileList = new ArrayList<String>();

    }

    @Override
    public int getCount() {
        return fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String file = fileList.get(position);
        ImageView pictureIV;
        TextView nameTV;
        PlanFileViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.plan_file_lv_item, null, false);
            pictureIV = (ImageView)convertView.findViewById(R.id.plan_file_lv_item_iv);
            nameTV = (TextView)convertView.findViewById(R.id.plan_file_lv_item_tv);

            viewHolder = new PlanFileViewHolder();
            viewHolder.pictureIV = pictureIV;
            viewHolder.nameTV = nameTV;

        } else { // Otherwise re-use the converted view
            viewHolder = (PlanFileViewHolder)convertView.getTag();
            pictureIV = viewHolder.pictureIV;
            nameTV = viewHolder.nameTV;
        }

        nameTV.setText(file);

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setFileList(List<String> mFileList){
        if(fileList.size()>0){
            fileList.clear();
        }
        if(mFileList!=null&&mFileList.size()>0){
            fileList.addAll(mFileList);
        }
        notifyDataSetChanged();
    }

    public List<String> getFileList(){
        List<String> mFileList = new ArrayList<String>();
        if(fileList.size()>0){
            mFileList.addAll(fileList);
        }
        return mFileList;
    }
}
