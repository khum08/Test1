package com.kotlin.khum.opensqlcipher.net.interceptor;

import android.util.Log;

import com.kotlin.khum.opensqlcipher.utils.StaticField;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/10
 *     desc   : 打印网络请求的Interceptor
 * </pre>
 */
public class LogInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        requestLog(request);
        Response response = chain.proceed(request);
        responseLog(response);
        return response;
    }

    //响应Log
    private void responseLog(Response response) {
        Log.d(StaticField.NETTAG,
                "\nresponseMessage:\n" + response.message() + "\n" +
                        "responseHeaders:\n" + response.headers().toString()+ "\n" +
                        "responseBody:\n"+ getResponseBody(response.body()));
    }

    //请求Log
    private void requestLog(Request request) {
        Log.d(StaticField.NETTAG,
                "\nrequestURL:\n" + request.url() + "\n" +
                        "requestHeaders:\n" + request.headers().toString()+ "\n"+
                        "requestBody:\n" + getRequestBody(request.body()));
    }


    private String getRequestBody(RequestBody body) {
        Buffer buffer = new Buffer();
        String requestContent = null;
        try {
            body.writeTo(buffer);
            requestContent = buffer.readString(UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestContent;
    }


    private String getResponseBody(ResponseBody responseBody) {
        BufferedSource source = responseBody.source();
        String responseContent = null;
        try {
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            responseContent =  buffer.clone().readString(UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

}


