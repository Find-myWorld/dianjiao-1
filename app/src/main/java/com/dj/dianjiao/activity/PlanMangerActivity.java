package com.dj.dianjiao.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dj.dianjiao.R;
import com.dj.dianjiao.base.BaseActivity;
import com.dj.dianjiao.adapter.FileLVAdapter;
import com.dj.dianjiao.domain.DefiniteTimeEvent;
import com.dj.dianjiao.domain.Plan;
import com.dj.dianjiao.manger.PlanManger;
import com.dj.dianjiao.service.DefiniteService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanMangerActivity extends BaseActivity implements PlanManger.CallBack {
    private static final String TAG = "PlanMangerActivity";
    private EditText searchET;
    private ImageView searchIV;
    private RadioGroup planRG;
    private RelativeLayout addPlanRL;
    private RelativeLayout deletePlanRL;
    private EditText taskNameET;
    private TextView startTimeTV;
    private ImageView startTimeIV;
    private TextView overTimeTV;
    private ImageView overtimeIV;
    private CheckBox[] roundCB;
    private Spinner actionSP;
    private ListView fileLV;
    private ImageView addFileIV;
    private ImageView deleteFileIV;
    private EditText remarkET;


    private Context mContext;
    private List<Plan> planList;
    private PlanManger planManger;
    private FileLVAdapter fileLVAdapter;
    private List<RadioButton> radioButtonList;

    private Plan selectedPlan;
    private RadioButton selectedRB;
    private boolean isAdd = false;

    private TimePickerDialog startTimePickerDialog;
    private TimePickerDialog overTimePickerDialog;
    private Button commitModifyBTN;
    private Button cancelModifyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_manger);

        initBaseViews();

        mContext = getApplicationContext();
        planList = new ArrayList<Plan>();
        planManger = new PlanManger(mContext);
        planManger.setCallback(this);

        radioButtonList = new ArrayList<RadioButton>();
        fileLVAdapter = new FileLVAdapter(mContext);
        initViews();

        planManger.loadPlanList();//去加载计划任务的数据(local)

    }

    private void initViews() {
        searchET = (EditText) findViewById(R.id.pm_search_et);
        searchIV = (ImageView) findViewById(R.id.pm_search_iv);
        planRG = (RadioGroup) findViewById(R.id.pm_plan_rg);
        addPlanRL = (RelativeLayout) findViewById(R.id.pm_add_plan_rl);
        deletePlanRL = (RelativeLayout) findViewById(R.id.pm_delete_plan_rl);

        commitModifyBTN = (Button) findViewById(R.id.pm_commit_change_btn);
        cancelModifyBTN = (Button) findViewById(R.id.pm_cancel_change_btn);

        taskNameET = (EditText) findViewById(R.id.pm_task_name_et);
        startTimeTV = (TextView) findViewById(R.id.pm_start_time_tv);
        startTimeIV = (ImageView) findViewById(R.id.pm_start_time_iv);
        overTimeTV = (TextView) findViewById(R.id.pm_over_time_tv);
        overtimeIV = (ImageView) findViewById(R.id.pm_over_time_iv);
        roundCB = new CheckBox[7];
        roundCB[0] = (CheckBox) findViewById(R.id.pm_round_cb_one);
        roundCB[1] = (CheckBox) findViewById(R.id.pm_round_cb_two);
        roundCB[2] = (CheckBox) findViewById(R.id.pm_round_cb_three);
        roundCB[3] = (CheckBox) findViewById(R.id.pm_round_cb_four);
        roundCB[4] = (CheckBox) findViewById(R.id.pm_round_cb_five);
        roundCB[5] = (CheckBox) findViewById(R.id.pm_round_cb_six);
        roundCB[6] = (CheckBox) findViewById(R.id.pm_round_cb_seven);
        actionSP = (Spinner) findViewById(R.id.pm_action_sp);
        fileLV = (ListView) findViewById(R.id.pm_file_lv);
        addFileIV = (ImageView) findViewById(R.id.pm_file_add_iv);
        deleteFileIV = (ImageView) findViewById(R.id.pm_file_delete_iv);
        remarkET = (EditText) findViewById(R.id.pm_remark_et);//备注
      /*  addChangeBTN = (Button) findViewById(R.id.pm_add_change_btn);
        deleteChangeBTN = (Button) findViewById(R.id.pm_delete_change_btn);*/

        fileLV.setAdapter(fileLVAdapter);
        planRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRB = getRadioButtonById(checkedId);
                int poi = radioButtonList.indexOf(checkedRB);
                selectedRB = radioButtonList.get(poi);
                selectedPlan = planList.get(poi);
                initPlanValues(selectedPlan);
            }
        });

        deletePlanRL.setOnClickListener(this);
        addPlanRL.setOnClickListener(this);
        commitModifyBTN.setOnClickListener(this);
        cancelModifyBTN.setOnClickListener(this);

        addFileIV.setOnClickListener(this);
        deleteFileIV.setOnClickListener(this);
        startTimeIV.setOnClickListener(this);
        overtimeIV.setOnClickListener(this);
        searchIV.setOnClickListener(this);

        Calendar cale = Calendar.getInstance();
        startTimePickerDialog = new TimePickerDialog(PlanMangerActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                startTimeTV.setText(text);
            }
        }, cale.get(Calendar.HOUR_OF_DAY), cale.get(Calendar.MINUTE), true);
        startTimePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startTimeTV.setText("无");
            }
        });

        overTimePickerDialog = new TimePickerDialog(PlanMangerActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                overTimeTV.setText(text);
            }
        }, cale.get(Calendar.HOUR_OF_DAY), cale.get(Calendar.MINUTE), true);

        overTimePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                overTimeTV.setText("无");
            }
        });

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    for (RadioButton rb : radioButtonList) {
                        rb.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(this,R.layout.file_spinner, Plan.ACTION);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionSP.setAdapter(spAdapter);
    }

    private RadioButton getRadioButtonById(int checkedId) {
        return (RadioButton) findViewById(checkedId);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.pm_delete_plan_rl://删除计划
                if(selectedPlan!=null&&selectedRB!=null){
                    if (planList!=null&&planList.size()>0&&radioButtonList!=null&&radioButtonList.size()>0){
                        /*删除对应数据*/
                        planList.remove(selectedPlan);
                        radioButtonList.remove(selectedRB);
                        planRG.removeView(selectedRB);
                        /**/
                        if(planList.size()>0&&radioButtonList.size()>0){
                            selectedPlan = planList.get(0);
                            selectedRB = radioButtonList.get(0);
                            selectedRB.setChecked(true);
                            isAdd = false;
                            initPlanValues(selectedPlan);
                        }else{
                            selectedPlan = null;
                            selectedRB = null;
                            isAdd = true;
                            initPlanValues(Plan.getEmptyInstance());
                        }
                    }
                }
                break;
            case R.id.pm_add_plan_rl://增加计划
                isAdd = true;
                initPlanValues(Plan.getEmptyInstance());
                break;
            case R.id.pm_cancel_change_btn://取消修改
                if (isAdd){
                    if(selectedPlan!=null){
                        selectedRB.setChecked(true);
                        initPlanValues(selectedPlan);
                        isAdd = false;
                    }else{
                        initPlanValues(Plan.getEmptyInstance());
                    }
                }else {
                    if(selectedPlan!=null) {
                        selectedRB.setChecked(true);
                        initPlanValues(selectedPlan);
                        isAdd = false;
                    }else {
                        initPlanValues(Plan.getEmptyInstance());
                        isAdd = true;
                    }
                }
                break;

            case R.id.pm_commit_change_btn://提交修改
                String taskName = taskNameET.getText().toString().trim();
                if (taskName==null||"".equals(taskName)){
                    Toast.makeText(mContext,"任务名称不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                String startTime = startTimeTV.getText().toString().trim();
                String overTime = overTimeTV.getText().toString().trim();
                boolean[] round = {roundCB[0].isChecked(),roundCB[1].isChecked(),roundCB[2].isChecked(),roundCB[3].isChecked(),roundCB[4].isChecked(),roundCB[5].isChecked(),roundCB[6].isChecked()};
                String action = Plan.ACTION[actionSP.getSelectedItemPosition()];
                List<String> fileList = fileLVAdapter.getFileList();
                String remark = remarkET.getText().toString().trim();
                if(remark==null){remark="";}
                if(isAdd){
                    Plan plan = new Plan(taskName,startTime,overTime,round,action,fileList,remark);
                    planList.add(plan);
                    selectedRB = addRadioButtonByPlan(plan);
                    selectedPlan = plan;
                    Toast.makeText(mContext,"添加成功！",Toast.LENGTH_SHORT).show();
                    isAdd = false;
                    selectedRB.setChecked(true);
                }else {
                    if(selectedPlan!=null){
                        selectedPlan.setTaskName(taskName);
                        selectedPlan.setStartTime(startTime);
                        selectedPlan.setOverTime(overTime);
                        selectedPlan.setRound(round);
                        selectedPlan.setAction(action);
                        selectedPlan.getFileList().clear();
                        selectedPlan.getFileList().addAll(fileList);
                        selectedPlan.setRemark(remark);
                        selectedRB.setText(taskName);
                        Toast.makeText(mContext,"修改成功！",Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            case R.id.pm_file_delete_iv:
                break;
            case R.id.pm_file_add_iv:
                break;
            case R.id.pm_start_time_iv:
                if(!startTimePickerDialog.isShowing()){
                    startTimePickerDialog.show();
                }
                break;
            case R.id.pm_over_time_iv:
                if(!overTimePickerDialog.isShowing()){
                    overTimePickerDialog.show();
                }
                break;
            case R.id.pm_search_iv:
                String searchBody = searchET.getText().toString().toString();
                String rbBody = "";
                if(searchBody!=null&&!"".equals(searchBody)){
                    for (RadioButton rb:radioButtonList) {
                        rbBody = rb.getText().toString().trim();
                        if (rbBody.indexOf(searchBody)!=-1){
                            rb.setVisibility(View.VISIBLE);
                        }else{
                            rb.setVisibility(View.GONE);
                        }
                    }
                }
                break;

        }
    }

    @Override
    public void onGetPlanListCompleted(List<Plan> mPlanList) {
        /*每一次加载移除ui和data,重新加载*/
        planList.clear();
        if (mPlanList!=null&&mPlanList.size()>0){
            planList.addAll(mPlanList);
        }
        planRG.removeAllViews();
        radioButtonList.clear();
        /*增加左侧计划列表条目*/
        for (Plan plan:planList) {
            addRadioButtonByPlan(plan);

        }

        if (radioButtonList.size()>0){
            selectedPlan = planList.get(0);
            selectedRB = radioButtonList.get(0);
            selectedRB.setChecked(true);
            initPlanValues(selectedPlan);
        }

    }

    private RadioButton addRadioButtonByPlan(Plan plan) {
        RadioButton rb = new RadioButton(mContext);
        rb.setBackgroundResource(R.drawable.bluebg_checkbox);
        rb.setButtonDrawable(null);
        rb.setText(plan.getTaskName());
        rb.setTextSize(18);
        rb.setGravity(Gravity.CENTER_VERTICAL);
        rb.setTextColor(getResources().getColorStateList(R.color.white));
        rb.setPadding(20,0,0,0);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,50);
        planRG.addView(rb, lp);
        radioButtonList.add(rb);
        return rb;
    }

    private void initPlanValues(Plan plan) {
        taskNameET.setText(plan.getTaskName());
        startTimeTV.setText(plan.getStartTime());
        overTimeTV.setText(plan.getOverTime());
        for (int i=0;i<7;i++){
            roundCB[i].setChecked(plan.getRound()[i]);
        }
        for (int i=0;i<Plan.ACTION.length;i++){
            if (Plan.ACTION[i].equals(plan.getAction())){
                actionSP.setSelection(i);
                break;
            }
        }
        fileLVAdapter.setFileList(plan.getFileList());
        remarkET.setText(plan.getRemark());
    }

    @Override
    protected void onDestroy() {

        startService(new Intent(this, DefiniteService.class));
        
        EventBus.getDefault().post(new DefiniteTimeEvent(planList));
        super.onDestroy();
    }
}
