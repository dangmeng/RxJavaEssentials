package dm.rxjavaessentials.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dm.rxjavaessentials.R;
import dm.rxjavaessentials.model.AppInfo;

/**
 * Created by m on 2017/3/24.
 * ${describe}
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder> {

    private Context mContext;

    private List<AppInfo> appInfos;

    public CommonAdapter(Context context , List<AppInfo> datas) {
        mContext = context;
        appInfos = datas;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycle_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
            AppInfo appInfo = appInfos.get(position);
            holder.appName.setText(appInfo.getApp_name());
            holder.appIcon.setImageDrawable(appInfo.getApp_icon());
    }

    @Override
    public int getItemCount() {
        return appInfos.size();
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.app_name)
        TextView appName;

        @BindView(R.id.app_icon)
        ImageView appIcon;

        CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void addElement(int position, AppInfo element) {
        if (element != null) {
            if (this.appInfos == null) {
                this.appInfos = new ArrayList<>();
            }
            appInfos.add(position, element);
            notifyItemInserted(position);
        }
    }

}
