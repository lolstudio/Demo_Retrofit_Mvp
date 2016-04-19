package com.lolstudio.retrofit.http.retrofit;


import com.lolstudio.retrofit.model.MeiZhiModelBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lolstudio on 2016/4/18.
 */
public interface MeiZhiService {
    @GET("data/福利/10/{page}")
    Call<MeiZhiModelBean> getMeiZhi(@Path("page") int page);
}
