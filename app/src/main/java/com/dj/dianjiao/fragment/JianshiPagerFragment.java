package com.dj.dianjiao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.adapter.JianshiGVAdapter;
import com.dj.dianjiao.domain.BaseEvent;
import com.dj.dianjiao.domain.Jianshi;
import com.dj.dianjiao.domain.JianshiItemAllEvent;
import com.dj.dianjiao.domain.PositionState;
import com.dj.dianjiao.domain.SerializableDomain;
import com.dj.dianjiao.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class JianshiPagerFragment extends Fragment{
    public static final String TAG = "JianshiPagerFragment";
    public static final String EXPERT_LIST = "JianshiList";

    private GridView gv;
    private Context mContext;
    private List<Jianshi> jianshiList;

    private JianshiGVAdapter jianshiGVAdapter;

    public static JianshiPagerFragment newInstance(List<Jianshi> mJianshiList) {
        JianshiPagerFragment f = new JianshiPagerFragment();
        Bundle bdl = new Bundle();
        bdl.putSerializable(EXPERT_LIST,new SerializableDomain(mJianshiList));
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater,container,savedInstanceState);
        jianshiList = new ArrayList<Jianshi>();

        SerializableDomain listS = (SerializableDomain) getArguments().getSerializable(EXPERT_LIST);

        if(listS!=null&&listS.getJianshiList()!=null&&listS.getJianshiList().size()>0){
            jianshiList.addAll(listS.getJianshiList());
        }

        mContext = getActivity().getApplicationContext();

        View view = inflater.inflate(R.layout.jianshi_viewpager, container, false);
        gv = (GridView) view.findViewById(R.id.jianshi_viewpager_gv);
        jianshiGVAdapter = new JianshiGVAdapter(mContext);
        gv.setAdapter(jianshiGVAdapter);
        jianshiGVAdapter.setJianshiList(jianshiList);
        return view;
    }

    @Subscribe
    public void onEvent(BaseEvent event){
        if (event instanceof JianshiItemAllEvent){
            jianshiGVAdapter.selectAll(((JianshiItemAllEvent) event).isSelectAll());
        }else if(event instanceof PositionState){
            /*  List<String> positions = ((PositionState) event).getPosition();
            for (String position:positions) {
                System.out.println("现在选择的监室为:  "+position);
                *//*设置监室的状态*//*
                jianshiList.get(Integer.parseInt(position)).setState(Jianshi.STATE_PLAY);
                SPUtils.put(mContext,"position:"+position,jianshiList.get(Integer.parseInt(position)).getName());
                jianshiList.get(Integer.parseInt(position)).setName(((PositionState) event).getMoviemsg());
            }
            jianshiGVAdapter.setJianshiList(jianshiList);*/
            for (Jianshi jianshi:jianshiList){
                jianshi.setChannel(((PositionState) event).getChannel());
                System.err.println("fragment~~~`"+((PositionState) event).getChannel());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            EventBus.getDefault().register(this);
        }else{
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}
