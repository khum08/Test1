package com.kotlin.khum.opensqlcipher.net;

import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.net.interceptor.LogInterceptor;
import com.kotlin.khum.opensqlcipher.net.interceptor.TransferInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   :
 * </pre>
 */
public class RetrofitService {

    private static Retrofit sRetrofit;
    private static ApiService sApi;

    public static void init(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new TransferInterceptor())
                .addInterceptor(new LogInterceptor())
                .connectTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(StaticField.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        sApi = sRetrofit.create(ApiService.class);
    }

    public static void uploadPricing(String data){
        sApi.uploadWxPricing(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetResponse<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NetResponse<String> stringNetResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
