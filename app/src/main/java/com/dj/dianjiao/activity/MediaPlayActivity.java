package com.dj.dianjiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.base.BaseActivity;
import com.dj.dianjiao.adapter.JianshiPagerAdapter;
import com.dj.dianjiao.adapter.SendMediaLVAdapter;
import com.dj.dianjiao.domain.BaseEvent;
import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiAndJianqu;
import com.dj.dianjiao.domain.JianshiItemAllEvent;
import com.dj.dianjiao.domain.JianshiItemClickEvent;
import com.dj.dianjiao.domain.SendMedia;
import com.dj.dianjiao.domain.SendMediaItemClickEvent;
import com.dj.dianjiao.fragment.JianshiPagerFragment;
import com.dj.dianjiao.manger.JianquManger;
import com.dj.dianjiao.manger.JianshiManger;
import com.dj.dianjiao.manger.SendMediaManger;
import com.dj.dianjiao.service.MediaSendService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MediaPlayActivity extends BaseActivity implements JianquManger.CallBack,JianshiManger.CallBack,SendMediaManger.CallBack{
    private static final String TAG = "MediaPlayActivity";
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
    private Button confirmSendBTN;

    private int pager_size = 18;

    private ListView sendMediaLV;
    private SendMediaManger sendMediaManger;
    private SendMediaLVAdapter sendMediaLVAdapter;
    private List<SendMedia> sendMediaList;
    private TextView sendMediaCheckedTV;
    private int sendMediaCheckedNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);

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
        sendMediaLVAdapter = new SendMediaLVAdapter(mContext);
        sendMediaManger = new SendMediaManger(mContext);
        sendMediaManger.setCallback(this);
        sendMediaList = new ArrayList<SendMedia>();

        initViews();
        sendMediaManger.loadSendMediaList();
        jianquManger.loadJianquList();
    }

    private int jianquID;

    private void initViews() {
        sendMediaLV = (ListView) findViewById(R.id.mp_send_media_lv);
        sendMediaCheckedTV = (TextView) findViewById(R.id.mp_checked_media_num_tv);
        jianquRG = (RadioGroup) findViewById(R.id.jianqu_rg);
        jianshiViewPager = (ViewPager) findViewById(R.id.jianshi_vp);
        pointLL = (LinearLayout) findViewById(R.id.point_ll);
        selectNumTV = (TextView) findViewById(R.id.selected_jianshi_num_tv);
        selectAllBTN = (Button) findViewById(R.id.select_all_btn);
        delSelectALLBTN = (Button) findViewById(R.id.del_select_btn);
        confirmSendBTN = (Button) findViewById(R.id.btn_confirm_send);

        selectAllBTN.setOnClickListener(this);
        delSelectALLBTN.setOnClickListener(this);
        confirmSendBTN.setOnClickListener(this);


        jianquRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//监区选定操作
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                jianquID = checkedId;
                /*清除所有集合里的数据*/
                cleanAllList();
                RadioButton temp = getRadioButtonById(checkedId);
                jianshiManger.loadJianshiList(temp.getText().toString().trim());//初始化操作,获取gv数据
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

        sendMediaLV.setAdapter(sendMediaLVAdapter);

    }

    private void cleanAllList() {
        jianqu1List.clear();
        jianqu2List.clear();
        jianqu3List.clear();
        jianqu4List.clear();
    }

    private RadioButton getRadioButtonById(int id) {
        return (RadioButton) findViewById(id);
    }

    @Override
    public void onGetSendMediaListCompleted(List<SendMedia> mSendMediaList) {
        if(sendMediaList!=null){
            sendMediaList.clear();
        }

        if(mSendMediaList!=null&&mSendMediaList.size()>0){
            sendMediaList.addAll(mSendMediaList);
        }
        sendMediaLVAdapter.setSendMediaList(sendMediaList);
        sendMediaCheckedNum = 0;
        sendMediaCheckedTV.setText(""+sendMediaCheckedNum);
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
        /*根据获取的监室数来分配相对应的Page数*/
        if(jianshiList!=null && jianshiList. size()>0){
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
        jianshiPagerAdapter.setFragmentList(fragmentList);//pages集合放入adapter
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
            case R.id.btn_confirm_send:
                sendMediaResources();
                break;

        }
    }

    private void sendMediaResources(){//客户端开启service 发起socket请求
        Intent intent = new Intent(getApplicationContext(), MediaSendService.class);
        JianshiAndJianqu andJianqu = new JianshiAndJianqu();
        andJianqu.setMovieFiles(movieFiles);
        andJianqu.setJianqu1List(jianqu1List);
        andJianqu.setJianqu2List(jianqu2List);
        andJianqu.setJianqu3List(jianqu3List);
        andJianqu.setJianqu4List(jianqu4List);
        intent.putExtra("jianqujianshi",andJianqu);
        startService(intent);
        Log.d("tag","MediaSendService开启？");
    }

    List<String> movieFiles = new ArrayList<>();
    List<String> jianqu1List = new ArrayList<>();
    List<String> jianqu2List = new ArrayList<>();
    List<String> jianqu3List = new ArrayList<>();
    List<String> jianqu4List = new ArrayList<>();

    @Subscribe
    public void onEvent(BaseEvent event){
        if (event instanceof JianshiItemClickEvent){
            if (((JianshiItemClickEvent) event).isCheck()){
                selectedNum++;
            }else {
                selectedNum--;
            }
            selectNumTV.setText(""+selectedNum);
            /*拿到各监区选中的条目数*/
            setJianshiJianquDate(jianquID,((JianshiItemClickEvent) event).getName());
        }else if (event instanceof SendMediaItemClickEvent){
            if (((SendMediaItemClickEvent) event).isChecked()){
                sendMediaCheckedNum++;
            }else {
                sendMediaCheckedNum--;
            }
            sendMediaCheckedTV.setText(""+sendMediaCheckedNum);
            movieFiles.add(((SendMediaItemClickEvent) event).getMovieName());//添加发送数据 电影名
        }
    }

    private void setJianshiJianquDate(int jianquID, String name) {
        switch (jianquID) {
            case 1:
                jianqu1List.add(name);
                break;
            case 2:
                jianqu2List.add(name);
                break;
            case 3:
                jianqu3List.add(name);
                break;
            case 4:
                jianqu4List.add(name);
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
