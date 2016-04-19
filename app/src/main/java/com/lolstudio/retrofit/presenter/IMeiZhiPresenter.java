package com.lolstudio.retrofit.presenter;


import com.lolstudio.retrofit.model.MeiZhiModelBean;

/**
 * Created by lolstudio on 2016/4/18.
 */
public interface IMeiZhiPresenter {
    void loadDataSuccess(MeiZhiModelBean bean);

    void loadDataFailed();
}
