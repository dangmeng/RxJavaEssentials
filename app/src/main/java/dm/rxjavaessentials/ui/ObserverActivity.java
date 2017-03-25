package dm.rxjavaessentials.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import dm.rxjavaessentials.R;
import dm.rxjavaessentials.adapter.CommonAdapter;
import dm.rxjavaessentials.base.BaseActivity;
import dm.rxjavaessentials.model.AppInfo;
import dm.rxjavaessentials.service.AppInfoService;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by m on 2017/3/23.
 * ${describe}Observer sample
 */

public class ObserverActivity extends BaseActivity {

    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.recycleview_ob)
    RecyclerView recyclerView;

    private CommonAdapter adapter;



    @Override
    protected void initData() {
//        String text = "###Observable.create()\n" +
//                " > create()方法使开发者有能力从头开始创建一个Observable。它需要一个OnSubscribe对象,这个对象继承Action1,当观察者订阅Observable时，它作为一个参数传入并执行call()函数。\n"
//                 + "``` java\n" +
//                "Observable.create(new Observable.OnSubscribe<Object>(){\n" +
//                "        @Override\n" +
//                "        public void call(Subscriber<? super Object> subscriber) {\n" +
//                "\n" +
//                "        }\n" +
//                "});\n" +
//                "```\n" +
//                " > 首先，我们创建Observable。我们需要一个函数来检索安装的应用程序列表并把它提供给我们的观察者。我们一个接一个的发射这些应用程序数据，将它们分组到一个单独的列表中，以此来展示响应式方法的灵活性。";
//        RichText.fromMarkdown(text).into(tvDescribe);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        AppInfoService appInfoService = new AppInfoService(this);
        Observable<List<AppInfo>> apps = appInfoService.getApps();
        apps.onBackpressureBuffer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<AppInfo>>() {
                @Override
                public void onCompleted() {}

                @Override
                public void onError(Throwable e) {}

                @Override
                public void onNext(List<AppInfo> appInfos) {

                    if (null == adapter) {
                        adapter = new CommonAdapter(ObserverActivity.this,appInfos);
                    }

                    recyclerView.setAdapter(adapter);
                }
            });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_observer;
    }



}
