package dm.rxjavaessentials;

import android.app.Application;

import butterknife.ButterKnife;

/**
 * Created by m on 2017/3/23.
 * ${describe}
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
