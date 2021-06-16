package com.android.debug.model.api

import com.android.debug.model.bean.*
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

    /**
     * 推荐文章接口，等同于网站首页
     * @param page Int 页码，最少1
     * @return BaseResponse<HomeRecommend>
     */
    @GET("/ct/content/home/recommend/{page}")
    suspend fun getHomeRecommend(@Path("page") page: Int): BaseResponse<HomeRecommend>

    /**
     * 获取摸鱼动态列表，等同于摸鱼首页
     * @param page Int
     * @return BaseResponse<SobMoment>
     */
    @GET("/ct/moyu/list/recommend/{page}")
    suspend fun getMomentList(@Path("page") page: Int): BaseResponse<SobMoment>
}