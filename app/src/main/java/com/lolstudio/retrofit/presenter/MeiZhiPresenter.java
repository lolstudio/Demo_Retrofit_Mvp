package com.lolstudio.retrofit.presenter;


import com.lolstudio.retrofit.model.MeiZhiModel;
import com.lolstudio.retrofit.model.MeiZhiModelBean.Results;
import com.lolstudio.retrofit.view.MeiZhiView;

import java.util.List;

/**
 * Created by lolstudio on 2016/4/18.
 */
public class MeiZhiPresenter implements BasePresenter, IMeiZhiPresenter {
    private MeiZhiView mWeatherView;
    private MeiZhiModel mMeiZhiModel;

    public MeiZhiPresenter(MeiZhiView mWeatherView) {
        this.mWeatherView = mWeatherView;
        mMeiZhiModel = new MeiZhiModel(this);
    }

    public void loadData() {
        mWeatherView.showProgress();
        mMeiZhiModel.loadData();
    }


    @Override
    public void detachView() {
        mWeatherView = null;
    }

    @Override
    public void loadDataSuccess(List<Results> bean) {
        mWeatherView.showData(bean);
        mWeatherView.hideProgress();
    }

    @Override
    public void loadDataFailed() {
        mWeatherView.hideProgress();
    }
}
