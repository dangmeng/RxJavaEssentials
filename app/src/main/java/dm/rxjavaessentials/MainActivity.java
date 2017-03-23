package dm.rxjavaessentials;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dm.rxjavaessentials.model.AppInfo;
import dm.rxjavaessentials.service.AppInfoService;
import dm.rxjavaessentials.utils.DimenUtils;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends SupportActivity {

    @BindView(R.id.title_1) TextView title1;
    @BindView(R.id.icon_observer) ImageView iconObserver;
    @BindView(R.id.icon_just) ImageView iconJust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setIconDrawable(title1, MaterialDesignIconic.Icon.gmi_favorite_outline);

        setIconToImageView(iconObserver,FontAwesome.Icon.faw_smile_o);
        setIconToImageView(iconJust,FontAwesome.Icon.faw_smile_o);

        AppInfoService appInfoService = new AppInfoService(this);
        Observable<List<AppInfo>> apps = appInfoService.getApps();
        apps.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AppInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<AppInfo> appInfos) {
                Log.e("APPINFO==",appInfos.size()+"");
                for (AppInfo appInfo : appInfos) {
                    Log.e("APPINFO==",appInfo.toString());
                }
            }
        });
    }

    /**set icon to textview*/
    private void setIconDrawable(TextView view, IIcon icon) {
        view.setCompoundDrawablesWithIntrinsicBounds(null,
                null, new IconicsDrawable(this)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(16), null);
        view.setCompoundDrawablePadding(DimenUtils.dp2px(this, 10));
    }
    /**set icon to iamgeview*/
    private void  setIconToImageView(ImageView imageView, IIcon icon) {
        imageView.setImageDrawable(new IconicsDrawable(this, icon).sizeDp(24).color(Color.parseColor("#666666")).contourWidthDp(1));
    }
}
