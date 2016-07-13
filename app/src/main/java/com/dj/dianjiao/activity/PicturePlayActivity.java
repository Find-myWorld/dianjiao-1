package com.dj.dianjiao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.base.BaseActivity;
import com.dj.dianjiao.adapter.JianshiPagerAdapter;
import com.dj.dianjiao.adapter.SendPictureGVAdapter;
import com.dj.dianjiao.domain.BaseEvent;
import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiItemAllEvent;
import com.dj.dianjiao.domain.JianshiItemClickEvent;
import com.dj.dianjiao.domain.SendPicture;
import com.dj.dianjiao.domain.SendPictureItemClickEvent;
import com.dj.dianjiao.fragment.JianshiPagerFragment;
import com.dj.dianjiao.manger.JianquManger;
import com.dj.dianjiao.manger.JianshiManger;
import com.dj.dianjiao.manger.SendPictureManger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PicturePlayActivity extends BaseActivity implements JianquManger.CallBack,JianshiManger.CallBack,SendPictureManger.CallBack{
    private static final String TAG = "PicturePlayActivity";
    private RadioGroup jianquRG;
    private Context mContext;
    private JianquManger jianquManger;
    private JianshiManger jianshiManger;
    private List<Jianshi> mJianshiList;

    private ViewPager jianshiViewPager;
    private JianshiPagerAdapter jianshiPagerAdapter;
    private List<JianshiPagerFragment> fragmentList;

    private LinearLayout pointLL;
    private List<CheckBox> pointCheckBoxList;

    private List<RadioButton> jianquRadioButtonList;

    private TextView selectNumTV;
    private int selectedNum = 0;

    private Button selectAllBTN;
    private Button delSelectALLBTN;

    private int pager_size = 18;

    private GridView sendPictureGV;
    private SendPictureManger sendPictureManger;
    private SendPictureGVAdapter sendPictureGVAdapter;
    private List<SendPicture> sendPictureList;
    private TextView sendPictureCheckedTV;
    private int sendPictureCheckedNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_play);

        initBaseViews();
        mContext = getApplicationContext();
        EventBus.getDefault().register(this);
        jianquManger = new JianquManger(mContext);
        jianquManger.setCallback(this);
        jianshiManger = new JianshiManger(mContext);
        jianshiManger.setCallback(this);
        mJianshiList = new ArrayList<Jianshi>();
        fragmentList = new ArrayList<JianshiPagerFragment>();
        pointCheckBoxList = new ArrayList<CheckBox>();
        jianquRadioButtonList = new ArrayList<RadioButton>();
        sendPictureGVAdapter = new SendPictureGVAdapter(mContext);
        sendPictureManger = new SendPictureManger(mContext);
        sendPictureManger.setCallback(this);
        sendPictureList = new ArrayList<SendPicture>();

        initViews();

        sendPictureManger.loadSendPictureListList();
        jianquManger.loadJianquList();
    }

    private void initViews() {
        sendPictureGV = (GridView) findViewById(R.id.pp_send_picture_gv);
        sendPictureCheckedTV = (TextView) findViewById(R.id.pp_checked_picture_num_tv);
        jianquRG = (RadioGroup) findViewById(R.id.jianqu_rg);
        jianshiViewPager = (ViewPager) findViewById(R.id.jianshi_vp);
        pointLL = (LinearLayout) findViewById(R.id.point_ll);
        selectNumTV = (TextView) findViewById(R.id.selected_jianshi_num_tv);
        selectAllBTN = (Button) findViewById(R.id.select_all_btn);
        delSelectALLBTN = (Button) findViewById(R.id.del_select_btn);

        selectAllBTN.setOnClickListener(this);
        delSelectALLBTN.setOnClickListener(this);

        jianquRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton temp = getRadioButtonById(checkedId);
                jianshiManger.loadJianshiList(temp.getText().toString().trim());
            }
        });

        jianshiPagerAdapter = new JianshiPagerAdapter(getSupportFragmentManager(),mContext);
        jianshiViewPager.setAdapter(jianshiPagerAdapter);
        jianshiViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changePointSelected(position + 1);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        sendPictureGV.setAdapter(sendPictureGVAdapter);

    }

    private RadioButton getRadioButtonById(int id) {
        return (RadioButton) findViewById(id);
    }

    @Override
    public void onGetSendPictureListCompleted(List<SendPicture> mSendPictureList) {
        if(sendPictureList!=null){
            sendPictureList.clear();
        }

        if(mSendPictureList!=null&&mSendPictureList.size()>0){
            sendPictureList.addAll(mSendPictureList);
        }
        sendPictureGVAdapter.setSendPictureList(sendPictureList);
        sendPictureCheckedNum = 0;
        sendPictureCheckedTV.setText(""+sendPictureCheckedNum);
    }

    @Override
    public void onGetJianquListCompleted(List<Jianqu> jianquList) {
        jianquRadioButtonList.clear();
        for (Jianqu jianqu : jianquList) {
            RadioButton rb = new RadioButton(mContext);
            rb.setBackgroundResource(R.drawable.radio_jianqu);
            rb.setButtonDrawable(null);
            rb.setText(jianqu.getName());
            rb.setTextSize(16);

            rb.setGravity(Gravity.CENTER);
            //rb.setChecked(jianqu.getIschecked());
            rb.setTextColor(getResources().getColorStateList(R.color.selector_blue_white));
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(125,RadioGroup.LayoutParams.FILL_PARENT);
            lp.setMargins(10, 0, 10, 0);
            jianquRG.addView(rb, lp);
            jianquRadioButtonList.add(rb);
        }

        jianquRadioButtonList.get(0).setChecked(true);
        jianshiManger.loadJianshiList(jianquRadioButtonList.get(0).getText().toString().trim());
    }

    @Override
    public void onGetJianshiListCompleted(List<Jianshi> jianshiList) {
        Log.d(TAG, "" + jianshiList.get(0).getJianquName() + jianshiList.get(0).getIsChecked());
        if (mJianshiList.size()>0){
            mJianshiList.clear();
        }
        mJianshiList.addAll(jianshiList);
        fragmentList.clear();
        List<Jianshi> temp = new ArrayList<Jianshi>();
        if(jianshiList!=null && jianshiList.size()>0){
            for (int i=1;i<=jianshiList.size();i++){
                temp.add(jianshiList.get(i-1));
                if(i%pager_size==0){
                    JianshiPagerFragment jsFragment = JianshiPagerFragment.newInstance(temp);
                    fragmentList.add(jsFragment);
                    temp.clear();
                }else if(i==jianshiList.size()){
                    JianshiPagerFragment jsFragment = JianshiPagerFragment.newInstance(temp);
                    fragmentList.add(jsFragment);
                    temp.clear();
                }
            }
        }
        jianshiViewPager.removeAllViews();
        jianshiPagerAdapter.setFragmentList(fragmentList);
        jianshiViewPager.setAdapter(jianshiPagerAdapter);
        selectedNum = 0;
        selectNumTV.setText("" + selectedNum);

        initPoint(fragmentList.size());

    }

    private void initPoint(int size) {
        pointLL.removeAllViews();
        pointCheckBoxList.clear();
        for (int i=1;i<=size;i++) {
            CheckBox cb = new CheckBox(mContext);
            cb.setBackgroundResource(R.drawable.point_checkbox);
            cb.setButtonDrawable(null);
            cb.setChecked(i == 1 ? true : false);
            cb.setClickable(false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(12,12);
            lp.setMargins(10,0,10,0);
            pointLL.addView(cb, lp);
            pointCheckBoxList.add(cb);
        }
    }

    private void changePointSelected(int pos) {
        for (CheckBox cb:pointCheckBoxList) {
            cb.setChecked(false);
        }
        if (pointCheckBoxList.size()>0){
            pointCheckBoxList.get(pos-1).setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.select_all_btn:
                EventBus.getDefault().post(new JianshiItemAllEvent(true));
                break;
            case R.id.del_select_btn:
                EventBus.getDefault().post(new JianshiItemAllEvent(false));
                break;
        }
    }

    @Subscribe
    public void onEvent(BaseEvent event){
        if (event instanceof JianshiItemClickEvent){
            if (((JianshiItemClickEvent) event).isCheck()){
                selectedNum++;
            }else {
                selectedNum--;
            }
            selectNumTV.setText(""+selectedNum);
        }else if (event instanceof SendPictureItemClickEvent){
            if (((SendPictureItemClickEvent) event).isChecked()){
                sendPictureCheckedNum++;
            }else {
                sendPictureCheckedNum--;
            }
            sendPictureCheckedTV.setText(""+sendPictureCheckedNum);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
