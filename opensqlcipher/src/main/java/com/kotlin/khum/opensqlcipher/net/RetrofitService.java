package com.kotlin.khum.opensqlcipher.net;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kotlin.khum.opensqlcipher.enttity.RequestEntity;
import com.kotlin.khum.opensqlcipher.net.interceptor.LogInterceptor;
import com.kotlin.khum.opensqlcipher.net.interceptor.TransferInterceptor;
import com.kotlin.khum.opensqlcipher.utils.StaticField;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.NET_TAG;


/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   : 网络服务
 * </pre>
 */
public class RetrofitService {

    private static ApiService sApi;

    private static String lastUrl = "";
    private static OkHttpClient okHttpClient;

    public static void init(){
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new TransferInterceptor())
                .addInterceptor(new LogInterceptor())
                .connectTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(StaticField.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        initRetrofit(StaticField.baseUrl);
    }

    //
    private static void initRetrofit(String url) {
        sApi = null;
        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        sApi = sRetrofit.create(ApiService.class);
    }


    //上传数据
    public static void uploadPricing(String url,RequestEntity data){
        if(!url.equals(lastUrl)){
            initRetrofit(url);
            lastUrl = url;
        }
        if(sApi==null){
            Log.d(NET_TAG, "uploadPricing: ");
            init();
        }
        sApi.uploadWxPricing(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetResponse<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(NET_TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(NetResponse<String> stringNetResponse) {
                        Log.d(NET_TAG, "onNext: ");
                        int code = stringNetResponse.getCode();
                        if(code!=0){
                            Log.d(NET_TAG,stringNetResponse.getCode()+stringNetResponse.getMessage());
                        }else {
                            Log.d(NET_TAG,"upload success");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(NET_TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(NET_TAG, "onComplete: ");
                    }
                });
    }



}
