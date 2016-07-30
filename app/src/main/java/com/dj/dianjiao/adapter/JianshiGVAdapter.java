package com.dj.dianjiao.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiItemClickEvent;
import com.dj.dianjiao.domain.JianshiViewHolder;
import com.dj.dianjiao.utils.Constant;
import com.dj.dianjiao.utils.SPUtils;
import com.dj.dianjiao.widget.MyTextView;

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

    public JianshiGVAdapter(Context context) {
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

    //private int positon;//记录当前条目的位置
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Jianshi jianshi = jianshiList.get(position);
        final ImageView typeIV;
        final MyTextView nameTV;
        final CheckBox isCheckCB;
        JianshiViewHolder viewHolder;

        if (convertView == null) { // if it's not recycled, instantiate and initialize
            convertView = LayoutInflater.from(mContext).inflate(R.layout.jianshi_gv_item, null, false);
            typeIV = (ImageView) convertView.findViewById(R.id.jianshi_gv_item_iv);
            nameTV = (MyTextView) convertView.findViewById(R.id.jianshi_gv_item_tv);
            isCheckCB = (CheckBox) convertView.findViewById(R.id.jianshi_gv_item_cb);

            viewHolder = new JianshiViewHolder();
            viewHolder.typeIV = typeIV;
            viewHolder.nameTV = nameTV;
            viewHolder.isCheckCB = isCheckCB;

        } else { // Otherwise re-use the converted view
            viewHolder = (JianshiViewHolder) convertView.getTag();
            typeIV = viewHolder.typeIV;
            nameTV = viewHolder.nameTV;
            isCheckCB = viewHolder.isCheckCB;
        }

        isCheckCB.setChecked(jianshi.getIsChecked());
        switch (jianshi.getChannel()) {
            case Constant.CHANNEL_DVD:
                nameTV.setText("正在播放DVD光盘");
                break;
            case Constant.CHANNEL_TV:
                nameTV.setText("正在播放电视节目源");
                break;
            case Constant.CHANNEL_CLASS:
                nameTV.setText("正在播放大课教育");
                break;

        }
        if (jianshi.getState() == Jianshi.STATE_FREE) {
            switch (jianshi.getType()) {
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
            SPUtils.put(mContext, "position:" + position, jianshi.getName());
                            /*关闭走马灯*/
            nameTV.setSingleLine(false);
            nameTV.setFocusable(false);
            nameTV.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            nameTV.setTextColor(Color.WHITE);

        } else if (jianshi.getState() == Jianshi.STATE_PLAY) {//如果状态为播放状态时
            System.err.println("Jianshi.STATE_PLAY------");
           // EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), false, position));
            typeIV.setImageResource(R.mipmap.stop);
            isCheckCB.setChecked(false);
            /*开启走马灯*/
            nameTV.setSingleLine(true);
            nameTV.setFocusable(true);
            nameTV.setEllipsize(TextUtils.TruncateAt.MARQUEE);  //走马灯样式
            nameTV.setMarqueeRepeatLimit(-1);
            nameTV.setTextColor(Color.GREEN);
        }

       /* if (position == 0) {
            Log.d(TAG, "" + jianshi.getJianquName() + jianshi.getIsChecked());
        }*/

        /*isCheckCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), isChecked));
            }
        });*/


        isCheckCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    jianshi.setIsChecked(((CheckBox) v).isChecked());
                    if(jianshi.getChannel()==Constant.CHANNEL_CLASS||jianshi.getChannel()==Constant.CHANNEL_TV||jianshi.getChannel()==Constant.CHANNEL_DVD){
                        if (jianshi.getState() == Jianshi.STATE_FREE) {
                            jianshi.setState(Jianshi.STATE_PLAY);
                        } else if (jianshi.getState() == Jianshi.STATE_PLAY) {
                            jianshi.setState(Jianshi.STATE_FREE);//将状态置为空闲
                        }
                        jianshi.setIsChecked(false);
                    }else {
                        /*弹对话框*/
                        Toast.makeText(mContext,"我是对话框",Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }
                EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), ((CheckBox) v).isChecked(), position));

            }

        });
        /*长按，出现popWindows*/
        isCheckCB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.pop_play_state, null);
                Button pauseBTN = (Button) view.findViewById(R.id.pop_pause_play);//暂停功能
                pauseBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(mContext, "pop点击", Toast.LENGTH_SHORT).show();
                    }
                });

                PopupWindow popupWindow = new PopupWindow(view, 150, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setTouchable(true);
                popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.pop_bg));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.animator.pop_animal);
           /*当监室状态为播放状态时，可弹出pop*/
                if (jianshi.getState() == Jianshi.STATE_PLAY) {
                    popupWindow.showAsDropDown(v);
                }

                return true;
            }
        });

        convertView.setTag(viewHolder);

        return convertView;
    }

    public void setJianshiList(List<Jianshi> mJianshiList) {
        if (jianshiList.size() > 0) {
            jianshiList.clear();
        }
        if (mJianshiList != null && mJianshiList.size() > 0) {
            jianshiList.addAll(mJianshiList);
        }
        notifyDataSetChanged();
    }

    public void selectAll(boolean isSelectAll) {
        if (jianshiList.size() > 0) {
            for (Jianshi jianshi : jianshiList) {
                if (jianshi.getIsChecked() != isSelectAll) {
                    EventBus.getDefault().post(new JianshiItemClickEvent(jianshi.getName(), isSelectAll, jianshiList.indexOf(jianshi)));
                }
                jianshi.setIsChecked(isSelectAll);//更改状态
            }
        }
        notifyDataSetChanged();
    }
}
