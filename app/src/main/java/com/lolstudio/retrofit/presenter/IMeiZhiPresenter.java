package com.lolstudio.retrofit.presenter;


import com.lolstudio.retrofit.model.MeiZhiModelBean.Results;

import java.util.List;

/**
 * Created by lolstudio on 2016/4/18.
 */
public interface IMeiZhiPresenter{
    void loadDataSuccess(List<Results> bean);

    void loadDataFailed();
}
