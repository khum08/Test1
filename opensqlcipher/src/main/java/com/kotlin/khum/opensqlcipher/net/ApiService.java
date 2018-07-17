package com.kotlin.khum.opensqlcipher.net;

import com.kotlin.khum.opensqlcipher.enttity.RequestEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   : api接口
 * </pre>
 */
public interface ApiService {

    /**
     * 上传报价信息
     * @param data
     * @return
     */
    @POST("upload")
    Observable<NetResponse<String>> uploadWxPricing(@Body RequestEntity data);

}
