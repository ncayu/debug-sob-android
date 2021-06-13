package com.android.debug.model.api

import com.android.debug.BuildConfig
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import sj.mblog.LL
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author: dr
 * @date: 2021/2/1
 * @description $
 */
class CookiesManager : CookieJar {
    private val cookieStoreBlog = HashMap<String, List<Cookie>>()

    //博客系统登录的key，是服务器生成的，保存在lci这个key中
    private val key_cli = "l_c_i"
    private val key_token = "sob_blog_token"

    override fun saveFromResponse(httpUrl: HttpUrl, list: List<Cookie>) {
        LL.d("Response httpUrl:$httpUrl")
        if (BuildConfig.UNION_BASE_URL.contains(httpUrl.host)) {
            LL.d("保存sob的数据")
            cookieStoreBlog.put(BuildConfig.UNION_BASE_URL, list)
        }
        // for (i in list.indices) {
        //     if (key_cli == list[i].name) {
        //         val lciValue = list[i].value
        //         LL.d("保存lci数据:$lciValue")
        //         cookieStoreBlog[key_cli] = listOf(list[i])
        //     } else if (key_token == list[i].name) {
        //         cookieStoreBlog[key_token] = listOf(list[i])
        //     } else {
        //         LL.d("domain:" + list[i].domain)
        //         LL.d("name  :" + list[i].name)
        //         LL.d("value :" + list[i].value)
        //     }
        // }
    }

    override fun loadForRequest(httpUrl: HttpUrl): List<Cookie> {

        val list = ArrayList<Cookie>()

        // LL.d("loadForRequest httpUrl: ${httpUrl.toString()}")
        // val cookieLci = cookieStoreBlog[key_cli]
        // LL.d("loadForRequest: 隐藏的last captcha id 验证码：$cookieLci")
        // val cookieToken = cookieStoreBlog[key_token]
        // LL.d("loadForRequest: cookieToken                ：$cookieToken")
        //
        // if (cookieLci != null) {
        //     list.addAll(cookieLci)
        // }
        // if (cookieToken != null) {
        //     list.addAll(cookieToken)
        // }
        val sob = cookieStoreBlog.get(BuildConfig.UNION_BASE_URL);
        sob?.apply {
            LL.e("返回sob的cookie", sob.toString())
            return this
        }
        return list
    }
}