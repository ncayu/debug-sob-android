package com.android.debug.model.api

import com.android.debug.model.bean.UnionTestBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 商品领券api
 */
interface UnionService {

    @GET("/onSell/{page}")
    suspend fun getLoop(@Path("page") page: Int): BaseResponse<UnionTestBean>

}