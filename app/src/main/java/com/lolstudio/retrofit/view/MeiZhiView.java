package com.lolstudio.retrofit.view;


import com.lolstudio.retrofit.model.MeiZhiModelBean.Results;

import java.util.List;

/**
 * Created by lolstudio on 2016/4/18.
 */
public interface MeiZhiView {
    void showData(List<Results> beanList);

    void showProgress();

    void hideProgress();
}
