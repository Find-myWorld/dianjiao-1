package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class PlanManger {
    private static final String TAG = "PlanManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<Plan> mPlanList;

    private CallBack mCallBack;

    public PlanManger(Context context) {

        mContext = context;
        mPlanList = new ArrayList<Plan>();
    }

    public interface CallBack {
        void onGetPlanListCompleted(List<Plan> planList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadPlanList(){

        if(mPlanList != null){
            mPlanList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 3; i++) {
                Plan plan = new Plan();
                plan.setAction(Plan.ACTION[0]);
                List<String> temp = new ArrayList<>();
                temp.add("文件01.mp4");
                temp.add("文件02.mp4");
                temp.add("文件03.mp4");
                temp.add("文件04.mp4");
                plan.setFileList(temp);
                plan.setOverTime("无");
                plan.setRemark("remark");
                plan.setRound(new boolean[]{true, true, true, true, true, false, false});
                plan.setStartTime("06:30:00");
                plan.setTaskName("计划任务-定时开机0"+i);
                mPlanList.add(plan);
            }
            setPlanList();
        } else {
            //online

            setPlanList();
        }
    }

    private void setPlanList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetPlanListCompleted(mPlanList);
        }
    }
}
