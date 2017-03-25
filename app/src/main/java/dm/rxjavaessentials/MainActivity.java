package dm.rxjavaessentials;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dm.rxjavaessentials.router.Router;
import dm.rxjavaessentials.ui.ObserverActivity;
import dm.rxjavaessentials.utils.DimenUtils;
import me.yokeyword.fragmentation.SupportActivity;

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
    }

    @OnClick(R.id.cd_observer)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cd_observer:
                Router.newIntent(this)
                        .to(ObserverActivity.class)
                        .launch();
                break;
        }
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
