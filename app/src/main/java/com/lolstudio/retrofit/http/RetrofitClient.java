package com.lolstudio.retrofit.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lolstudio on 2016/4/18.
 */
public class RetrofitClient {
    private static RetrofitClient mInstance = null;
    private static final String TAG = "RetrofitClient";
    private Retrofit retrofit;

    final static Gson gson =
            new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").serializeNulls().create();

    public RetrofitClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor())
                .build();

        retrofit = new Retrofit.Builder().client(client)
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T createService(Class<T> cls) {
        return retrofit.create(cls);
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) mInstance = new RetrofitClient();
            }
        }
        return mInstance;
    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            /*HttpUrl.Builder builder = request.url().newBuilder().addQueryParameter("sign","");
            Request authorised  = request.newBuilder().url(builder.build())
                    .build();
            return chain.proceed(authorised);*/
            long t1 = System.nanoTime();
            Log.d(TAG, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

}
