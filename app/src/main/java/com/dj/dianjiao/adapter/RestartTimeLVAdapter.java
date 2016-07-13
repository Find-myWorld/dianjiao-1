package com.dj.dianjiao.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.RestartTime;
import com.dj.dianjiao.domain.RestartTimeViewHolder;
import com.dj.dianjiao.domain.SendMedia;
import com.dj.dianjiao.domain.SendMediaItemClickEvent;
import com.dj.dianjiao.domain.SendMediaViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class RestartTimeLVAdapter extends BaseAdapter {
    private static final String TAG = "RestartTimeLVAdapter";
    private Context mContext;
    private List<RestartTime> restartTimeList;
    private TimePickerDialog timePickerDialog;
    private Calendar cale;
    private String time;

    public RestartTimeLVAdapter(Context context){
        mContext = context;
        restartTimeList = new ArrayList<RestartTime>();
    }

    @Override
    public int getCount() {
        return restartTimeList.size();
    }

    @Override
    public Object getItem(int position) {
        return restartTimeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RestartTime restartTime = restartTimeList.get(position);
        ImageView timeIV;
        TextView nameTV;
        final TextView timeTV;
        RestartTimeViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ss_restart_time_lv_item, null, false);
            timeIV = (ImageView)convertView.findViewById(R.id.ss_restart_time_lv_item_iv);
            nameTV = (TextView)convertView.findViewById(R.id.ss_restart_time_lv_item_name_tv);
            timeTV = (TextView) convertView.findViewById(R.id.ss_restart_time_lv_item_time_tv);

            viewHolder = new RestartTimeViewHolder();
            viewHolder.timeIV = timeIV;
            viewHolder.nameTV = nameTV;
            viewHolder.timeTV = timeTV;

        } else { // Otherwise re-use the converted view
            viewHolder = (RestartTimeViewHolder)convertView.getTag();
            timeIV = viewHolder.timeIV;
            nameTV = viewHolder.nameTV;
            timeTV = viewHolder.timeTV;
        }

        nameTV.setText(restartTime.getName());
        timeTV.setText(restartTime.getTime());

        cale = Calendar.getInstance();
        timeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String text = "";
                        if(hourOfDay<10){
                            text += "0"+hourOfDay+":";
                        }else {
                            text += hourOfDay+":";
                        }

                        if(minute<10){
                            text += "0"+minute+":00";
                        }else {
                            text += minute+":00";
                        }
                        timeTV.setText(text);
                        restartTime.setTime(text);
                    }
                }, cale.get(Calendar.HOUR_OF_DAY), cale.get(Calendar.MINUTE), true);
            }
        });

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setRestartTimeList(List<RestartTime> mRestartTimeList){
        if(restartTimeList.size()>0){
            restartTimeList.clear();
        }
        if(mRestartTimeList!=null&&mRestartTimeList.size()>0){
            restartTimeList.addAll(mRestartTimeList);
        }
        notifyDataSetChanged();
    }
}
