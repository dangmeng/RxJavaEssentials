package dm.rxjavaessentials.base;

import android.os.Bundle;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by m on 2017/3/23.
 * ${describe}
 */

public abstract class BaseActivity extends SwipeBackActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutRes();
}
