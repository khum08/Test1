package com.kotlin.khum.opensqlcipher.net.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/10
 *     desc   :
 * </pre>
 */
public class TransferInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder().addHeader("Accept-Encoding", "gzip,deflate")
                .addHeader("Charset", "UTF-8")
                .addHeader("system","android");
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }
}
