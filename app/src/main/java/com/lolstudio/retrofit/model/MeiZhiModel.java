package com.lolstudio.retrofit.model;


import com.lolstudio.retrofit.http.RetrofitClient;
import com.lolstudio.retrofit.http.retrofit.MeiZhiService;
import com.lolstudio.retrofit.presenter.IMeiZhiPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lolstudio on 2016/4/18.
 */
public class MeiZhiModel {
    private IMeiZhiPresenter presenter;

    public MeiZhiModel(IMeiZhiPresenter presenter) {
        this.presenter = presenter;
    }

    public void loadData() {
        MeiZhiService meiZhiService = RetrofitClient.getInstance().creatService(MeiZhiService.class);
        meiZhiService.getMeiZhi(1).enqueue(new Callback<MeiZhiModelBean>() {
            @Override
            public void onResponse(Call<MeiZhiModelBean> call, Response<MeiZhiModelBean> response) {
                presenter.loadDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MeiZhiModelBean> call, Throwable t) {
                presenter.loadDataFailed();
            }
        });

        /*RetrofitClient.getInstance().getService().getMeiZhi(1).enqueue(new Callback<MeiZhiModelBean>() {
            @Override
            public void onResponse(Call<MeiZhiModelBean> call, Response<MeiZhiModelBean> response) {
                presenter.loadDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MeiZhiModelBean> call, Throwable t) {
                presenter.loadDataFailed();
            }
        });*/
    }
}
