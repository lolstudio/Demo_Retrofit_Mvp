package com.lolstudio.retrofit.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lolstudio.retrofit.R;
import com.lolstudio.retrofit.face.OnHomeTouchListener;
import com.lolstudio.retrofit.model.MeiZhiModelBean.Results;

import java.util.List;

/**
 * Created by lolstudio on 2016/4/19.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Results> mMeiziList;
    private OnHomeTouchListener listener;

    public HomeAdapter(List<Results> mMeiziList) {
        this.mMeiziList = mMeiziList;
    }

    public void setOnTouchListener(OnHomeTouchListener listener){
        this.listener=listener;
    }

    public void addAll(List<Results> bean) {
        this.mMeiziList.addAll(bean);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Results meizi = mMeiziList.get(position);
        holder.meizi = meizi;
        holder.titleView.setText(meizi.getWho());
    }

    @Override
    public int getItemCount() {
        return mMeiziList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        Results meizi;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.tv_name);
            titleView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onTouch(v.getContext(), meizi);
        }
    }

}
