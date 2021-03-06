package com.lidroid.xutils.sample.download;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

import java.util.List;


/**
 * 下载服务
 * @author Administrator
 *
 */
public class DownloadService extends Service {

    private static DownloadManager DOWNLOAD_MANAGER;

    /**
     * 
     * 单例模式得到下载管理对象
     * @param appContext
     * @return
     */
    public static DownloadManager getDownloadManager(Context appContext) {
    	//判断下载服务没有启动，启动Service
        if (!DownloadService.isServiceRunning(appContext)) {
            Intent downloadSvr = new Intent("download.service.action");
            appContext.startService(downloadSvr);
        }
        if (DownloadService.DOWNLOAD_MANAGER == null) {
            DownloadService.DOWNLOAD_MANAGER = new DownloadManager(appContext);
        }
        return DOWNLOAD_MANAGER;
    }

    public DownloadService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        if (DOWNLOAD_MANAGER != null) {
            try {
                DOWNLOAD_MANAGER.stopAllDownload();
                DOWNLOAD_MANAGER.backupDownloadInfoList();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        super.onDestroy();
    }

    /**
     * 判断下载Service是否在运行
     * @param context
     * @return
     */
    public static boolean isServiceRunning(Context context) {
        boolean isRunning = false;

        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //得到当前的正在运行的Service
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (serviceList == null || serviceList.size() == 0) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            //判断下载Service是否在运行
            if (serviceList.get(i).service.getClassName().equals(DownloadService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
