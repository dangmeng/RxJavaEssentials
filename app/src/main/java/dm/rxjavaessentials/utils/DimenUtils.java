package dm.rxjavaessentials.utils;

import android.content.Context;

/**
 * Created by m on 2017/3/23.
 * ${describe}
 */

public class DimenUtils {

    public DimenUtils() {
    }

    public static int dp2px(Context context, float dpValue) {
        return (int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int)(pxValue / context.getResources().getDisplayMetrics().density + 0.5F);
    }

}
