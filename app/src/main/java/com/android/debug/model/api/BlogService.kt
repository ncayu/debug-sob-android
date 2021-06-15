package com.android.debug.model.api

import com.android.debug.model.bean.HomeRecommend
import com.android.debug.model.bean.LoginBody
import com.android.debug.model.bean.SobLoop
import com.android.debug.model.bean.SobUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface BlogService {



    /**
     * 轮播图接口
     */
    @GET("/portal/web_size_info/loop")
    suspend fun getLoop(): BaseResponse<List<SobLoop>>

    /**
     * 登录
     */
    @POST("/uc/user/login/{captcha}")
    suspend fun doLogin(
            @Path("captcha") verifyCode: String,
            @Body user: LoginBody): BaseResponse<SobUser>


    @GET("/ct/content/home/recommend/{page}")
    suspend fun getHomeRecommend(@Path("page") page: Int): BaseResponse<HomeRecommend>

}