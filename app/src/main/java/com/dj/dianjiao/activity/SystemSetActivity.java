package com.dj.dianjiao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.base.BaseActivity;
import com.dj.dianjiao.adapter.OnlineMonitorLVAdapter;
import com.dj.dianjiao.adapter.FileLVAdapter;
import com.dj.dianjiao.adapter.RestartTimeLVAdapter;
import com.dj.dianjiao.adapter.ServerAddressGVAdapter;
import com.dj.dianjiao.domain.OnlineMonitor;
import com.dj.dianjiao.domain.RestartTime;
import com.dj.dianjiao.domain.ServerAddress;
import com.dj.dianjiao.manger.FileManger;
import com.dj.dianjiao.manger.OnlineMonitorManger;
import com.dj.dianjiao.manger.RestartTimeManger;
import com.dj.dianjiao.manger.ServerAddressManger;

import java.util.List;

public class SystemSetActivity extends BaseActivity implements ServerAddressManger.CallBack, RestartTimeManger.CallBack, OnlineMonitorManger.CallBack, FileManger.CallBack {
    private Context mContext;
    private RadioGroup ssRG;
    private RadioButton timeSyncRB;
    private RadioButton serverAddressRB;
    private RadioButton restartTimeRB;
    private RadioButton remoteUpdateRB;
    private RadioButton onlineMonitorRB;
    private RadioButton importExportRB;
    private RadioButton productInfoRB;

    private TextView titleTV;

    private View selectedView;
    private RelativeLayout timeSyncRL;
    private RelativeLayout serverAddressRL;
    private RelativeLayout restartTimeRL;
    private RelativeLayout remoteUpdateRL;
    private RelativeLayout onlineMonitorRL;
    private RelativeLayout importExportRL;
    private RelativeLayout productInfoRL;

    private GridView serverAddressGV;
    private RadioGroup serverAddressRG;
    private RadioButton serverAddressServerRB;
    private RadioButton serverAddressStreamMediaRB;
    private ServerAddressManger serverAddressManger;
    private ServerAddressGVAdapter serverAddressGVAdapter;

    private ListView restartTimeLV;
    private RestartTimeManger restartTimeManger;
    private RestartTimeLVAdapter restartTimeLVAdapter;

    private ListView onlineMonitorLV;
    private OnlineMonitorManger onlineMonitorManger;
    private OnlineMonitorLVAdapter onlineMonitorLVAdapter;

    private ListView exportFileLV;
    private FileLVAdapter exportFileLVAdapter;
    private FileManger exportFileManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        mContext = getApplicationContext();
        initBaseViews();

        serverAddressManger = new ServerAddressManger(mContext);
        serverAddressManger.setCallback(this);
        serverAddressGVAdapter = new ServerAddressGVAdapter(mContext);

        restartTimeManger = new RestartTimeManger(mContext);
        restartTimeManger.setCallback(this);
        restartTimeLVAdapter = new RestartTimeLVAdapter(SystemSetActivity.this);

        onlineMonitorManger = new OnlineMonitorManger(mContext);
        onlineMonitorManger.setCallback(this);
        onlineMonitorLVAdapter = new OnlineMonitorLVAdapter(mContext);

        exportFileLVAdapter = new FileLVAdapter(mContext);
        exportFileManger = new FileManger(mContext);
        exportFileManger.setCallback(this);

        initViews();

