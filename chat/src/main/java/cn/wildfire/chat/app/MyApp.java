package cn.wildfire.chat.app;

import android.app.ActivityManager;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

import cn.wildfire.chat.kit.WfcUIKit;


public class MyApp extends BaseApp {

    private WfcUIKit wfcUIKit;

    @Override
    public void onCreate() {
        super.onCreate();

        // bugly，务必替换为你自己的!!!
        CrashReport.initCrashReport(getApplicationContext(), "34490ba79f", false);
        // 只在主进程初始化
        if (getCurProcessName(this).equals("cn.wildfirechat.chat")) {
            wfcUIKit = new WfcUIKit();
            wfcUIKit.init(this);
        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
