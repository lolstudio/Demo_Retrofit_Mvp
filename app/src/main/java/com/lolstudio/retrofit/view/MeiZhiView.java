package com.lolstudio.retrofit.view;


import com.lolstudio.retrofit.model.MeiZhiModelBean;

/**
 * Created by lolstudio on 2016/4/18.
 */
public interface MeiZhiView {
    void showData(MeiZhiModelBean bean);

    void showProgress();

    void hideProgress();
}