        selectedView = timeSyncRL;
        timeSyncRB.setChecked(true);
    }

    private void initViews() {
        ssRG = (RadioGroup) findViewById(R.id.ss_rg);

        timeSyncRB = (RadioButton) findViewById(R.id.ss_time_sync_rb);
        timeSyncRL = (RelativeLayout) findViewById(R.id.ss_time_sync_rl);
        serverAddressRB = (RadioButton) findViewById(R.id.ss_server_address_rb);
        serverAddressRL = (RelativeLayout) findViewById(R.id.ss_server_address_rl);
        restartTimeRB = (RadioButton) findViewById(R.id.ss_restart_time_rb);
        restartTimeRL = (RelativeLayout) findViewById(R.id.ss_restart_time_rl);
        remoteUpdateRB = (RadioButton) findViewById(R.id.ss_remote_update_rb);
        remoteUpdateRL = (RelativeLayout) findViewById(R.id.ss_remote_update_rl);
        onlineMonitorRB = (RadioButton) findViewById(R.id.ss_online_monitor_rb);
        onlineMonitorRL = (RelativeLayout) findViewById(R.id.ss_online_monitor_rl);
        importExportRB = (RadioButton) findViewById(R.id.ss_import_export_rb);
        importExportRL = (RelativeLayout) findViewById(R.id.ss_import_export_rl);
        productInfoRB = (RadioButton) findViewById(R.id.ss_product_info_rb);
        productInfoRL = (RelativeLayout) findViewById(R.id.ss_product_info_rl);
        titleTV = (TextView) findViewById(R.id.ss_title_tv);

        serverAddressGV = (GridView) findViewById(R.id.ss_server_address_gv);
        serverAddressRG = (RadioGroup) findViewById(R.id.ss_server_address_rg);
        serverAddressServerRB = (RadioButton) findViewById(R.id.ss_server_address_server_rb);
        serverAddressStreamMediaRB = (RadioButton) findViewById(R.id.ss_server_address_stream_media_rb);

        restartTimeLV = (ListView) findViewById(R.id.ss_restart_time_lv);

        onlineMonitorLV = (ListView) findViewById(R.id.ss_online_monitor_lv);

        exportFileLV = (ListView) findViewById(R.id.ss_import_export_file_lv);

        serverAddressGV.setAdapter(serverAddressGVAdapter);
        serverAddressRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ss_server_address_server_rb:
                        serverAddressManger.loadServerAddressList();
                        break;
                    case R.id.ss_server_address_stream_media_rb:
                        serverAddressManger.loadStreamMediaAddressList();
                        break;
                }
            }
        });

        restartTimeLV.setAdapter(restartTimeLVAdapter);

        onlineMonitorLV.setAdapter(onlineMonitorLVAdapter);

        exportFileLV.setAdapter(exportFileLVAdapter);

        ssRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ss_time_sync_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = timeSyncRL;
                        timeSyncRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.time_sync);
                        break;
                    case R.id.ss_server_address_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = serverAddressRL;
                        serverAddressRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.server_address);
                        serverAddressServerRB.setChecked(true);
                        serverAddressManger.loadServerAddressList();
                        break;
                    case R.id.ss_restart_time_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = restartTimeRL;
                        restartTimeRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.restart_time);
                        restartTimeManger.loadRestartTimeListList();
                        break;
                    case R.id.ss_remote_update_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = remoteUpdateRL;
                        remoteUpdateRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.remote_update);
                        break;
                    case R.id.ss_online_monitor_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = onlineMonitorRL;
                        onlineMonitorRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.online_monitor);
                        onlineMonitorManger.loadOnlineMonitorList();
                        break;
                    case R.id.ss_import_export_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = importExportRL;
                        importExportRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.import_export);
                        exportFileManger.loadFileList();
                        break;
                    case R.id.ss_product_info_rb:
                        selectedView.setVisibility(View.GONE);
                        selectedView = productInfoRL;
                        productInfoRL.setVisibility(View.VISIBLE);
                        titleTV.setText(R.string.product_info);
                        break;
                }
            }
        });
    }



    @Override
    public void onGetServerAddressListCompleted(List<ServerAddress> serverAddressList) {
        if (serverAddressList!=null){
            serverAddressGVAdapter.setServerAddressList(serverAddressList);
        }
    }

    @Override
    public void onGetStreamMediaAddressListCompleted(List<ServerAddress> streamMediaAddressList) {
        if (streamMediaAddressList!=null){
            serverAddressGVAdapter.setServerAddressList(streamMediaAddressList);
        }
    }

    @Override
    public void onGetRestartTimeListCompleted(List<RestartTime> restartTimeList) {
        if(restartTimeList!=null){
            restartTimeLVAdapter.setRestartTimeList(restartTimeList);
        }
    }

    @Override
    public void onGetOnlineMonitorListCompleted(List<OnlineMonitor> onlineMonitorList) {
        if(onlineMonitorList!=null){
            onlineMonitorLVAdapter.setOnlineMonitorList(onlineMonitorList);
        }
    }

    @Override
    public void onGetFileListCompleted(List<String> fileList) {
        if(fileList!=null){
            exportFileLVAdapter.setFileList(fileList);
        }
    }
}
