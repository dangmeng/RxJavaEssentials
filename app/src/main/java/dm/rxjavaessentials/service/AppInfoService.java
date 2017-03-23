package dm.rxjavaessentials.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import dm.rxjavaessentials.model.AppInfo;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by m on 2017/3/23.
 * ${describe}
 */

public class AppInfoService {

    private Context context;
    private PackageManager pm;
    public AppInfoService(Context context) {
        this.context = context;
        pm = context.getPackageManager();
    }

    public Observable<List<AppInfo>> getApps() {
        return Observable.create(new Observable.OnSubscribe<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                //创建要返回的集合对象
                List<AppInfo> appInfos = new ArrayList<>();
                //获取手机中所有安装的应用集合
                List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
                //遍历所有的应用集合
                for(ApplicationInfo info : applicationInfos){
                    AppInfo appInfo = new AppInfo();
                    //获取应用程序的图标
                    Drawable app_icon = info.loadIcon(pm);
                    appInfo.setApp_icon(app_icon);
                    //获取应用的名称
                    String app_name = info.loadLabel(pm).toString();
                    appInfo.setApp_name(app_name);
                    //获取应用的包名
                    String packageName = info.packageName;
                    appInfo.setPackagename(packageName);
                    //获取应用的版本号
                    try {
                        PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
                        String app_version = packageInfo.versionName;
                        appInfo.setApp_version(app_version);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //判断应用程序是否是用户程序
                    boolean isUserApp = filterApp(info);
                    appInfo.setUserApp(isUserApp);
                    appInfos.add(appInfo);
                    subscriber.onNext(appInfos);
                }

                if (!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
            }
        });
    }

    //判断应用程序是否是用户程序
    public boolean filterApp(ApplicationInfo info) {
        //原来是系统应用，用户手动升级
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
            //用户自己安装的应用程序
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }
}
